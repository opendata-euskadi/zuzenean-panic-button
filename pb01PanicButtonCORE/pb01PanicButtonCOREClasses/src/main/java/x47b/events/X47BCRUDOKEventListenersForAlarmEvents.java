package x47b.events;

import javax.inject.Inject;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import r01f.core.services.notifier.UseEMailNotifier;
import r01f.core.services.notifier.UseLogNotifier;
import r01f.core.services.notifier.UseMessagingNotifier;
import r01f.core.services.notifier.UseVoiceNotifier;
import r01f.events.COREEventBusEventListener;
import r01f.events.PersistenceOperationEvents.PersistenceOperationOKEvent;
import r01f.events.crud.CRUDOperationOKEventListenerBase;
import x47b.client.api.X47BPanicButtonClientAPI;
import x47b.internal.services.X47BPanicButtonNotifierServices;
import x47b.model.X47BAlarmEvent;
import x47b.model.X47BAlarmMessage;

/**
 * Contains event listeners for the alarm events
 * Notification is done in TWO STAGES:
 * 		STAGE 1: transforms the X47BAlarmEvent into an X47BAlarmMessage and post it again
 *		     	 to the event bus
 *
 *		STAGE 2: consumes X47BAlarmMessage objects and uses a concrete X47BPanicButtonNotifierServices (email / sms / voice...)
 *			     to post a notification
 *               Multiple alarm event listeners are available
 *               <ul>
 *               		<li>SMS based: notifies using SMS services to send a SMS / push notification to the security person mobile</li>
 *               		<li>EMail based: notifies using SMTP services to send an email to the security person mobile</li>
 *               		<li>Voice based: notifies using twilio services to send a voice call to the security person mobild</li>
 *               		<li>Lob based</li>
 *               </ul>
 *               All them implements {@link X47BPanicButtonNotifierServices} that contains a single method: sendNotification(message)
 *
 * Using this two-stages approach improves reusability of alarm events since anyone (ie the UI)
 * can subscribe to X47BAlarmMessage posts
 */
@Slf4j
@NoArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class X47BCRUDOKEventListenersForAlarmEvents {
/////////////////////////////////////////////////////////////////////////////////////////
//	STAGE 1: transforms the X47BAlarmEvent into an X47BAlarmMessage and post it again
//		     to the event bus
/////////////////////////////////////////////////////////////////////////////////////////
	public static class X47BCRUDOKEventListenerForAlarmEvents
		        extends CRUDOperationOKEventListenerBase {

		private final EventBus _eventBus;
		private final X47BPanicButtonClientAPI _api;

		@Inject
		public X47BCRUDOKEventListenerForAlarmEvents(final EventBus eventBus,
													 final X47BPanicButtonClientAPI api) {
			super(X47BAlarmEvent.class);	// listen for X47BAlarmEvent messages
			_eventBus = eventBus;
			_api = api;
		}
		@Subscribe	// subscribes this event listener at the EventBus
		@Override
		public void onPersistenceOperationOK(final PersistenceOperationOKEvent opOKEvent) {
			if (_isEventForSuccessfulCreate(opOKEvent)) {
				try {
					// [1]-Get the alarm event from the bus event
					X47BAlarmEvent alarmEvent = opOKEvent.getResultAsCRUDOperationOKOn(X47BAlarmEvent.class)
														 .getOrThrow();
					// [2]-A bit of logging
					log.warn("[AlarmEvent event handler]: NOTIFY ALARM on {}/{}/{}/{}/{}",
							 alarmEvent.getOrganization().getId(),alarmEvent.getDivision().getId(),alarmEvent.getService().getId(),alarmEvent.getLocation().getId(),alarmEvent.getWorkPlace().getId());

					// [3]-Compose the alarm message to be sent frm the event
					X47BAlarmMessage alarmMessage = X47BAlarmMessageBuilder.using(_api)
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
//  STAGE II: consumes X47BAlarmMessage objects and uses a concrete X47BPanicButtonNotifierServices (email / sms / voice...)
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
	private static abstract class X47BCRUDOKEventListenerForAlarmMessageBase
					   implements COREEventBusEventListener {	// DO NOT forget: this automatically registers the listener at the event bus

		private final X47BPanicButtonNotifierServices _notifierServices;

		public X47BCRUDOKEventListenerForAlarmMessageBase(final X47BPanicButtonNotifierServices notifierServices) {
			_notifierServices = notifierServices;
		}
		@Subscribe	// subscribes this event listener at the EventBus
		public void onAlarmMessage(final X47BAlarmMessage alarmMessage) {
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
	public static class X47BCRUDOKEventListenersForAlarmMessageLog
			    extends X47BCRUDOKEventListenerForAlarmMessageBase {
		@Inject
		public X47BCRUDOKEventListenersForAlarmMessageLog(@UseLogNotifier final X47BPanicButtonNotifierServices notifierServices) {
			super(notifierServices);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  Messaging (SMS)
/////////////////////////////////////////////////////////////////////////////////////////
	public static class X47BCRUDOKEventListenersForAlarmMessageNotifyByMessaging
			    extends X47BCRUDOKEventListenerForAlarmMessageBase {
		@Inject
		public X47BCRUDOKEventListenersForAlarmMessageNotifyByMessaging(@UseMessagingNotifier final X47BPanicButtonNotifierServices notifierServices) {
			super(notifierServices);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  MAIL
/////////////////////////////////////////////////////////////////////////////////////////
	public static class X47BCRUDOKEventListenersForAlarmMessageNotifyByEMail
			    extends X47BCRUDOKEventListenerForAlarmMessageBase {
		@Inject
		public X47BCRUDOKEventListenersForAlarmMessageNotifyByEMail(@UseEMailNotifier final X47BPanicButtonNotifierServices notifierServices) {
			super(notifierServices);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  VOICE (Twilio)
/////////////////////////////////////////////////////////////////////////////////////////
	public static class X47BCRUDOKEventListenersForAlarmMessageNotifyByVoice
			    extends X47BCRUDOKEventListenerForAlarmMessageBase {
		@Inject
		public X47BCRUDOKEventListenersForAlarmMessageNotifyByVoice(@UseVoiceNotifier final X47BPanicButtonNotifierServices notifierServices) {
			super(notifierServices);
		}
	}
}
