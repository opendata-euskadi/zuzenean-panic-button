package pb01.ui.vaadin;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import r01f.ui.vaadin.view.VaadinViewID;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class PB01UIVaadinViews {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public static final VaadinViewID MAIN = VaadinViewID.forId("main");
	public static final VaadinViewID LOGIN = VaadinViewID.forId("login");
}
