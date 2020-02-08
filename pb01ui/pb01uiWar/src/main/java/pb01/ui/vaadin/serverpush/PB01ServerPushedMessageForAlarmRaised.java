package pb01.ui.vaadin.serverpush;

import com.google.common.eventbus.EventBus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import pb01a.model.PB01AAlarmMessage;
import r01f.ui.vaadin.serverpush.VaadinServerPushedMessage;
import r01f.ui.vaadin.serverpush.VaadinServerPushedMessagesBroadcastListener;
import r01f.ui.vaadin.serverpush.VaadinServerPushedMessagesBroadcaster;
import r01f.util.types.Strings;

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
 */
@Accessors(prefix="_")
@RequiredArgsConstructor
public class PB01ServerPushedMessageForAlarmRaised
  implements VaadinServerPushedMessage {

	private static final long serialVersionUID = -1806364438494109494L;
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final PB01AAlarmMessage _message;
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public String asText() {
		// TODO use the name
		return Strings.customized("{}/{}/{}/{}/{} at {}",
								   _message.getOrganization().getEntityId(),_message.getDivision().getEntityId(),_message.getService().getEntityId(),_message.getLocation().getEntityId(),_message.getWorkPlace().getEntityId(),
								   _message.getDateTime());
	}
}
