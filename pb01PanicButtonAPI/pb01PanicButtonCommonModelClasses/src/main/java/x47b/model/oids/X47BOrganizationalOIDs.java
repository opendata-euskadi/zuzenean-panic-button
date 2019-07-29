package x47b.model.oids;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import r01f.annotations.Immutable;
import r01f.objectstreamer.annotations.MarshallType;
import x47b.model.oids.X47BOIDs.X47BModelObjectOIDBase;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOIDBase;

/**
 * Organizational oids
 */
@RequiredArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class X47BOrganizationalOIDs {
/////////////////////////////////////////////////////////////////////////////////////////
//  ORGANIZATION / DIVISION / SERVICE 
/////////////////////////////////////////////////////////////////////////////////////////
	@Immutable
	@MarshallType(as="orgOid")
	@NoArgsConstructor
	public static class X47BOrganizationOID
				extends X47BPersistableObjectOIDBase {
		private static final long serialVersionUID = 4985595053081655476L;
		public X47BOrganizationOID(final String oid) {
			super(oid);
		}
		public static X47BOrganizationOID valueOf(final String s) {
			return X47BOrganizationOID.forId(s);
		}
		public static X47BOrganizationOID fromString(final String s) {
			return X47BOrganizationOID.forId(s);
		}
		public static X47BOrganizationOID forId(final String id) {
			return new X47BOrganizationOID(id);
		}
		public static X47BOrganizationOID supply() {
			return X47BOrganizationOID.forId(X47BModelObjectOIDBase.supplyId());
		}
	}
	@Immutable
	@MarshallType(as="orgDivisionOid")
	@NoArgsConstructor
	public static class X47BOrgDivisionOID
				extends X47BPersistableObjectOIDBase {
		private static final long serialVersionUID = -4480202236021085670L;
		public X47BOrgDivisionOID(final String oid) {
			super(oid);
		}
		public static X47BOrgDivisionOID valueOf(final String s) {
			return X47BOrgDivisionOID.forId(s);
		}
		public static X47BOrgDivisionOID fromString(final String s) {
			return X47BOrgDivisionOID.forId(s);
		}
		public static X47BOrgDivisionOID forId(final String id) {
			return new X47BOrgDivisionOID(id);
		}
		public static X47BOrgDivisionOID supply() {
			return X47BOrgDivisionOID.forId(X47BModelObjectOIDBase.supplyId());
		}
	}
	@Immutable
	@MarshallType(as="orgDivisionServiceOid")
	@NoArgsConstructor
	public static class X47BOrgDivisionServiceOID
				extends X47BPersistableObjectOIDBase {
		private static final long serialVersionUID = 7791733828828520678L;
		public X47BOrgDivisionServiceOID(final String oid) {
			super(oid);
		}
		public static X47BOrgDivisionServiceOID valueOf(final String s) {
			return X47BOrgDivisionServiceOID.forId(s);
		}
		public static X47BOrgDivisionServiceOID fromString(final String s) {
			return X47BOrgDivisionServiceOID.forId(s);
		}
		public static X47BOrgDivisionServiceOID forId(final String id) {
			return new X47BOrgDivisionServiceOID(id);
		}
		public static X47BOrgDivisionServiceOID supply() {
			return X47BOrgDivisionServiceOID.forId(X47BModelObjectOIDBase.supplyId());
		}
	}
	@Immutable
	@MarshallType(as="orgDivisionServiceLocationGroupOid")
	@NoArgsConstructor
	public static class X47BOrgDivisionServiceLocationGroupOID
				extends X47BPersistableObjectOIDBase {
		private static final long serialVersionUID = 4250337881848966224L;
		public X47BOrgDivisionServiceLocationGroupOID(final String oid) {
			super(oid);
		}
		public static X47BOrgDivisionServiceLocationGroupOID valueOf(final String s) {
			return X47BOrgDivisionServiceLocationGroupOID.forId(s);
		}
		public static X47BOrgDivisionServiceLocationGroupOID fromString(final String s) {
			return X47BOrgDivisionServiceLocationGroupOID.forId(s);
		}
		public static X47BOrgDivisionServiceLocationGroupOID forId(final String id) {
			return new X47BOrgDivisionServiceLocationGroupOID(id);
		}
		public static X47BOrgDivisionServiceLocationGroupOID supply() {
			return X47BOrgDivisionServiceLocationGroupOID.forId(X47BModelObjectOIDBase.supplyId());
		}
	}
	@Immutable
	@MarshallType(as="orgDivisionServiceLocationOid")
	@NoArgsConstructor
	public static class X47BOrgDivisionServiceLocationOID
				extends X47BPersistableObjectOIDBase {
		private static final long serialVersionUID = -4723392764569241773L;
		public X47BOrgDivisionServiceLocationOID(final String oid) {
			super(oid);
		}
		public static X47BOrgDivisionServiceLocationOID valueOf(final String s) {
			return X47BOrgDivisionServiceLocationOID.forId(s);
		}
		public static X47BOrgDivisionServiceLocationOID fromString(final String s) {
			return X47BOrgDivisionServiceLocationOID.forId(s);
		}
		public static X47BOrgDivisionServiceLocationOID forId(final String id) {
			return new X47BOrgDivisionServiceLocationOID(id);
		}
		public static X47BOrgDivisionServiceLocationOID supply() {
			return X47BOrgDivisionServiceLocationOID.forId(X47BModelObjectOIDBase.supplyId());
		}
	}	
/////////////////////////////////////////////////////////////////////////////////////////
//  WorkPlace
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * OID for an workPlace, location can have one or multiple workPlace.
	 */
	@Immutable
	@MarshallType(as="workPlaceOid")
	@NoArgsConstructor
	public static class X47BWorkPlaceOID
				extends X47BPersistableObjectOIDBase {

		private static final long serialVersionUID = 3492537351039178420L;
		public X47BWorkPlaceOID(final String oid) {
			super(oid);
		}
		public static X47BWorkPlaceOID valueOf(final String s) {
			return X47BWorkPlaceOID.forId(s);
		}
		public static X47BWorkPlaceOID fromString(final String s) {
			return X47BWorkPlaceOID.forId(s);
		}
		public static X47BWorkPlaceOID forId(final String id) {
			return new X47BWorkPlaceOID(id);
		}
		public static X47BWorkPlaceOID supply() {
			return X47BWorkPlaceOID.forId(X47BModelObjectOIDBase.supplyId());
		}
	}
}
