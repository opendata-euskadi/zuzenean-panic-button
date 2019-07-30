package pb01;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import r01f.types.JavaPackage;
import r01f.ui.vaadin.VaadinUI;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class PB01UI
		      extends VaadinUI {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public static final JavaPackage PACKAGE = JavaPackage.of(PB01UI.class);


}
