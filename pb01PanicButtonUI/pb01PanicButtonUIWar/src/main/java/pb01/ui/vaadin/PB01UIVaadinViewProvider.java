package pb01.ui.vaadin;

import javax.inject.Inject;

import com.google.inject.Injector;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewProvider;

import pb01.ui.vaadin.org.PB01UIOrgDetailView;
import r01f.util.types.Strings;

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
	public PB01UIVaadinViewProvider( final Injector injector ) {
		_injector = injector;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public String getViewName( final String viewAndParameters ) {
		String[] splitViewAndParameters = viewAndParameters.split( "/" );
		return splitViewAndParameters[0];
	}
	@Override
	public View getView( final String viewName ) {
		View outView = null;
		if ( Strings.isNullOrEmpty(viewName)
		  || PB01UIVaadinViews.MAIN.is(viewName) ) {
			outView = _injector.getInstance(PB01UIOrgDetailView.class);
		}
		else {
			throw new IllegalArgumentException(viewName + " is NOT a vaadin view!");
		}
		return outView;
	}
}
