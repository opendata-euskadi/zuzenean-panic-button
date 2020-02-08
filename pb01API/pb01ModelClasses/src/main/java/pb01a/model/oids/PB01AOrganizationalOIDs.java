package pb01a.model.oids;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import pb01a.model.oids.PB01AOIDs.PB01AModelObjectOIDBase;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOIDBase;
import r01f.annotations.Immutable;
import r01f.objectstreamer.annotations.MarshallType;

/**
 * Organizational oids
 */
@RequiredArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class PB01AOrganizationalOIDs {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public interface PB01AOrgObjectOID
			 extends PB01APersistableObjectOID {
		// just an interface
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  ORGANIZATION / DIVISION / SERVICE
/////////////////////////////////////////////////////////////////////////////////////////
	@Immutable
	@MarshallType(as="orgOid")
	@NoArgsConstructor
	public static class PB01AOrganizationOID
				extends PB01APersistableObjectOIDBase
			 implements PB01AOrgObjectOID {
		private static final long serialVersionUID = 4985595053081655476L;
		public PB01AOrganizationOID(final String oid) {
			super(oid);
		}
		public static PB01AOrganizationOID valueOf(final String s) {
			return PB01AOrganizationOID.forId(s);
		}
		public static PB01AOrganizationOID fromString(final String s) {
			return PB01AOrganizationOID.forId(s);
		}
		public static PB01AOrganizationOID forId(final String id) {
			return new PB01AOrganizationOID(id);
		}
		public static PB01AOrganizationOID supply() {
			return PB01AOrganizationOID.forId(PB01AModelObjectOIDBase.supplyId());
		}
	}
	@Immutable
	@MarshallType(as="orgDivisionOid")
	@NoArgsConstructor
	public static class PB01AOrgDivisionOID
				extends PB01APersistableObjectOIDBase
			 implements PB01AOrgObjectOID {
		private static final long serialVersionUID = -4480202236021085670L;
		public PB01AOrgDivisionOID(final String oid) {
			super(oid);
		}
		public static PB01AOrgDivisionOID valueOf(final String s) {
			return PB01AOrgDivisionOID.forId(s);
		}
		public static PB01AOrgDivisionOID fromString(final String s) {
			return PB01AOrgDivisionOID.forId(s);
		}
		public static PB01AOrgDivisionOID forId(final String id) {
			return new PB01AOrgDivisionOID(id);
		}
		public static PB01AOrgDivisionOID supply() {
			return PB01AOrgDivisionOID.forId(PB01AModelObjectOIDBase.supplyId());
		}
	}
	@Immutable
	@MarshallType(as="orgDivisionServiceOid")
	@NoArgsConstructor
	public static class PB01AOrgDivisionServiceOID
				extends PB01APersistableObjectOIDBase
			 implements PB01AOrgObjectOID {
		private static final long serialVersionUID = 7791733828828520678L;
		public PB01AOrgDivisionServiceOID(final String oid) {
			super(oid);
		}
		public static PB01AOrgDivisionServiceOID valueOf(final String s) {
			return PB01AOrgDivisionServiceOID.forId(s);
		}
		public static PB01AOrgDivisionServiceOID fromString(final String s) {
			return PB01AOrgDivisionServiceOID.forId(s);
		}
		public static PB01AOrgDivisionServiceOID forId(final String id) {
			return new PB01AOrgDivisionServiceOID(id);
		}
		public static PB01AOrgDivisionServiceOID supply() {
			return PB01AOrgDivisionServiceOID.forId(PB01AModelObjectOIDBase.supplyId());
		}
	}
	@Immutable
	@MarshallType(as="orgDivisionServiceLocationGroupOid")
	@NoArgsConstructor
	public static class PB01AOrgDivisionServiceLocationGroupOID
				extends PB01APersistableObjectOIDBase
			 implements PB01AOrgObjectOID {
		private static final long serialVersionUID = 4250337881848966224L;
		public PB01AOrgDivisionServiceLocationGroupOID(final String oid) {
			super(oid);
		}
		public static PB01AOrgDivisionServiceLocationGroupOID valueOf(final String s) {
			return PB01AOrgDivisionServiceLocationGroupOID.forId(s);
		}
		public static PB01AOrgDivisionServiceLocationGroupOID fromString(final String s) {
			return PB01AOrgDivisionServiceLocationGroupOID.forId(s);
		}
		public static PB01AOrgDivisionServiceLocationGroupOID forId(final String id) {
			return new PB01AOrgDivisionServiceLocationGroupOID(id);
		}
		public static PB01AOrgDivisionServiceLocationGroupOID supply() {
			return PB01AOrgDivisionServiceLocationGroupOID.forId(PB01AModelObjectOIDBase.supplyId());
		}
	}
	@Immutable
	@MarshallType(as="orgDivisionServiceLocationOid")
	@NoArgsConstructor
	public static class PB01AOrgDivisionServiceLocationOID
				extends PB01APersistableObjectOIDBase
			 implements PB01AOrgObjectOID {
		private static final long serialVersionUID = -4723392764569241773L;
		public PB01AOrgDivisionServiceLocationOID(final String oid) {
			super(oid);
		}
		public static PB01AOrgDivisionServiceLocationOID valueOf(final String s) {
			return PB01AOrgDivisionServiceLocationOID.forId(s);
		}
		public static PB01AOrgDivisionServiceLocationOID fromString(final String s) {
			return PB01AOrgDivisionServiceLocationOID.forId(s);
		}
		public static PB01AOrgDivisionServiceLocationOID forId(final String id) {
			return new PB01AOrgDivisionServiceLocationOID(id);
		}
		public static PB01AOrgDivisionServiceLocationOID supply() {
			return PB01AOrgDivisionServiceLocationOID.forId(PB01AModelObjectOIDBase.supplyId());
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
	public static class PB01AWorkPlaceOID
				extends PB01APersistableObjectOIDBase
			 implements PB01AOrgObjectOID {

		private static final long serialVersionUID = 3492537351039178420L;
		public PB01AWorkPlaceOID(final String oid) {
			super(oid);
		}
		public static PB01AWorkPlaceOID valueOf(final String s) {
			return PB01AWorkPlaceOID.forId(s);
		}
		public static PB01AWorkPlaceOID fromString(final String s) {
			return PB01AWorkPlaceOID.forId(s);
		}
		public static PB01AWorkPlaceOID forId(final String id) {
			return new PB01AWorkPlaceOID(id);
		}
		public static PB01AWorkPlaceOID supply() {
			return PB01AWorkPlaceOID.forId(PB01AModelObjectOIDBase.supplyId());
		}
	}
}
