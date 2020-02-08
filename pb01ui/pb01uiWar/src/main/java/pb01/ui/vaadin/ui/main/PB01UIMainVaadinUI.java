/**
 *
 */
package pb01.ui.vaadin.ui.main;

import java.util.Locale;

import javax.inject.Inject;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.ui.UI;

import lombok.extern.slf4j.Slf4j;
import pb01.ui.vaadin.serverpush.PB01ServerPushedMessageForAlarmRaised;
import pb01.ui.vaadin.view.PB01ViewContainer;
import pb01a.common.internal.P01AAppCodes;
import r01f.internal.R01FAppCodes;
import r01f.locale.Language;
import r01f.ui.i18n.UII18NService;
import r01f.ui.i18n.UIMessageBundle;
import r01f.ui.vaadin.serverpush.VaadinServerPushedMessage;
import r01f.ui.vaadin.serverpush.VaadinServerPushedMessagesBroadcastListener;
import r01f.ui.vaadin.serverpush.VaadinServerPushedMessagesBroadcaster;
import r01f.util.types.locale.Languages;

@Theme("panicButtonStyles")	// see [WebContent]/VAADIN/themes/demoStyles
							// check the styles.scss and ensure it's like:
							//		.demoStyles {
							// 			@include addons;
  							//			@include demoStyles;
							//		}
@Push(PushMode.MANUAL)	// server-pushed messages (used to show a notification when an alarm is raised)
@UIMessageBundle(basename={ R01FAppCodes.R01_UI_APP_CODE_STR,	// loads i18n resource bundles from r01f.i18n
							P01AAppCodes.UI_APPCODE_STR })		// loads i18n resource bundles from pb01ui.i18n

@Slf4j
public class PB01UIMainVaadinUI
	 extends UI
  implements VaadinServerPushedMessagesBroadcastListener<PB01ServerPushedMessageForAlarmRaised> {		// listens for server-push broadcast messages

	private static final long serialVersionUID = 9089164482239711005L;
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private UII18NService _i18n;
	private ViewProvider _viewProvider;
	private VaadinServerPushedMessagesBroadcaster _serverPushedMessagesBroadcaster;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTORS
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01UIMainVaadinUI() {
		// BEWARE!! Vaadin requires the UI to have a no-args constructor (see R01UIDemoVaadinServlet)
	}
	@Inject
	public PB01UIMainVaadinUI(final UII18NService i18n,
						  	  final ViewProvider viewProvider,
						  	  // server-pushed events broadcaster (used server-push a message to the client when an alarm event is fired)
						  	  final VaadinServerPushedMessagesBroadcaster serverPushedMessagesBroadcaster) {
		// this constructor is called from the UI provider
		_i18n = i18n;
		_viewProvider = viewProvider;
		_serverPushedMessagesBroadcaster = serverPushedMessagesBroadcaster;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	protected void init(final VaadinRequest request) {
		log.info("Initializing MAIN Vaadin UI....");
		this.getPage()
			.setTitle(_i18n.getMessage("app.window.title"));

	// set the preferred lang
		Locale locale = Languages.getLocale(Language.DEFAULT);
        this.setLocale(locale);
        this.getSession().setLocale( locale );

    // build the ui
        PB01ViewContainer main = new PB01ViewContainer(_i18n);
		super.setContent(main); 	// Building the main layout

	// Create a navigator to control the views
		Navigator nav = new Navigator(this,						// the ui
									  main.getViewDisplay());	// the view display where the navigator "injects" the views
		nav.addProvider(_viewProvider);
		super.setNavigator(nav);

	// register the server-pushed messages listener
		_serverPushedMessagesBroadcaster.register(this);
		this.setPollInterval(1000);
	}
    @Override
    public void detach() {
        _serverPushedMessagesBroadcaster.unregister(this);	// Must also unregister when the UI expires
        super.detach();
    }
/////////////////////////////////////////////////////////////////////////////////////////
//	SERVER PUSH (see VaadinServerPushedMessagesBroadcastListener)
// 	Server push mechanism:
//		a) Someone pushes the panic button which makes an HTTP call to a REST-endpoint
//
//		b) the CORE upon persisting the raised alarm posts a message to the {@link EventBus} (guava)
//		   (see PB01CCRUDOKEventListenersForAlarmEvents)
//
//      c) the UI is subscribed to that type of messages at the {@link EventBus}
//		   (see PB01AlarmMessageEventListener and PB01UIServletContextListener)
//
//      d) when handling the {@link EventBus}-received message at the UI, another message is posted
//         to the {@link VaadinServerPushedMessagesBroadcaster} which in turn invokes the receiveBroadcast(...)
//         method on every registered listener (instances of {@link VaadinServerPushedMessagesBroadcastListener})
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public <M extends VaadinServerPushedMessage> boolean acceptsMessagesOfType(final Class<M> messageType) {
		return messageType == PB01ServerPushedMessageForAlarmRaised.class;
	}
	@Override
	public void receiveBroadcast(final PB01ServerPushedMessageForAlarmRaised message) {
        // Must lock the session to execute logic safely
        this.access(new Runnable() {
				            @Override
				            public void run() {
				            	PB01ViewContainer main = (PB01ViewContainer)PB01UIMainVaadinUI.this.getContent();
				            	main.showEventRaisedMessage(message);
				            }
        			});
	}
}
