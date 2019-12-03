package pb01.ui.vaadin.ui.login;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.VaadinServlet;

import lombok.extern.slf4j.Slf4j;
import pb01.ui.vaadin.PB01UIVaadinUIProvider;

@Singleton
@VaadinServletConfiguration(ui = PB01UILoginVaadinUI.class,
							productionMode = false)
@Slf4j
public class PB01UIVaadinLoginServlet
	 extends VaadinServlet
  implements SessionInitListener {

	private static final long serialVersionUID = -3956460153634114253L;
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	protected final PB01UIVaadinUIProvider _vaadinUIProvider;

/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01UIVaadinLoginServlet(final PB01UIVaadinUIProvider vaadinUIProvider) {
		_vaadinUIProvider = vaadinUIProvider;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  SESSION
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void sessionInit(final SessionInitEvent event) throws ServiceException {
		log.info("Creating a NEW vaadin session");
		event.getSession()
			 .addUIProvider(_vaadinUIProvider);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  CONTEXT
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	protected void servletInitialized() {
		log.info("Init vaadin LOGIN servlet context");
		this.getService()
			.addSessionInitListener(this);
	}
}
