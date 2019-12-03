package x47b.model.typo;

import lombok.NoArgsConstructor;
import r01f.locale.LanguageTexts;
import r01f.objectstreamer.annotations.MarshallType;
import x47b.model.oids.X47BTypoIDs.X47BTypoSubThemeID;

@MarshallType(as="typoSubTheme")
@NoArgsConstructor
public class X47BTypoSubTheme
	 extends X47BTypoItem<X47BTypoSubThemeID,X47BTypoSubTheme> {
	
	private static final long serialVersionUID = 2517107344360954737L;
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BTypoSubTheme(final X47BTypoSubThemeID id,final String quickCode) {
		super(id,quickCode);
	}
	public X47BTypoSubTheme(final X47BTypoSubThemeID id,final String quickCode,
							final LanguageTexts names) {
		super(id,quickCode,
			  names);
	}
	public X47BTypoSubTheme(final X47BTypoSubThemeID id,final String quickCode,
							final String nameES,final String nameEU) {
		super(id,quickCode,
			  nameES,nameEU);
	}
}
