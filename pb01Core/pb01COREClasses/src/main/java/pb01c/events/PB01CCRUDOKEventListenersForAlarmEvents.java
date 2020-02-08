package pb01c.events;

import javax.inject.Inject;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.gson.reflect.TypeToken;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pb01a.client.api.PB01APanicButtonClientAPI;
import pb01a.model.PB01AAlarmEvent;
import pb01a.model.PB01AAlarmMessage;
import pb01c.internal.notifier.PB01CPanicButtonNotifier;
import r01f.core.services.notifier.annotations.UseEMailNotifier;
import r01f.core.services.notifier.annotations.UseLogNotifier;
import r01f.core.services.notifier.annotations.UseMessagingNotifier;
import r01f.core.services.notifier.annotations.UseVoiceNotifier;
import r01f.events.COREEventBusEventListener;
import r01f.events.COREServiceMethodExecEvents.COREServiceMethodExecOKEvent;
import r01f.events.crud.CRUDOKEventListenerBase;
import r01f.model.persistence.CRUDOK;

/**
 * Contains event listeners for the alarm events
 * Notification is done in TWO STAGES:
 * 		STAGE 1: transforms the PB01AAlarmEvent into an PB01AAlarmMessage and post it again
 *		     	 to the event bus
 *
 *		STAGE 2: consumes PB01AAlarmMessage objects and uses a concrete PB01CPanicButtonNotifierServices (email / sms / voice...)
 *			     to post a notification
 *               Multiple alarm event listeners are available
 *               <ul>
 *               		<li>SMS based: notifies using SMS services to send a SMS / push notification to the security person mobile</li>
 *               		<li>EMail based: notifies using SMTP services to send an email to the security person mobile</li>
 *               		<li>Voice based: notifies using twilio services to send a voice call to the security person mobile</li>
 *               		<li>Lob based</li>
 *               </ul>
 *               All them implements {@link PB01CPanicButtonNotifier} that contains a single method: sendNotification(message)
 *
 * Using this two-stages approach improves reusability of alarm events since anyone (ie the UI)
 * can subscribe to PB01AAlarmMessage posts
 */
@Slf4j
@NoArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class PB01CCRUDOKEventListenersForAlarmEvents {
/////////////////////////////////////////////////////////////////////////////////////////
//	STAGE 1: transforms the PB01AAlarmEvent into an PB01AAlarmMessage and post it again
//		     to the event bus
/////////////////////////////////////////////////////////////////////////////////////////
	public static class PB01CCRUDOKEventListenerForAlarmEvents
		        extends CRUDOKEventListenerBase {

		private final EventBus _eventBus;
		private final PB01APanicButtonClientAPI _api;

		@Inject
		public PB01CCRUDOKEventListenerForAlarmEvents(final EventBus eventBus,
													 final PB01APanicButtonClientAPI api) {
			super(PB01AAlarmEvent.class);	// listen for PB01AAlarmEvent messages
			_eventBus = eventBus;
			_api = api;
		}

		@Subscribe	// subscribes this event listener at the EventBus
		@Override @SuppressWarnings("unchecked")
		public void onPersistenceOperationOK(final COREServiceMethodExecOKEvent opOKEvent) {
			if (_isEventForSuccessfulCreate(opOKEvent)) {
				try {
					// [1]-Get the alarm event from the bus event
					CRUDOK<PB01AAlarmEvent> alarmCRUD = opOKEvent.getAsCOREServiceMethodExecOK()
														 .as(CRUDOK.class);
					PB01AAlarmEvent alarmEvent = alarmCRUD.getOrThrow();
					// [2]-A bit of logging
					log.warn("[AlarmEvent event handler]: NOTIFY ALARM on {}/{}/{}/{}/{}",
							 alarmEvent.getOrganization().getId(),alarmEvent.getDivision().getId(),alarmEvent.getService().getId(),alarmEvent.getLocation().getId(),alarmEvent.getWorkPlace().getId());

					// [3]-Compose the alarm message to be sent frm the event
					PB01AAlarmMessage alarmMessage = PB01CAlarmMessageBuilder.using(_api)
																	  	   .createForEvent(alarmEvent);

					// [4]-Post the alarm message to the event bus
					_eventBus.post(alarmMessage);
				} catch(Throwable th) {
					log.error("Error creating an alarm message {}",th.getMessage(),th);
				}
			}
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  STAGE II: consumes PB01AAlarmMessage objects and uses a concrete PB01CPanicButtonNotifierServices (email / sms / voice...)
//			  to post a notification
//  IMPORTANT!!
//  Guava's {@link EventBus} does not cope with generic events:
//  		There MUST exist specific event handlers associated with CRUD events for each of the indexable model objects
//  		These event handlers are registered at {@link BeanImplementedPersistenceServicesCoreBootstrapGuiceModuleBase} where the @Subscribe annotated method is
//  		used to know the event-type and associate the event type with the event handler
//
//   	The underlying problem is that if a generic event like CRUDOperationEvent<R extends PersistableModelObject<? extends OID>> is used
//  		due to type erasure, ALL event handlers would be associated with the SAME raw event-type: {@link CRUDOperationEvent}
//  		The consequences of this multiple-association is that ALL three event handlers will be called for any event, independently of
//  		the event type
/////////////////////////////////////////////////////////////////////////////////////////
	private static abstract class PB01CCRUDOKEventListenerForAlarmMessageBase
					   implements COREEventBusEventListener {	// DO NOT forget: this automatically registers the listener at the event bus

		private final PB01CPanicButtonNotifier _notifierServices;

		public PB01CCRUDOKEventListenerForAlarmMessageBase(final PB01CPanicButtonNotifier notifierServices) {
			_notifierServices = notifierServices;
		}
		@Subscribe	// subscribes this event listener at the EventBus
		public void onAlarmMessage(final PB01AAlarmMessage alarmMessage) {
			try {
				// [2]-Check if the notifier is enabled
				if (!_notifierServices.isEnabled()) {
					log.warn("[AlarmMessage event handler]: {} is NOT enabled; the notification will not be sent",
							 _notifierServices.getClass().getSimpleName());
					return;
				}
				// [2]-Use the concrete [notifierServices] (sms/voice/email...) to send the message
				log.warn("[AlarmMessage event handler]: use {} notifier to notify",
						  _notifierServices.getClass().getSimpleName());
				_notifierServices.sendNotification(alarmMessage);
			} catch(Throwable th) {
				log.error("Error notifying alarm {}",th.getMessage(),th);
			}
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  LOG
/////////////////////////////////////////////////////////////////////////////////////////
	public static class PB01CCRUDOKEventListenersForAlarmMessageLog
			    extends PB01CCRUDOKEventListenerForAlarmMessageBase {
		@Inject
		public PB01CCRUDOKEventListenersForAlarmMessageLog(@UseLogNotifier final PB01CPanicButtonNotifier notifierServices) {
			super(notifierServices);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  Messaging (SMS)
/////////////////////////////////////////////////////////////////////////////////////////
	public static class PB01CCRUDOKEventListenersForAlarmMessageNotifyByMessaging
			    extends PB01CCRUDOKEventListenerForAlarmMessageBase {
		@Inject
		public PB01CCRUDOKEventListenersForAlarmMessageNotifyByMessaging(@UseMessagingNotifier final PB01CPanicButtonNotifier notifierServices) {
			super(notifierServices);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  MAIL
/////////////////////////////////////////////////////////////////////////////////////////
	public static class PB01CCRUDOKEventListenersForAlarmMessageNotifyByEMail
			    extends PB01CCRUDOKEventListenerForAlarmMessageBase {
		@Inject
		public PB01CCRUDOKEventListenersForAlarmMessageNotifyByEMail(@UseEMailNotifier final PB01CPanicButtonNotifier notifierServices) {
			super(notifierServices);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  VOICE (Twilio)
/////////////////////////////////////////////////////////////////////////////////////////
	public static class PB01CCRUDOKEventListenersForAlarmMessageNotifyByVoice
			    extends PB01CCRUDOKEventListenerForAlarmMessageBase {
		@Inject
		public PB01CCRUDOKEventListenersForAlarmMessageNotifyByVoice(@UseVoiceNotifier final PB01CPanicButtonNotifier notifierServices) {
			super(notifierServices);
		}
	}
}
