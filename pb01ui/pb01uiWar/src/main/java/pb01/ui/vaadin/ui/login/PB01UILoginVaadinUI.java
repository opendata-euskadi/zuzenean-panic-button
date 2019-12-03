/**
 *
 */
package pb01.ui.vaadin.ui.login;

import java.util.Locale;

import javax.inject.Inject;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;

import lombok.extern.slf4j.Slf4j;
import pb01.ui.vaadin.PB01UIVaadinViews;
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
@UIMessageBundle(basename={X47BAppCodes.UI_APPCODE_STR})		// loads i18n resource bundles from x47b.i18n
@Slf4j
public class PB01UILoginVaadinUI
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
	public PB01UILoginVaadinUI() {
		// BEWARE!! Vaadin requires the UI to have a no-args constructor (see R01UIDemoVaadinServlet)
	}
	@Inject
	public PB01UILoginVaadinUI(final UII18NService i18n,
							   final ViewProvider viewProvider) {
		// this constructor is called from the UI provider
		_i18n = i18n;
		_viewProvider = viewProvider;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	protected void init(final VaadinRequest request) {
		log.info("Initializing LOGIN Vaadin UI....");
		this.getPage()
			.setTitle(_i18n.getMessage("login.window.title"));

		Locale locale = Languages.getLocale(Language.DEFAULT);
		this.setLocale(locale);
		this.getSession().setLocale(locale);

	// build the ui
		// load the login view
		PB01UILoginView loginView = (PB01UILoginView)_viewProvider.getView(PB01UIVaadinViews.LOGIN.asString());

		// put the view into a component container
		Panel panel = new Panel(loginView);
		panel.setSizeFull();
		panel.setWidth(50,Unit.PERCENTAGE);
//		this.setComponentAlignment(panel,Alignment.MIDDLE_CENTER);

		super.setContent(panel);
	}
}
