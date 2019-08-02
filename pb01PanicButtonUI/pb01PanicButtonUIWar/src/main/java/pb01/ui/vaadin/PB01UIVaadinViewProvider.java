package pb01.ui.vaadin;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.inject.Injector;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewProvider;

import lombok.extern.slf4j.Slf4j;
import pb01.ui.vaadin.view.PB01MainView;
import pb01.ui.vaadin.view.PB01MainView2;
import r01f.util.types.Strings;

@Slf4j
@Singleton
public class PB01UIVaadinViewProvider
  implements ViewProvider {

	private static final long serialVersionUID = 4980630595486553732L;
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final transient Injector _injector;
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01UIVaadinViewProvider(final Injector injector) {
		_injector = injector;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public String getViewName(final String viewAndParameters) {
		String[] splitViewAndParameters = viewAndParameters.split( "/" );
		return splitViewAndParameters[0];
	}
	@Override
	public View getView(final String viewName) {
		View outView = null;
		log.info("...going to [{}] view",viewName);
		if ( Strings.isNullOrEmpty(viewName)
		  || PB01UIVaadinViews.MAIN.is(viewName) ) {
			outView = _injector.getInstance(PB01MainView2.class);
		}
		else if (viewName.equals("mainOLD")) {
			outView = _injector.getInstance(PB01MainView.class);
		}
		else {
			throw new IllegalArgumentException(viewName + " is NOT a vaadin view!");
		}
		return outView;
	}
}
