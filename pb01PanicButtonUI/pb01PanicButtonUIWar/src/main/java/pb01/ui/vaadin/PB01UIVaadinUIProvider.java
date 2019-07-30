package pb01.ui.vaadin;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.inject.Injector;
import com.vaadin.server.UIClassSelectionEvent;
import com.vaadin.server.UICreateEvent;
import com.vaadin.server.UIProvider;

import lombok.Getter;
import lombok.experimental.Accessors;

@Singleton
@Accessors(prefix="_")
public class PB01UIVaadinUIProvider
     extends UIProvider {

	private static final long serialVersionUID = -7398094005589935590L;
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final transient Injector _injector;

/////////////////////////////////////////////////////////////////////////////////////////
// 	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01UIVaadinUIProvider(final Injector injector) {
		_injector = injector;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override @SuppressWarnings("unchecked")
	public PB01UIVaadinUI createInstance(final UICreateEvent event) {
		// get the app
		Class<PB01UIVaadinUI> vaadinUIType = (Class<PB01UIVaadinUI>)event.getUIClass();

		PB01UIVaadinUI outApp = _injector.getInstance(vaadinUIType);
		return outApp;
	}
	@Override
	public Class<PB01UIVaadinUI> getUIClass(final UIClassSelectionEvent event) {
		return PB01UIVaadinUI.class;
	}
}
