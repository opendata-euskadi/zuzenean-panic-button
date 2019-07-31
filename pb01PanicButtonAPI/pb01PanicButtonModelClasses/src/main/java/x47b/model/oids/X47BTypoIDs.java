package x47b.model.oids;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import r01f.annotations.Immutable;
import r01f.guids.OIDBaseMutable;
import r01f.guids.OIDTyped;
import r01f.objectstreamer.annotations.MarshallType;
/**
 * typo identifiers definitions.
 */
@RequiredArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class X47BTypoIDs {
/////////////////////////////////////////////////////////////////////////////////////////
//  BASE
/////////////////////////////////////////////////////////////////////////////////////////
	public static interface X47BTypoID 
				    extends OIDTyped<String> {
		// just a marker interface
	}
	@Immutable
	static abstract class X47BTypoIDBase
	       		  extends OIDBaseMutable<String>  	// normally this should extend OIDBaseInmutable BUT it MUST have a default no-args constructor to be serializable
			   implements X47BTypoID {
		private static final long serialVersionUID = -2265379958676173576L;
		public X47BTypoIDBase() {
			/* default no args constructor for serialization purposes */
		}
		public X47BTypoIDBase(final String id) {
			super(id);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  Typo: Type / theme / subtheme / initiative
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * ID for an interaction type
	 */
	@Immutable
	@MarshallType(as="interactionTypeId")
	@NoArgsConstructor
	public static class X47BInteractionTypeID
				extends X47BTypoIDBase {
		private static final long serialVersionUID = 6032445151471876013L;
		public X47BInteractionTypeID(final String oid) {
			super(oid);
		}
		public static X47BInteractionTypeID valueOf(final String s) {
			return X47BInteractionTypeID.forId(s);
		}
		public static X47BInteractionTypeID fromString(final String s) {
			return X47BInteractionTypeID.forId(s);
		}
		public static X47BInteractionTypeID forId(final String id) {
			return new X47BInteractionTypeID(id);
		}
		public static final X47BInteractionTypeID PROCEDURE = X47BInteractionTypeID.forId("1");
		public static final X47BInteractionTypeID INFO = X47BInteractionTypeID.forId("2");
		public static final X47BInteractionTypeID MAIL = X47BInteractionTypeID.forId("3");
		public static final X47BInteractionTypeID CSA_S = X47BInteractionTypeID.forId("1221");
		public static final X47BInteractionTypeID CSA_C = X47BInteractionTypeID.forId("1222");
		public static final X47BInteractionTypeID CSA_A = X47BInteractionTypeID.forId("1223");
		public static final X47BInteractionTypeID DIRECTORY = X47BInteractionTypeID.forId("1180");
		public static final X47BInteractionTypeID LOST_CALL = X47BInteractionTypeID.forId("1185");
		
         public static final String PROCEDURE_QUICKCODE = "1";
         public static final String INFO_QUICKCODE = "2";
         public static final String MAIL_QUICKCODE = "3";
         public static final String CSA_S_QUICKCODE = "6";
         public static final String CSA_C_QUICKCODE = "7";
         public static final String CSA_A_QUICKCODE = "8";
         public static final String DIRECTORY_QUICKCODE = "4";
         public static final String LOST_CALL_QUICKCODE = "5";
	}
	/**
	 * ID for the theme
	 */
	@Immutable
	@MarshallType(as="typoThemeId")
	@NoArgsConstructor
	public static class X47BTypoThemeID
				extends X47BTypoIDBase {
		private static final long serialVersionUID = -3989544516931844745L;
		public X47BTypoThemeID(final String oid) {
			super(oid);
		}
		public static X47BTypoThemeID valueOf(final String s) {
			return X47BTypoThemeID.forId(s);
		}
		public static X47BTypoThemeID fromString(final String s) {
			return X47BTypoThemeID.forId(s);
		}
		public static X47BTypoThemeID forId(final String id) {
			return new X47BTypoThemeID(id);
		}
	}
	/**
	 * ID for the sub-theme
	 */
	@Immutable
	@MarshallType(as="typoSubThemeId")
	@NoArgsConstructor
	public static class X47BTypoSubThemeID
				extends X47BTypoIDBase {
		private static final long serialVersionUID = -8201258074163047593L;
		public X47BTypoSubThemeID(final String oid) {
			super(oid);
		}
		public static X47BTypoSubThemeID valueOf(final String s) {
			return X47BTypoSubThemeID.forId(s);
		}
		public static X47BTypoSubThemeID fromString(final String s) {
			return X47BTypoSubThemeID.forId(s);
		}
		public static X47BTypoSubThemeID forId(final String id) {
			return new X47BTypoSubThemeID(id);
		}
	}
	/**
	 * ID for the sub-theme
	 */
	@Immutable
	@MarshallType(as="typoInitiativeId")
	@NoArgsConstructor
	public static class X47BTypoInitiativeID
				extends X47BTypoIDBase {
		private static final long serialVersionUID = 2975845832540742618L;
		public X47BTypoInitiativeID(final String oid) {
			super(oid);
		}
		public static X47BTypoInitiativeID valueOf(final String s) {
			return X47BTypoInitiativeID.forId(s);
		}
		public static X47BTypoInitiativeID fromString(final String s) {
			return X47BTypoInitiativeID.forId(s);
		}
		public static X47BTypoInitiativeID forId(final String id) {
			return new X47BTypoInitiativeID(id);
		}
	}
}
