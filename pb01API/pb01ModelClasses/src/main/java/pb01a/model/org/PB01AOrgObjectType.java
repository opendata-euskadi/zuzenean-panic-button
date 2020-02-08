package pb01a.model.org;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgObjectID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgObjectOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.org.summaries.PB01ASummarizedObject;
import pb01a.model.org.summaries.PB01ASummarizedOrgDivision;
import pb01a.model.org.summaries.PB01ASummarizedOrgDivisionService;
import pb01a.model.org.summaries.PB01ASummarizedOrgDivisionServiceLocation;
import pb01a.model.org.summaries.PB01ASummarizedOrganization;
import pb01a.model.org.summaries.PB01ASummarizedWorkPlace;
import pb01a.model.search.PB01ASearchResultItemForPanicButtonOrganizationalEntity;
import r01f.patterns.FactoryFrom;

@Accessors(prefix="_")
@RequiredArgsConstructor
public enum PB01AOrgObjectType {
	ORGANIZATION(0),
	ORG_DIVISION(1),
	ORG_DIVISION_SERVICE(2),
	ORG_DIVISION_SERVICE_LOCATION(3),
	WORKPLACE(4);

	@Getter private final int _orgHierarchyLevel;

/////////////////////////////////////////////////////////////////////////////////////////
//	FROM OID & ID TYPES
/////////////////////////////////////////////////////////////////////////////////////////
	public static <O extends PB01AOrgObjectOID> PB01AOrgObjectType ofOIDType(final Class<O> type) {
		PB01AOrgObjectType outType = null;
		if (type == PB01AOrganizationOID.class) {
			outType = ORGANIZATION;
		} else if (type == PB01AOrgDivisionOID.class) {
			outType = ORG_DIVISION;
		} else if (type == PB01AOrgDivisionServiceOID.class) {
			outType = ORG_DIVISION_SERVICE;
		} else if (type == PB01AOrgDivisionServiceLocationOID.class) {
			outType = ORG_DIVISION_SERVICE_LOCATION;
		} else if (type == PB01AWorkPlaceOID.class) {
			outType = WORKPLACE;
		} else {
			throw new IllegalArgumentException(type + " is NOT a valid organizational object oid type!");
		}
		return outType;
	}
	public static <I extends PB01AOrgObjectID<?>> PB01AOrgObjectType ofIDType(final Class<I> type) {
		PB01AOrgObjectType outType = null;
		if (type == PB01AOrganizationID.class) {
			outType = ORGANIZATION;
		} else if (type == PB01AOrgDivisionID.class) {
			outType = ORG_DIVISION;
		} else if (type == PB01AOrgDivisionServiceID.class) {
			outType = ORG_DIVISION_SERVICE;
		} else if (type == PB01AOrgDivisionServiceLocationID.class) {
			outType = ORG_DIVISION_SERVICE_LOCATION;
		} else if (type == PB01AWorkPlaceID.class) {
			outType = WORKPLACE;
		} else {
			throw new IllegalArgumentException(type + " is NOT a valid organizational object id type!");
		}
		return outType;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	FROM SUMARIZED TYPES
/////////////////////////////////////////////////////////////////////////////////////////
	public static <T extends PB01AOrganizationalPersistableObject<?,?>> PB01AOrgObjectType ofType(final Class<T> type) {
		PB01AOrgObjectType outType = null;
		if (type == PB01AOrganization.class) {
			outType = ORGANIZATION;
		} else if (type == PB01AOrgDivision.class) {
			outType = ORG_DIVISION;
		} else if (type == PB01AOrgDivisionService.class) {
			outType = ORG_DIVISION_SERVICE;
		} else if (type == PB01AOrgDivisionServiceLocation.class) {
			outType = ORG_DIVISION_SERVICE_LOCATION;
		} else if (type == PB01AWorkPlace.class) {
			outType = WORKPLACE;
		} else {
			throw new IllegalArgumentException(type + " is NOT a valid organizational object type!");
		}
		return outType;
	}
	public static <T extends PB01ASummarizedObject<?,?,?>> PB01AOrgObjectType ofSummarizedType(final Class<T> type) {
		PB01AOrgObjectType outType = null;
		if (type == PB01ASummarizedOrganization.class) {
			outType = ORGANIZATION;
		} else if (type == PB01ASummarizedOrgDivision.class) {
			outType = ORG_DIVISION;
		} else if (type == PB01ASummarizedOrgDivisionService.class) {
			outType = ORG_DIVISION_SERVICE;
		} else if (type == PB01ASummarizedOrgDivisionServiceLocation.class) {
			outType = ORG_DIVISION_SERVICE_LOCATION;
		} else if (type == PB01ASummarizedWorkPlace.class) {
			outType = WORKPLACE;
		} else {
			throw new IllegalArgumentException(type + " is NOT a valid organizational summarized object type!");
		}
		return outType;
	}
	public static PB01AOrgObjectType of(final PB01ASearchResultItemForPanicButtonOrganizationalEntity searchResultItem) {
		PB01AOrgObjectType outType = null;
		if (searchResultItem.getWorkPlace() != null) {
			outType = WORKPLACE;
		} else if (searchResultItem.getOrgDivisionServiceLocation() != null) {
			outType = ORG_DIVISION_SERVICE_LOCATION;
		} else if (searchResultItem.getOrgDivisionService() != null) {
			outType = ORG_DIVISION_SERVICE;
		} else if (searchResultItem.getOrgDivision() != null) {
			outType = ORG_DIVISION;
		} else if (searchResultItem.getOrganization() != null) {
			outType = ORGANIZATION;
		} else {
			throw new IllegalArgumentException("Could NOT guess the type of a search result item instance");
		}
		return outType;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	oid & id factories from string
/////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	public static <O extends PB01AOrgObjectOID> FactoryFrom<String,O> factoryForOIDType(final Class<O> type) {
		FactoryFrom<String,O> outFactory = null;
		if (type == PB01AOrganizationOID.class) {
			outFactory = id -> (O)PB01AOrganizationOID.forId(id);
		} else if (type == PB01AOrgDivisionOID.class) {
			outFactory = id -> (O)PB01AOrgDivisionOID.forId(id);
		} else if (type == PB01AOrgDivisionServiceOID.class) {
			outFactory = id -> (O)PB01AOrgDivisionServiceOID.forId(id);
		} else if (type == PB01AOrgDivisionServiceLocationOID.class) {
			outFactory = id -> (O)PB01AOrgDivisionServiceLocationOID.forId(id);
		} else if (type == PB01AWorkPlaceOID.class) {
			outFactory = id -> (O)PB01AWorkPlaceOID.forId(id);
		} else {
			throw new IllegalArgumentException(type + " is NOT a valid organizational object oid type!");
		}
		return outFactory;
	}
	@SuppressWarnings("unchecked")
	public static <I extends PB01AOrgObjectID<?>> FactoryFrom<String,I> factoryForIDType(final Class<I> type) {
		FactoryFrom<String,I> outFactory = null;
		if (type == PB01AOrganizationID.class) {
			outFactory = id -> (I)PB01AOrganizationID.forId(id);
		} else if (type == PB01AOrgDivisionID.class) {
			outFactory = id -> (I)PB01AOrgDivisionID.forId(id);
		} else if (type == PB01AOrgDivisionServiceID.class) {
			outFactory = id -> (I)PB01AOrgDivisionServiceID.forId(id);
		} else if (type == PB01AOrgDivisionServiceLocationID.class) {
			outFactory = id -> (I)PB01AOrgDivisionServiceLocationID.forId(id);
		} else if (type == PB01AWorkPlaceID.class) {
			outFactory = id -> (I)PB01AWorkPlaceID.forId(id);
		} else {
			throw new IllegalArgumentException(type + " is NOT a valid organizational object id type!");
		}
		return outFactory;
	}
}
