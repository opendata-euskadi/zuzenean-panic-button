package x47b.model.oids;

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
public abstract class X47BOIDs {
/////////////////////////////////////////////////////////////////////////////////////////
//	OIDs
/////////////////////////////////////////////////////////////////////////////////////////
	public static interface X47BModelObjectOID
					extends OIDTyped<String>,
							SuppliesOID {
		/* a marker interface */
	}
	public static interface X47BPersistableObjectOID
					extends PersistableObjectOID,
							X47BModelObjectOID {
		/* a marker interface */
	}
	/**
	 * Base OID for every X47B OIDs
	 */
	@Immutable
	public static abstract class X47BModelObjectOIDBase
	              		 extends OIDBaseMutable<String> 	// normally this should extend OIDBaseInmutable BUT it MUST have a default no-args constructor to be serializable
					  implements X47BModelObjectOID,
					  			 OIDIsGenerated {			// the oid is generated at DB-level
		private static final long serialVersionUID = -492440610093169966L;
		public X47BModelObjectOIDBase() {
			/* default no args constructor for serialization purposes */
		}
		public X47BModelObjectOIDBase(final String id) {
			super(id);
		}
		/**
		 * Generates an oid
		 * @return the generated oid
		 */
		protected static String supplyId() {
			return X47BGUIDDispenser.generateGUID();
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Base OID for every X47B OIDs
	 */
	@Immutable
	public static abstract class X47BPersistableObjectOIDBase
	              		 extends X47BModelObjectOIDBase 	// normally this should extend OIDBaseInmutable BUT it MUST have a default no-args constructor to be serializable
					  implements X47BPersistableObjectOID {
		private static final long serialVersionUID = -9164578219787647708L;
		public X47BPersistableObjectOIDBase() {
			/* default no args constructor for serialization purposes */
		}
		public X47BPersistableObjectOIDBase(final String id) {
			super(id);
		}
	}
}
