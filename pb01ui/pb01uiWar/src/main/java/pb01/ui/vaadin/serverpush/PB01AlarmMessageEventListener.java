package pb01.ui.vaadin.serverpush;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import lombok.RequiredArgsConstructor;
import pb01.ui.bootstrap.PB01UIServletContextListener;
import pb01a.model.PB01AAlarmMessage;
import r01f.events.COREEventBusEventListener;
import r01f.ui.vaadin.serverpush.VaadinServerPushedMessagesBroadcastListener;
import r01f.ui.vaadin.serverpush.VaadinServerPushedMessagesBroadcaster;

/**
 * 	Server push mechanism:
 *		a) Someone pushes the panic button which makes an HTTP call to a REST-endpoint
 *
 *		b) the CORE upon persisting the raised alarm posts a message to the {@link EventBus} (guava)
 *		   (see PB01CCRUDOKEventListenersForAlarmEvents)
 *
 *      c) the UI is subscribed to that type of messages at the {@link EventBus}
 *		   (see PB01AlarmMessageEventListener and PB01UIServletContextListener)
 *
 *      d) when handling the {@link EventBus}-received message at the UI, another message is posted
 *         to the {@link VaadinServerPushedMessagesBroadcaster} which in turn invokes the receiveBroadcast(...)
 *         method on every registered listener (instances of {@link VaadinServerPushedMessagesBroadcastListener})
 *
 * The subscription is done at the contextInitialized() method of {@link PB01UIServletContextListener}
 */
@RequiredArgsConstructor
public class PB01AlarmMessageEventListener
  implements COREEventBusEventListener {
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * UIs implementing {@link VaadinServerPushedMessagesBroadcastListener}
	 * are registered at the broadcaster who is in charge of broadcasting the messages
	 */
	private final VaadinServerPushedMessagesBroadcaster _serverPushedMessagesBroadcaster;

/////////////////////////////////////////////////////////////////////////////////////////
//	POSTED MESSAGE HANDLER METHOD
/////////////////////////////////////////////////////////////////////////////////////////
	@Subscribe
	public void onAlarmMessage(final PB01AAlarmMessage msg) {
		// Send a server-pushed event to show a popup in every UIs
		PB01ServerPushedMessageForAlarmRaised broadcastedMsg = new PB01ServerPushedMessageForAlarmRaised(msg);
		_serverPushedMessagesBroadcaster.broadcast(broadcastedMsg);
	}
}
