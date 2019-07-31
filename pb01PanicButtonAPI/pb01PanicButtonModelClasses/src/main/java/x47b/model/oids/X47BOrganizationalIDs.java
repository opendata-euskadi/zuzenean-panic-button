package x47b.model.oids;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import r01f.annotations.Immutable;
import r01f.objectstreamer.annotations.MarshallType;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BIDs.X47BModelObjectIDBase;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;

/**
 * Panic button service identifiers definitions.
 */
@RequiredArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class X47BOrganizationalIDs {
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public static interface X47BOrganizationalID<O extends X47BPersistableObjectOID>
					extends X47BPersistableObjectID<O> {
		// nothing
	}
	public static abstract class X47BOrganizationalIDBase<O extends X47BPersistableObjectOID> 
						 extends X47BModelObjectIDBase<O> 
				      implements X47BOrganizationalID<O> {

		private static final long serialVersionUID = -3628476292215645376L;
		
		public X47BOrganizationalIDBase() {
			/* default no args constructor for serialization purposes */
		}
		public X47BOrganizationalIDBase(final String id) {
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
	public static class X47BOrganizationID
				extends X47BOrganizationalIDBase<X47BOrganizationOID> {
		private static final long serialVersionUID = -48738842255923897L;
		public X47BOrganizationID(final String oid) {
			super(oid);
		}
		public static X47BOrganizationID valueOf(final String s) {
			return X47BOrganizationID.forId(s);
		}
		public static X47BOrganizationID fromString(final String s) {
			return X47BOrganizationID.forId(s);
		}
		public static X47BOrganizationID forId(final String id) {
			return new X47BOrganizationID(id);
		}
		public static X47BOrganizationID EJGV = X47BOrganizationID.forId("ejgv");
	}
	@Immutable
	@MarshallType(as="orgDivisionId")
	@NoArgsConstructor
	public static class X47BOrgDivisionID
				extends X47BOrganizationalIDBase<X47BOrgDivisionOID> {

		private static final long serialVersionUID = -1981220256984650494L;
		public X47BOrgDivisionID(final String oid) {
			super(oid);
		}
		public static X47BOrgDivisionID valueOf(final String s) {
			return X47BOrgDivisionID.forId(s);
		}
		public static X47BOrgDivisionID fromString(final String s) {
			return X47BOrgDivisionID.forId(s);
		}
		public static X47BOrgDivisionID forId(final String id) {
			return new X47BOrgDivisionID(id);
		}
	}
	/**
	 * ID for a source service
	 */
	@Immutable
	@MarshallType(as="orgDivisionServiceId")
	@NoArgsConstructor
	public static class X47BOrgDivisionServiceID 
				extends X47BOrganizationalIDBase<X47BOrgDivisionServiceOID> {
		private static final long serialVersionUID = 1122664293187620380L;
		public X47BOrgDivisionServiceID(final String oid) {
			super(oid);
		}
		public static X47BOrgDivisionServiceID valueOf(final String s) {
			return X47BOrgDivisionServiceID.forId(s);
		}
		public static X47BOrgDivisionServiceID fromString(final String s) {
			return X47BOrgDivisionServiceID.forId(s);
		}
		public static X47BOrgDivisionServiceID forId(final String id) {
			return new X47BOrgDivisionServiceID(id);
		}
		public static X47BOrgDivisionServiceID ZUZENEAN = X47BOrgDivisionServiceID.forId("zuzenean");
	}
	/**
	 * ID for a source service location group
	 */
	@Immutable
	@MarshallType(as="orgServiceLocationGroupId")
	@NoArgsConstructor
	public static class X47BOrganizationServiceLocationGroupID
				extends X47BOrganizationalIDBase<X47BOrgDivisionServiceLocationOID> {
		private static final long serialVersionUID = 1122664293187620380L;
		public X47BOrganizationServiceLocationGroupID(final String oid) {
			super(oid);
		}
		public static X47BOrganizationServiceLocationGroupID valueOf(final String s) {
			return X47BOrganizationServiceLocationGroupID.forId(s);
		}
		public static X47BOrganizationServiceLocationGroupID fromString(final String s) {
			return X47BOrganizationServiceLocationGroupID.forId(s);
		}
		public static X47BOrganizationServiceLocationGroupID forId(final String id) {
			return new X47BOrganizationServiceLocationGroupID(id);
		}
		public static X47BOrganizationServiceLocationGroupID ZUZENEAN_BIZKAIA = X47BOrganizationServiceLocationGroupID.forId("zuzenean_bizkaia");
		public static X47BOrganizationServiceLocationGroupID ZUZENEAN_ARABA = X47BOrganizationServiceLocationGroupID.forId("zuzenean_araba");
		public static X47BOrganizationServiceLocationGroupID ZUZENEAN_GIPUZKOA = X47BOrganizationServiceLocationGroupID.forId("zuzenean_gipuzkoa");
	}
	/**
	 * ID for a source service location
	 */
	@Immutable
	@MarshallType(as="orgServiceLocationId")
	@NoArgsConstructor
	public static class X47BOrgDivisionServiceLocationID
				extends X47BOrganizationalIDBase<X47BOrgDivisionServiceLocationOID> {
		private static final long serialVersionUID = 1122664293187620380L;
		public X47BOrgDivisionServiceLocationID(final String oid) {
			super(oid);
		}
		public static X47BOrgDivisionServiceLocationID valueOf(final String s) {
			return X47BOrgDivisionServiceLocationID.forId(s);
		}
		public static X47BOrgDivisionServiceLocationID fromString(final String s) {
			return X47BOrgDivisionServiceLocationID.forId(s);
		}
		public static X47BOrgDivisionServiceLocationID forId(final String id) {
			return new X47BOrgDivisionServiceLocationID(id);
		}
		public static X47BOrgDivisionServiceLocationID ZUZENEAN_BIZKAIA_BILBAO = X47BOrgDivisionServiceLocationID.forId("zuzenean_bizkaia_bilbao");
		public static X47BOrgDivisionServiceLocationID ZUZENEAN_ARABA_GASTEIZ = X47BOrgDivisionServiceLocationID.forId("zuzenean_araba_gasteiz");
		public static X47BOrgDivisionServiceLocationID ZUZENEAN_GIPUZKOA_DONOSTIA = X47BOrgDivisionServiceLocationID.forId("zuzenean_gipuzkoa_donostia");
	}
	/**
	 * ID for an work place
	 */
	@Immutable
	@MarshallType(as="workPlaceId")
	@NoArgsConstructor
	public static class X47BWorkPlaceID
				extends X47BOrganizationalIDBase<X47BWorkPlaceOID> {

		private static final long serialVersionUID = -6040904246600905030L;
		public X47BWorkPlaceID(final String id) {
			super(id);
		}
		public static X47BWorkPlaceID valueOf(final String s) {
			return X47BWorkPlaceID.forId(s);
		}
		public static X47BWorkPlaceID fromString(final String s) {
			return X47BWorkPlaceID.forId(s);
		}
 		public static X47BWorkPlaceID forId(final String id) {
			return new X47BWorkPlaceID(id);
		}
	}
}
