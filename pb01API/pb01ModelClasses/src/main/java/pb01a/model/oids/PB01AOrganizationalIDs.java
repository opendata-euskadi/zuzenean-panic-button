package pb01a.model.oids;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import pb01a.model.oids.PB01AIDs.PB01AModelObjectIDBase;
import pb01a.model.oids.PB01AIDs.PB01APersistableObjectID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgObjectOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import r01f.annotations.Immutable;
import r01f.objectstreamer.annotations.MarshallType;

/**
 * Panic button service identifiers definitions.
 */
@RequiredArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class PB01AOrganizationalIDs {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public static interface PB01AOrgObjectID<O extends PB01AOrgObjectOID>
					extends PB01APersistableObjectID<O> {
		// nothing
	}
	public static abstract class PB01AOrgObjectIDBase<O extends PB01AOrgObjectOID>
						 extends PB01AModelObjectIDBase<O>
				      implements PB01AOrgObjectID<O> {

		private static final long serialVersionUID = -3628476292215645376L;

		public PB01AOrgObjectIDBase() {
			/* default no args constructor for serialization purposes */
		}
		public PB01AOrgObjectIDBase(final String id) {
			super(id);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  Source: Organization / Service / location / user
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * ID for a source organization
	 */
	@Immutable
	@MarshallType(as="organizationId")
	@NoArgsConstructor
	public static class PB01AOrganizationID
				extends PB01AOrgObjectIDBase<PB01AOrganizationOID> {
		private static final long serialVersionUID = -48738842255923897L;
		public PB01AOrganizationID(final String oid) {
			super(oid);
		}
		public static PB01AOrganizationID valueOf(final String s) {
			return PB01AOrganizationID.forId(s);
		}
		public static PB01AOrganizationID fromString(final String s) {
			return PB01AOrganizationID.forId(s);
		}
		public static PB01AOrganizationID forId(final String id) {
			return new PB01AOrganizationID(id);
		}
		public static PB01AOrganizationID EJGV = PB01AOrganizationID.forId("ejgv");
	}
	@Immutable
	@MarshallType(as="orgDivisionId")
	@NoArgsConstructor
	public static class PB01AOrgDivisionID
				extends PB01AOrgObjectIDBase<PB01AOrgDivisionOID> {

		private static final long serialVersionUID = -1981220256984650494L;
		public PB01AOrgDivisionID(final String oid) {
			super(oid);
		}
		public static PB01AOrgDivisionID valueOf(final String s) {
			return PB01AOrgDivisionID.forId(s);
		}
		public static PB01AOrgDivisionID fromString(final String s) {
			return PB01AOrgDivisionID.forId(s);
		}
		public static PB01AOrgDivisionID forId(final String id) {
			return new PB01AOrgDivisionID(id);
		}
	}
	/**
	 * ID for a source service
	 */
	@Immutable
	@MarshallType(as="orgDivisionServiceId")
	@NoArgsConstructor
	public static class PB01AOrgDivisionServiceID
				extends PB01AOrgObjectIDBase<PB01AOrgDivisionServiceOID> {
		private static final long serialVersionUID = 1122664293187620380L;
		public PB01AOrgDivisionServiceID(final String oid) {
			super(oid);
		}
		public static PB01AOrgDivisionServiceID valueOf(final String s) {
			return PB01AOrgDivisionServiceID.forId(s);
		}
		public static PB01AOrgDivisionServiceID fromString(final String s) {
			return PB01AOrgDivisionServiceID.forId(s);
		}
		public static PB01AOrgDivisionServiceID forId(final String id) {
			return new PB01AOrgDivisionServiceID(id);
		}
		public static PB01AOrgDivisionServiceID ZUZENEAN = PB01AOrgDivisionServiceID.forId("zuzenean");
	}
	/**
	 * ID for a source service location group
	 */
	@Immutable
	@MarshallType(as="orgServiceLocationGroupId")
	@NoArgsConstructor
	public static class PB01AOrganizationServiceLocationGroupID
				extends PB01AOrgObjectIDBase<PB01AOrgDivisionServiceLocationOID> {
		private static final long serialVersionUID = 1122664293187620380L;
		public PB01AOrganizationServiceLocationGroupID(final String oid) {
			super(oid);
		}
		public static PB01AOrganizationServiceLocationGroupID valueOf(final String s) {
			return PB01AOrganizationServiceLocationGroupID.forId(s);
		}
		public static PB01AOrganizationServiceLocationGroupID fromString(final String s) {
			return PB01AOrganizationServiceLocationGroupID.forId(s);
		}
		public static PB01AOrganizationServiceLocationGroupID forId(final String id) {
			return new PB01AOrganizationServiceLocationGroupID(id);
		}
		public static PB01AOrganizationServiceLocationGroupID ZUZENEAN_BIZKAIA = PB01AOrganizationServiceLocationGroupID.forId("zuzenean_bizkaia");
		public static PB01AOrganizationServiceLocationGroupID ZUZENEAN_ARABA = PB01AOrganizationServiceLocationGroupID.forId("zuzenean_araba");
		public static PB01AOrganizationServiceLocationGroupID ZUZENEAN_GIPUZKOA = PB01AOrganizationServiceLocationGroupID.forId("zuzenean_gipuzkoa");
	}
	/**
	 * ID for a source service location
	 */
	@Immutable
	@MarshallType(as="orgServiceLocationId")
	@NoArgsConstructor
	public static class PB01AOrgDivisionServiceLocationID
				extends PB01AOrgObjectIDBase<PB01AOrgDivisionServiceLocationOID> {
		private static final long serialVersionUID = 1122664293187620380L;
		public PB01AOrgDivisionServiceLocationID(final String oid) {
			super(oid);
		}
		public static PB01AOrgDivisionServiceLocationID valueOf(final String s) {
			return PB01AOrgDivisionServiceLocationID.forId(s);
		}
		public static PB01AOrgDivisionServiceLocationID fromString(final String s) {
			return PB01AOrgDivisionServiceLocationID.forId(s);
		}
		public static PB01AOrgDivisionServiceLocationID forId(final String id) {
			return new PB01AOrgDivisionServiceLocationID(id);
		}
		public static PB01AOrgDivisionServiceLocationID ZUZENEAN_BIZKAIA_BILBAO = PB01AOrgDivisionServiceLocationID.forId("zuzenean_bizkaia_bilbao");
		public static PB01AOrgDivisionServiceLocationID ZUZENEAN_ARABA_GASTEIZ = PB01AOrgDivisionServiceLocationID.forId("zuzenean_araba_gasteiz");
		public static PB01AOrgDivisionServiceLocationID ZUZENEAN_GIPUZKOA_DONOSTIA = PB01AOrgDivisionServiceLocationID.forId("zuzenean_gipuzkoa_donostia");
	}
	/**
	 * ID for an work place
	 */
	@Immutable
	@MarshallType(as="workPlaceId")
	@NoArgsConstructor
	public static class PB01AWorkPlaceID
				extends PB01AOrgObjectIDBase<PB01AWorkPlaceOID> {

		private static final long serialVersionUID = -6040904246600905030L;
		public PB01AWorkPlaceID(final String id) {
			super(id);
		}
		public static PB01AWorkPlaceID valueOf(final String s) {
			return PB01AWorkPlaceID.forId(s);
		}
		public static PB01AWorkPlaceID fromString(final String s) {
			return PB01AWorkPlaceID.forId(s);
		}
 		public static PB01AWorkPlaceID forId(final String id) {
			return new PB01AWorkPlaceID(id);
		}
	}
}
