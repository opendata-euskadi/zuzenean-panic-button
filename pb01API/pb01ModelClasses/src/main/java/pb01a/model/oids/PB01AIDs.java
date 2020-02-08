package pb01a.model.oids;

import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import r01f.annotations.Immutable;
import r01f.guids.OIDBaseMutable;
import r01f.guids.OIDTyped;

public class PB01AIDs {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * The Id must be the linked oid.
	 * @param <O>
	 */
	public static interface PB01AID
				    extends OIDTyped<String> {
		public void normalize();
	}
	@Immutable
	static abstract class PB01AIDBase
	       		  extends OIDBaseMutable<String> 	// normally this should extend OIDBaseInmutable BUT it MUST have a default no-args constructor to be serializable
			   implements PB01AID {
		private static final long serialVersionUID = -4358217246961346683L;
		public PB01AIDBase() {
			/* default no args constructor for serialization purposes */
		}
		public PB01AIDBase(final String id) {
			super(id);
		}
		@Override
		public void normalize() {
			this.setId(this.getId().toUpperCase());
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * The Id must be the linked oid.
	 * @param <O>
	 */
	public static interface PB01APersistableObjectID<O extends PB01APersistableObjectOID>
				    extends PB01AID {
		// just a marker interface
	}
	@Immutable
	static abstract class PB01AModelObjectIDBase<O extends PB01APersistableObjectOID>
	       		  extends PB01AIDBase 	// normally this should extend OIDBaseInmutable BUT it MUST have a default no-args constructor to be serializable
			   implements PB01APersistableObjectID<O> {
		private static final long serialVersionUID = -2265379958676173576L;
		public PB01AModelObjectIDBase() {
			/* default no args constructor for serialization purposes */
		}
		public PB01AModelObjectIDBase(final String id) {
			super(id);
		}
		@Override
		public void normalize() {
			this.setId(this.getId().toUpperCase());
		}
	}
//	public static interface PB01AOrganizationalID
//				    extends OIDTyped<String> {
//		// just a marker interface
//	}
//	@Inmutable
//	static abstract class PB01AOrganizationIDBase
//	       		  extends OIDBaseMutable<String>  	// usually this should extend OIDBaseInmutable BUT it MUST have a default no-args constructor to be serializable
//			   implements PB01AIDOrganizationalID {
//		private static final long serialVersionUID = -2265379958676173576L;
//		public PB01AOrganizationIDBase() {
//			/* default no args constructor for serialization purposes */
//		}
//		public PB01AOrganizationIDBase(final String id) {
//			super(id);
//		}
//	}
}
