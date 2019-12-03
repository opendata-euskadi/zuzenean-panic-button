package pb01.ui.vaadin.ui.login;

import java.util.Locale;

import javax.inject.Inject;

import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import lombok.experimental.Accessors;
import pb01.PB01UI;
import r01f.guids.CommonOIDs.Password;
import r01f.guids.CommonOIDs.UserCode;
import r01f.servlet.SecurityContextServletFilterBase;
import r01f.ui.i18n.UII18NService;
import r01f.util.types.Strings;
import r01f.util.types.locale.Languages;
import x47b.api.context.X47BSecurityContext;
import x47b.api.context.X47BSecurityContextUserData;

@Accessors(prefix = "_")
public class PB01UILoginView
     extends VerticalLayout
  implements View {

	private static final long serialVersionUID = -3842197138419966575L;
/////////////////////////////////////////////////////////////////////////////////////////
//  fIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final UII18NService _i18n;

	private final PB01UILoginPresenter _presenter;

	private final TextField _txtUsr;
	private final PasswordField _txtPasswd;
	private final Button _btnExec;


///////////////////////////////////////////////////////////////////////////////////////
// 	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01UILoginView(final UII18NService i18n,
						   final PB01UILoginPresenter presenter) {
		_i18n = i18n;

		_presenter = presenter;

		// user & password
		_txtUsr = new TextField("User");
		_txtPasswd = new PasswordField("Password");
		_btnExec = new Button("Enter");
		_btnExec.setEnabled(false);
		_btnExec.addStyleName("btn btnExecute");

		_txtUsr.addValueChangeListener(event -> _btnExec.setEnabled(Strings.isNOTNullOrEmpty(_txtUsr.getValue())
																 && Strings.isNOTNullOrEmpty(_txtPasswd.getValue())));
		_txtPasswd.addValueChangeListener(event -> _btnExec.setEnabled(Strings.isNOTNullOrEmpty(_txtUsr.getValue())
																	&& Strings.isNOTNullOrEmpty(_txtPasswd.getValue())));
		_btnExec.addClickListener(event -> _logIn());

		VerticalLayout vl = new VerticalLayout();
		vl.setSpacing(true);
		vl.setMargin(true);

		// logo & help
		Label logo = new Label(_i18n.getMessage("login"));
		logo.addStyleNames(ValoTheme.LABEL_BOLD,ValoTheme.LABEL_H1,ValoTheme.LABEL_COLORED);
		logo.setIcon(VaadinIcons.KEY_O);
		vl.addComponent(logo);
		vl.addComponent(new Label("Use admin/admin or one of the [person]'s user & password"));

		// user & password
		vl.addComponent(_txtUsr);
		vl.addComponent(_txtPasswd);
		vl.addComponent(_btnExec);
		vl.setComponentAlignment(_btnExec,Alignment.BOTTOM_RIGHT);

		this.addComponent(vl);
		this.setMargin(true);

		// shortcut listener
		this.addShortcutListener(new ShortcutListener("Shortcut",
													  ShortcutAction.KeyCode.ENTER,null) {	// no modifier keys

										private static final long serialVersionUID = 1L;

										@Override
										public void handleAction(final Object sender,final Object target) {
											if (_btnExec.isEnabled()) {
												_logIn();
											}
										}
								});
	}
	@Override
	public void enter(final ViewChangeEvent event) {
		// nothing
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	private void _logIn() {
		X47BSecurityContextUserData userData = _presenter.userPasswordLogIn(UserCode.forId(_txtUsr.getValue()),
											   	  	  			   			Password.forId(_txtPasswd.getValue()));
		if (userData != null) {
			// set the [security context] at the vaadin session
			X47BSecurityContext securityContext = X47BSecurityContext.userLoginToSecurityContext(userData);
			securityContext.setLoginUrl(PB01UI.LOGIN_PAGE_URL);	// do not forget to set the login url

		  	VaadinService.getCurrentRequest()
		  				 .getWrappedSession()
			 			 .setAttribute(SecurityContextServletFilterBase.AUTH_CONTEXT_SESSION_PARAM_NAME,
			 						   securityContext);	// create a security context
		  	VaadinService.getCurrentRequest()
						 .getWrappedSession()
						 .setAttribute("change",
								 	   false);

			// set locale
			Locale locale = Languages.getLocale(userData.getPrefLang());
			this.getUI().getSession()
						.setLocale(locale);

			// goto the main page
			Page.getCurrent().setLocation(PB01UI.MAIN_PAGE_URL.asString());

//        	UI.getCurrent().getNavigator()
//        				   .navigateTo(R01DemoUIViews.MAIN.getId());
//			R01DemoUIPersonListView mainView = (R01DemoUIPersonListView)_viewProvider.getView(R01DemoUIViews.MAIN.getId());
//        	UI.getCurrent().setContent(mainView);
		} else {
			Notification.show(_i18n.getMessage("login.invalid.user.or.passwd"),
							  Notification.Type.ERROR_MESSAGE);
		}
	}
}
