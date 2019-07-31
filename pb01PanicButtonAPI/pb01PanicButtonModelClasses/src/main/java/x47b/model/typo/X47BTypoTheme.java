package x47b.model.typo;

import lombok.NoArgsConstructor;
import r01f.locale.LanguageTexts;
import r01f.objectstreamer.annotations.MarshallType;
import x47b.model.oids.X47BTypoIDs.X47BTypoThemeID;

@MarshallType(as="typoTheme")
@NoArgsConstructor
public class X47BTypoTheme
	 extends X47BTypoItem<X47BTypoThemeID,X47BTypoTheme> {
	
	private static final long serialVersionUID = 2517107344360954737L;
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BTypoTheme(final X47BTypoThemeID id,final String quickCode) {
		super(id,quickCode);
	}
	public X47BTypoTheme(final X47BTypoThemeID id,final String quickCode,
					     final LanguageTexts names) {
		super(id,quickCode,
			  names);
	}
	public X47BTypoTheme(final X47BTypoThemeID id,final String quickCode,
						 final String nameES,final String nameEU) {
		super(id,quickCode,
			  nameES,nameEU);
	}
}
