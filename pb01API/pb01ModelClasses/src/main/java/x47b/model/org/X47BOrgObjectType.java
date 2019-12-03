package x47b.model.org;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import r01f.patterns.FactoryFrom;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgObjectID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgObjectOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.summaries.X47BSummarizedObject;
import x47b.model.org.summaries.X47BSummarizedOrgDivision;
import x47b.model.org.summaries.X47BSummarizedOrgDivisionService;
import x47b.model.org.summaries.X47BSummarizedOrgDivisionServiceLocation;
import x47b.model.org.summaries.X47BSummarizedOrganization;
import x47b.model.org.summaries.X47BSummarizedWorkPlace;
import x47b.model.search.X47BSearchResultItemForPanicButtonOrganizationalEntity;

@Accessors(prefix="_")
@RequiredArgsConstructor
public enum X47BOrgObjectType {
	ORGANIZATION(0),
	ORG_DIVISION(1),
	ORG_DIVISION_SERVICE(2),
	ORG_DIVISION_SERVICE_LOCATION(3),
	WORKPLACE(4);

	@Getter private final int _orgHierarchyLevel;

/////////////////////////////////////////////////////////////////////////////////////////
//	FROM OID & ID TYPES
/////////////////////////////////////////////////////////////////////////////////////////
	public static <O extends X47BOrgObjectOID> X47BOrgObjectType ofOIDType(final Class<O> type) {
		X47BOrgObjectType outType = null;
		if (type == X47BOrganizationOID.class) {
			outType = ORGANIZATION;
		} else if (type == X47BOrgDivisionOID.class) {
			outType = ORG_DIVISION;
		} else if (type == X47BOrgDivisionServiceOID.class) {
			outType = ORG_DIVISION_SERVICE;
		} else if (type == X47BOrgDivisionServiceLocationOID.class) {
			outType = ORG_DIVISION_SERVICE_LOCATION;
		} else if (type == X47BWorkPlaceOID.class) {
			outType = WORKPLACE;
		} else {
			throw new IllegalArgumentException(type + " is NOT a valid organizational object oid type!");
		}
		return outType;
	}
	public static <I extends X47BOrgObjectID<?>> X47BOrgObjectType ofIDType(final Class<I> type) {
		X47BOrgObjectType outType = null;
		if (type == X47BOrganizationID.class) {
			outType = ORGANIZATION;
		} else if (type == X47BOrgDivisionID.class) {
			outType = ORG_DIVISION;
		} else if (type == X47BOrgDivisionServiceID.class) {
			outType = ORG_DIVISION_SERVICE;
		} else if (type == X47BOrgDivisionServiceLocationID.class) {
			outType = ORG_DIVISION_SERVICE_LOCATION;
		} else if (type == X47BWorkPlaceID.class) {
			outType = WORKPLACE;
		} else {
			throw new IllegalArgumentException(type + " is NOT a valid organizational object id type!");
		}
		return outType;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	FROM SUMARIZED TYPES
/////////////////////////////////////////////////////////////////////////////////////////
	public static <T extends X47BOrganizationalPersistableObject<?,?>> X47BOrgObjectType ofType(final Class<T> type) {
		X47BOrgObjectType outType = null;
		if (type == X47BOrganization.class) {
			outType = ORGANIZATION;
		} else if (type == X47BOrgDivision.class) {
			outType = ORG_DIVISION;
		} else if (type == X47BOrgDivisionService.class) {
			outType = ORG_DIVISION_SERVICE;
		} else if (type == X47BOrgDivisionServiceLocation.class) {
			outType = ORG_DIVISION_SERVICE_LOCATION;
		} else if (type == X47BWorkPlace.class) {
			outType = WORKPLACE;
		} else {
			throw new IllegalArgumentException(type + " is NOT a valid organizational object type!");
		}
		return outType;
	}
	public static <T extends X47BSummarizedObject<?,?,?>> X47BOrgObjectType ofSummarizedType(final Class<T> type) {
		X47BOrgObjectType outType = null;
		if (type == X47BSummarizedOrganization.class) {
			outType = ORGANIZATION;
		} else if (type == X47BSummarizedOrgDivision.class) {
			outType = ORG_DIVISION;
		} else if (type == X47BSummarizedOrgDivisionService.class) {
			outType = ORG_DIVISION_SERVICE;
		} else if (type == X47BSummarizedOrgDivisionServiceLocation.class) {
			outType = ORG_DIVISION_SERVICE_LOCATION;
		} else if (type == X47BSummarizedWorkPlace.class) {
			outType = WORKPLACE;
		} else {
			throw new IllegalArgumentException(type + " is NOT a valid organizational summarized object type!");
		}
		return outType;
	}
	public static X47BOrgObjectType of(final X47BSearchResultItemForPanicButtonOrganizationalEntity searchResultItem) {
		X47BOrgObjectType outType = null;
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
	public static <O extends X47BOrgObjectOID> FactoryFrom<String,O> factoryForOIDType(final Class<O> type) {
		FactoryFrom<String,O> outFactory = null;
		if (type == X47BOrganizationOID.class) {
			outFactory = id -> (O)X47BOrganizationOID.forId(id);
		} else if (type == X47BOrgDivisionOID.class) {
			outFactory = id -> (O)X47BOrgDivisionOID.forId(id);
		} else if (type == X47BOrgDivisionServiceOID.class) {
			outFactory = id -> (O)X47BOrgDivisionServiceOID.forId(id);
		} else if (type == X47BOrgDivisionServiceLocationOID.class) {
			outFactory = id -> (O)X47BOrgDivisionServiceLocationOID.forId(id);
		} else if (type == X47BWorkPlaceOID.class) {
			outFactory = id -> (O)X47BWorkPlaceOID.forId(id);
		} else {
			throw new IllegalArgumentException(type + " is NOT a valid organizational object oid type!");
		}
		return outFactory;
	}
	@SuppressWarnings("unchecked")
	public static <I extends X47BOrgObjectID<?>> FactoryFrom<String,I> factoryForIDType(final Class<I> type) {
		FactoryFrom<String,I> outFactory = null;
		if (type == X47BOrganizationID.class) {
			outFactory = id -> (I)X47BOrganizationID.forId(id);
		} else if (type == X47BOrgDivisionID.class) {
			outFactory = id -> (I)X47BOrgDivisionID.forId(id);
		} else if (type == X47BOrgDivisionServiceID.class) {
			outFactory = id -> (I)X47BOrgDivisionServiceID.forId(id);
		} else if (type == X47BOrgDivisionServiceLocationID.class) {
			outFactory = id -> (I)X47BOrgDivisionServiceLocationID.forId(id);
		} else if (type == X47BWorkPlaceID.class) {
			outFactory = id -> (I)X47BWorkPlaceID.forId(id);
		} else {
			throw new IllegalArgumentException(type + " is NOT a valid organizational object id type!");
		}
		return outFactory;
	}
}
