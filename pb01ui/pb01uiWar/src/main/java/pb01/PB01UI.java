package pb01;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pb01.ui.vaadin.PB01UIVaadinViews;
import r01f.types.JavaPackage;
import r01f.types.url.Url;
import r01f.ui.vaadin.VaadinUI;
import r01f.util.types.Strings;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class PB01UI
		      extends VaadinUI {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public static final JavaPackage PACKAGE = JavaPackage.of(PB01UI.class);

/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public static final String WEB_APP_NAME = "pb01uiWar";

	public static final Url LOGIN_PAGE_URL = Url.from(Strings.customized("/{}/login/",
																		 WEB_APP_NAME));
	public static final Url MAIN_PAGE_URL = Url.from(Strings.customized("/{}#!{}",
																		 WEB_APP_NAME,PB01UIVaadinViews.MAIN));
}
