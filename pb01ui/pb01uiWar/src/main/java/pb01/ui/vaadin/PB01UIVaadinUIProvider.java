package pb01.ui.vaadin;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.inject.Injector;
import com.vaadin.server.UIClassSelectionEvent;
import com.vaadin.server.UICreateEvent;
import com.vaadin.server.UIProvider;
import com.vaadin.ui.UI;

import lombok.Getter;
import lombok.experimental.Accessors;
import pb01.ui.vaadin.ui.login.PB01UILoginVaadinUI;
import pb01.ui.vaadin.ui.main.PB01UIMainVaadinUI;
import r01f.types.url.UrlPath;
import r01f.util.types.Strings;

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
	@Override
	public UI createInstance(final UICreateEvent event) {
		// get the app
		Class<? extends UI> uiType = event.getUIClass();
		return _injector.getInstance(uiType);
	}
	@Override
	public Class<? extends UI> getUIClass(final UIClassSelectionEvent event) {
		String pathInfo = event.getRequest().getPathInfo();
		UrlPath urlPath = Strings.isNOTNullOrEmpty(pathInfo) ? UrlPath.from(event.getRequest().getPathInfo())
															 : null;
		Class<? extends UI> outUI = null;
		if (urlPath == null
		 || urlPath.startsWith(UrlPath.from("login"))) {
			outUI = PB01UILoginVaadinUI.class;
		}
		else {
			outUI = PB01UIMainVaadinUI.class;
		}
		return outUI;
	}
}
