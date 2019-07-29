package x47b.client.servicesproxy.rest;

import r01f.services.client.servicesproxy.rest.RESTServiceResourceUrlPathBuilders.RESTServiceEndPointUrl;
import r01f.types.url.UrlPath;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import x47b.client.servicesproxy.rest.X47BRESTServiceResourceUrlPathBuildersBases.X47BRESTServiceResourceUrlPathBuilderForPersistenceBase;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BPanicButtonOIDs.X47BAlarmEventOID;

  class X47BRESTServiceResourceUrlPathBuilderForAlarmEvent
extends X47BRESTServiceResourceUrlPathBuilderForPersistenceBase<X47BAlarmEventOID> {
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BRESTServiceResourceUrlPathBuilderForAlarmEvent(final XMLPropertiesForAppComponent clientProps) {
		super(new RESTServiceEndPointUrl(clientProps,
										 "panicButton"),
			  UrlPath.from("alarmEvents"));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  CREATING
/////////////////////////////////////////////////////////////////////////////////////////
	public UrlPath pathOfAlarmsBySourceId() {
		return this.pathOfAllEntities().joinedWith("bySourceId");
	}
	public UrlPath pathOfAlarmsBySourceOid() {
		return this.pathOfAllEntities().joinedWith("bySourceOid");
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  LISTING
/////////////////////////////////////////////////////////////////////////////////////////
	public UrlPath pathOfAlarmsListBySourceId(final X47BOrganizationID id) {
		return this.pathOfEntityList().joinedWith("byOrganizationId",id);	
	}
	public UrlPath pathOfAlarmsListBySourceId(final X47BOrgDivisionID id) {
		return this.pathOfEntityList().joinedWith("byDivisionId",id);	
	}
	public UrlPath pathOfAlarmsListBySourceId(final X47BOrgDivisionServiceID id) {
		return this.pathOfEntityList().joinedWith("byServiceId",id);	
	}
	public UrlPath pathOfAlarmsListBySourceId(final X47BOrgDivisionServiceLocationID id) {
		return this.pathOfEntityList().joinedWith("byLocationId",id);	
	}	
	public UrlPath pathOfAlarmsListBySourceId(final X47BWorkPlaceID id) {
		return this.pathOfEntityList().joinedWith("byWorkPlaceId",id);	
	}
}