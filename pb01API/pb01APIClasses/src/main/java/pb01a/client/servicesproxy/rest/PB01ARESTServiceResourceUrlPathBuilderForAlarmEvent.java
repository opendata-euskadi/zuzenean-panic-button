package pb01a.client.servicesproxy.rest;

import pb01a.client.servicesproxy.rest.PB01ARESTServiceResourceUrlPathBuildersBases.PB01ARESTServiceResourceUrlPathBuilderForPersistenceBase;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01APanicButtonOIDs.PB01AAlarmEventOID;
import r01f.services.client.servicesproxy.rest.RESTServiceResourceUrlPathBuilders.RESTServiceEndPointUrl;
import r01f.types.contact.EMail;
import r01f.types.contact.Phone;
import r01f.types.url.UrlPath;
import r01f.xmlproperties.XMLPropertiesForAppComponent;

  class PB01ARESTServiceResourceUrlPathBuilderForAlarmEvent
extends PB01ARESTServiceResourceUrlPathBuilderForPersistenceBase<PB01AAlarmEventOID> {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01ARESTServiceResourceUrlPathBuilderForAlarmEvent(final XMLPropertiesForAppComponent clientProps) {
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
	public UrlPath pathOfAlarmsListBySourceId(final PB01AOrganizationID id) {
		return this.pathOfEntityList().joinedWith("byOrganizationId",id);
	}
	public UrlPath pathOfAlarmsListBySourceId(final PB01AOrgDivisionID id) {
		return this.pathOfEntityList().joinedWith("byDivisionId",id);
	}
	public UrlPath pathOfAlarmsListBySourceId(final PB01AOrgDivisionServiceID id) {
		return this.pathOfEntityList().joinedWith("byServiceId",id);
	}
	public UrlPath pathOfAlarmsListBySourceId(final PB01AOrgDivisionServiceLocationID id) {
		return this.pathOfEntityList().joinedWith("byLocationId",id);
	}
	public UrlPath pathOfAlarmsListBySourceId(final PB01AWorkPlaceID id) {
		return this.pathOfEntityList().joinedWith("byWorkPlaceId",id);
	}
	public UrlPath pathOfAlarmsListByNotifiedPhone(final Phone phone) {
		return this.pathOfEntityList().joinedWith("byNotifiedPhone",phone);
	}
	public UrlPath pathOfAlarmsListByNotifiedEMail(final EMail email) {
		return this.pathOfEntityList().joinedWith("byNotifiedEMail",email);	// TODO: encode the @
	}
}