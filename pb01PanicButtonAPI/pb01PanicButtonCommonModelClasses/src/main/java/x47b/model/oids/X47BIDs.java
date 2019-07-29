package x47b.model.oids;

import r01f.annotations.Immutable;
import r01f.guids.OIDBaseMutable;
import r01f.guids.OIDTyped;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;

public class X47BIDs {
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * The Id must be the linked oid.
	 * @param <O>
	 */
	public static interface X47BID
				    extends OIDTyped<String> {
		public void normalize();
	}
	@Immutable
	static abstract class X47BIDBase
	       		  extends OIDBaseMutable<String> 	// normally this should extend OIDBaseInmutable BUT it MUST have a default no-args constructor to be serializable
			   implements X47BID {
		private static final long serialVersionUID = -4358217246961346683L;
		public X47BIDBase() {
			/* default no args constructor for serialization purposes */
		}
		public X47BIDBase(final String id) {
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
	public static interface X47BModelObjectID<O extends X47BPersistableObjectOID>
				    extends X47BID {
		// just a marker interface
	}
	@Immutable
	static abstract class X47BModelObjectIDBase<O extends X47BPersistableObjectOID>
	       		  extends X47BIDBase 	// normally this should extend OIDBaseInmutable BUT it MUST have a default no-args constructor to be serializable
			   implements X47BModelObjectID<O> {
		private static final long serialVersionUID = -2265379958676173576L;
		public X47BModelObjectIDBase() {
			/* default no args constructor for serialization purposes */
		}
		public X47BModelObjectIDBase(final String id) {
			super(id);
		}
		@Override
		public void normalize() {		
			this.setId(this.getId().toUpperCase());
		}
	}
//	public static interface X47BOrganizationalID 
//				    extends OIDTyped<String> {
//		// just a marker interface
//	}
//	@Inmutable
//	static abstract class X47BOrganizationIDBase
//	       		  extends OIDBaseMutable<String>  	// normally this should extend OIDBaseInmutable BUT it MUST have a default no-args constructor to be serializable
//			   implements X47BOrganizationalID {
//		private static final long serialVersionUID = -2265379958676173576L;
//		public X47BOrganizationIDBase() {
//			/* default no args constructor for serialization purposes */
//		}
//		public X47BOrganizationIDBase(final String id) {
//			super(id);
//		}
//	}
}
