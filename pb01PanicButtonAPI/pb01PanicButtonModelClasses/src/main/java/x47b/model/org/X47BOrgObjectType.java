package x47b.model.org;

import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.summaries.X47BSummarizedObject;
import x47b.model.org.summaries.X47BSummarizedOrgDivision;
import x47b.model.org.summaries.X47BSummarizedOrgDivisionService;
import x47b.model.org.summaries.X47BSummarizedOrgDivisionServiceLocation;
import x47b.model.org.summaries.X47BSummarizedOrganization;
import x47b.model.org.summaries.X47BSummarizedWorkPlace;
import x47b.model.search.X47BSearchResultItemForPanicButtonOrganizationalEntity;

public enum X47BOrgObjectType {
	ORGANIZATION,
	ORG_DIVISION,
	ORG_DIVISION_SERVICE,
	ORG_DIVISION_SERVICE_LOCATION,
	WORKPLACE;
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public static <O extends X47BPersistableObjectOID> X47BOrgObjectType ofOIDType(final Class<O> type) {
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
}
