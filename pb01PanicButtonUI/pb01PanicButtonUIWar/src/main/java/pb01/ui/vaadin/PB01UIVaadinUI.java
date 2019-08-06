/**
 *
 */
package pb01.ui.vaadin;

import java.util.Locale;

import javax.inject.Inject;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.ui.UI;

import pb01.ui.vaadin.view.PB01ViewContainer;
import r01f.locale.Language;
import r01f.ui.i18n.UII18NService;
import r01f.ui.i18n.UIMessageBundle;
import r01f.util.types.locale.Languages;
import x47b.common.internal.X47BAppCodes;

@Theme("panicButtonStyles")	// see [WebContent]/VAADIN/themes/demoStyles
							// check the styles.scss and ensure it's like:
							//		.demoStyles {
							// 			@include addons;
  							//			@include demoStyles;
							//		}
@Push(PushMode.MANUAL)
@UIMessageBundle(basename={X47BAppCodes.UI_APPCODE_STR})	// loads i18n resource bundles from x47b.i18n
public class PB01UIVaadinUI
	 extends UI {

	private static final long serialVersionUID = 9089164482239711005L;
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private UII18NService _i18n;
	private ViewProvider _viewProvider;

/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTORS
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01UIVaadinUI() {
		// BEWARE!! Vaadin requires the UI to have a no-args constructor (see R01UIDemoVaadinServlet)
	}
	@Inject
	public PB01UIVaadinUI(final UII18NService i18n,
						  final ViewProvider viewProvider ) {
		// this constructor is called from the UI provider
		_i18n = i18n;
		_viewProvider = viewProvider;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	protected void init(final VaadinRequest request) {
		this.getPage()
			.setTitle(_i18n.getMessage("app.window.title"));

		Locale locale = Languages.getLocale(Language.DEFAULT);
        this.setLocale(locale);
        this.getSession().setLocale( locale );

    // build the ui
        PB01ViewContainer main = new PB01ViewContainer();
		super.setContent(main); 	// Building the main layout

	// Create a navigator to control the views
		Navigator nav = new Navigator(this,						// the ui
									  main.getViewDisplay());	// the view display where the navigator "injects" the views
		super.setNavigator(nav);

	// Load the application navigation views.
		super.getNavigator()
			 .addProvider(_viewProvider);
	}

}
