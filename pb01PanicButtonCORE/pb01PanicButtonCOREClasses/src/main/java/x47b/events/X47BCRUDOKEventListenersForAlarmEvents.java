package x47b.events;

import javax.inject.Inject;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import lombok.extern.slf4j.Slf4j;
import r01f.bootstrap.BeanImplementedPersistenceServicesCoreBootstrapGuiceModuleBase;
import r01f.events.PersistenceOperationEvents.PersistenceOperationOKEvent;
import r01f.events.crud.CRUDOperationOKEventListenerBase;
import r01f.notifier.UseEMailNotifier;
import r01f.notifier.UseLogNotifier;
import r01f.notifier.UseMessagingNotifier;
import r01f.notifier.UseVoiceNotifier;
import x47b.client.api.X47BPanicButtonClientAPI;
import x47b.internal.services.X47BPanicButtonNotifierServices;
import x47b.model.X47BAlarmEvent;
import x47b.model.X47BAlarmMessage;

/**
 * Contains event listeners for the alarm events
 * TWO alarm event listeners are available
 * <ul>
 * 		<li>Latinia based: notifies using latinia services to send a SMS / push notification to the security person mobile</li>
 * 		<li>EMail based: notifies using SMTP services to send an email to the security person mobile</li>
 * </ul>
 * Both implements {@link X47BPanicButtonNotifierServices} that contains a single method: sendNotification(message)
 *
 * IMPORTANT!!
 * Guava's {@link EventBus} does not cope with generic events: 
 * 		There MUST exist specific event handlers associated with CRUD events for each of the indexable model objects
 * 		These event handlers are registered at {@link BeanImplementedPersistenceServicesCoreBootstrapGuiceModuleBase} where the @Subscribe annotated method is
 * 		used to know the event-type and associate the event type with the event handler
 * 
 *  	The underlying problem is that if a generic event like CRUDOperationEvent<R extends PersistableModelObject<? extends OID>> is used
 * 		due to type erasure, ALL event handlers would be associated with the SAME raw event-type: {@link CRUDOperationEvent}
 * 		The consequences of this multiple-association is that ALL three event handlers will be called for any event, independently of 
 * 		the event type
 */
@Slf4j
public class X47BCRUDOKEventListenersForAlarmEvents {
/////////////////////////////////////////////////////////////////////////////////////////
//  BASE 
/////////////////////////////////////////////////////////////////////////////////////////
	private static abstract class X47BCRUDOKEventListenersForAlarmEventsBase
			     		  extends CRUDOperationOKEventListenerBase {
		
		private X47BPanicButtonClientAPI _api;
		private X47BPanicButtonNotifierServices _notifierServices;
		
		public X47BCRUDOKEventListenersForAlarmEventsBase(final X47BPanicButtonClientAPI api,
														  final X47BPanicButtonNotifierServices notifierServices) {
			super(X47BAlarmEvent.class);
			_api = api;
			_notifierServices = notifierServices;
		}
		@Subscribe	// subscribes this event listener at the EventBus
		@Override
		public void onPersistenceOperationOK(final PersistenceOperationOKEvent opOKEvent) {
			if (_isEventForSuccessfulCreate(opOKEvent)) {					
				try {					
					// [0]-Check if the notifier is enabled
					if (!_notifierServices.isEnabled()) {
						log.warn(">> {} is NOT enabled; the notification will not be sent",_notifierServices.getClass().getSimpleName());
						return;
					}
					// [1]-Get the alarm event from the bus event
					X47BAlarmEvent alarmEvent = opOKEvent.getResultAsCRUDOperationOKOn(X47BAlarmEvent.class)
														 .getOrThrow();
					// [2]-A bit of logging
					log.info(">> [{}] NOTIFY ALARM on {}/{}/{}/{}/{}",
							 this.getClass().getSimpleName(),
							 alarmEvent.getOrganization().getId(),alarmEvent.getDivision().getId(),alarmEvent.getService().getId(),alarmEvent.getLocation().getId(),alarmEvent.getWorkPlace().getId());
					
					// [3]-Compose the alarm message to be sent frm the event
					X47BAlarmMessage alarmMessage = X47BAlarmMessageBuilder.using(_api)
																	  	   .createForEvent(alarmEvent);
					_notifierServices.sendNotification(alarmMessage);
				} catch(Throwable th) {
					log.error("Error notifying alarm {}",th.getMessage(),th);
				}
			}
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  LOG
/////////////////////////////////////////////////////////////////////////////////////////
	public static class X47BCRUDOKEventListenersForAlarmEventsLog
			    extends X47BCRUDOKEventListenersForAlarmEventsBase {		
		@Inject
		public X47BCRUDOKEventListenersForAlarmEventsLog(				 final X47BPanicButtonClientAPI api,
														 @UseLogNotifier final X47BPanicButtonNotifierServices notifierServices) {
			super(api,
				  notifierServices);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  Messaging (SMS)
/////////////////////////////////////////////////////////////////////////////////////////
	public static class X47BCRUDOKEventListenersForAlarmEventsNotifyByMessaging
			    extends X47BCRUDOKEventListenersForAlarmEventsBase {		
		@Inject
		public X47BCRUDOKEventListenersForAlarmEventsNotifyByMessaging(						 final X47BPanicButtonClientAPI api,
																 	   @UseMessagingNotifier final X47BPanicButtonNotifierServices notifierServices) {
			super(api,
				  notifierServices);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  MAIL
/////////////////////////////////////////////////////////////////////////////////////////	
	public static class X47BCRUDOKEventListenersForAlarmEventsNotifyByEMail
			    extends X47BCRUDOKEventListenersForAlarmEventsBase {		
		@Inject
		public X47BCRUDOKEventListenersForAlarmEventsNotifyByEMail(					 final X47BPanicButtonClientAPI api,
																   @UseEMailNotifier final X47BPanicButtonNotifierServices notifierServices) {
			super(api,
				  notifierServices);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  VOICE (Twilio)
/////////////////////////////////////////////////////////////////////////////////////////	
	public static class X47BCRUDOKEventListenersForAlarmEventsNotifyByVoice
			    extends X47BCRUDOKEventListenersForAlarmEventsBase {		
		@Inject
		public X47BCRUDOKEventListenersForAlarmEventsNotifyByVoice(					 final X47BPanicButtonClientAPI api,
																   @UseVoiceNotifier final X47BPanicButtonNotifierServices notifierServices) {
			super(api,
				  notifierServices);
		}
	}
}
