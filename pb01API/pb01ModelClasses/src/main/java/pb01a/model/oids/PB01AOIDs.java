package pb01a.model.oids;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import r01f.annotations.Immutable;
import r01f.guids.OIDBaseMutable;
import r01f.guids.OIDIsGenerated;
import r01f.guids.OIDTyped;
import r01f.guids.PersistableObjectOID;
import r01f.guids.SuppliesOID;

/**
 * Panic button service identifiers definitions.
 */
@RequiredArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class PB01AOIDs {
/////////////////////////////////////////////////////////////////////////////////////////
//	OIDs
/////////////////////////////////////////////////////////////////////////////////////////
	public static interface PB01AModelObjectOID
					extends OIDTyped<String>,
							SuppliesOID {
		/* a marker interface */
	}
	public static interface PB01APersistableObjectOID
					extends PersistableObjectOID,
							PB01AModelObjectOID {
		/* a marker interface */
	}
	/**
	 * Base OID for every PB01 OIDs
	 */
	@Immutable
	public static abstract class PB01AModelObjectOIDBase
	              		 extends OIDBaseMutable<String> 	// usually this should extend OIDBaseInmutable BUT it MUST have a default no-args constructor to be serializable
					  implements PB01AModelObjectOID,
					  			 OIDIsGenerated {			// the oid is generated at DB-level
		private static final long serialVersionUID = -492440610093169966L;
		public PB01AModelObjectOIDBase() {
			/* default no args constructor for serialization purposes */
		}
		public PB01AModelObjectOIDBase(final String id) {
			super(id);
		}
		/**
		 * Generates an oid
		 * @return the generated oid
		 */
		protected static String supplyId() {
			return PB01AGUIDDispenser.generateGUID();
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Base OID for every PB01A OIDs
	 */
	@Immutable
	public static abstract class PB01APersistableObjectOIDBase
	              		 extends PB01AModelObjectOIDBase 	// usually this should extend OIDBaseInmutable BUT it MUST have a default no-args constructor to be serializable
					  implements PB01APersistableObjectOID {
		private static final long serialVersionUID = -9164578219787647708L;
		public PB01APersistableObjectOIDBase() {
			/* default no args constructor for serialization purposes */
		}
		public PB01APersistableObjectOIDBase(final String id) {
			super(id);
		}
	}
}
