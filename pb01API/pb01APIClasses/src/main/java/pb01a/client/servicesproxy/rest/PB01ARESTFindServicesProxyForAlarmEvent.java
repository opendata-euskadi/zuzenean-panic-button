package pb01a.client.servicesproxy.rest;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Singleton;

import pb01a.api.interfaces.PB01AFindServicesForAlarmEvent;
import pb01a.model.PB01AAlarmEvent;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01APanicButtonOIDs.PB01AAlarmEventOID;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.model.persistence.FindResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.client.servicesproxy.rest.RESTServicesForDBFindProxyBase;
import r01f.types.Range;
import r01f.types.contact.EMail;
import r01f.types.contact.Phone;
import r01f.types.url.Url;
import r01f.types.url.UrlQueryString;
import r01f.types.url.UrlQueryStringParam;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import r01f.xmlproperties.annotations.XMLPropertiesComponent;


@Singleton
public class PB01ARESTFindServicesProxyForAlarmEvent
	 extends RESTServicesForDBFindProxyBase<PB01AAlarmEventOID,PB01AAlarmEvent>
  implements PB01AFindServicesForAlarmEvent {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01ARESTFindServicesProxyForAlarmEvent(@XMLPropertiesComponent("client") final XMLPropertiesForAppComponent clientProps,
												  @ModelObjectsMarshaller 			final Marshaller marshaller) {
		super(marshaller,
			  PB01AAlarmEvent.class,
			  new PB01ARESTServiceResourceUrlPathBuilderForAlarmEvent(clientProps));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindResult<PB01AAlarmEvent> findBySourceId(final SecurityContext securityContext,
											   		 final PB01AOrganizationID id,
											   		 final Range<Date> dateRange) {
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(PB01ARESTServiceResourceUrlPathBuilderForAlarmEvent.class)
													   			  .pathOfAlarmsListBySourceId(id),
												 UrlQueryString.fromParams(new UrlQueryStringParam("dateRange",dateRange.asString())));
		return _findDelegate.doFindEntities(securityContext,
											restResourceUrl);
	}
	@Override
	public FindResult<PB01AAlarmEvent> findBySourceId(final SecurityContext securityContext,
											   		 final PB01AOrgDivisionID id,
											   		 final Range<Date> dateRange) {
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(PB01ARESTServiceResourceUrlPathBuilderForAlarmEvent.class)
													   			  .pathOfAlarmsListBySourceId(id),
												 UrlQueryString.fromParams(new UrlQueryStringParam("dateRange",dateRange.asString())));
		return _findDelegate.doFindEntities(securityContext,
											restResourceUrl);
	}
	@Override
	public FindResult<PB01AAlarmEvent> findBySourceId(final SecurityContext securityContext,
											   		 final PB01AOrgDivisionServiceID id,
											   		 final Range<Date> dateRange) {
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(PB01ARESTServiceResourceUrlPathBuilderForAlarmEvent.class)
													   			  .pathOfAlarmsListBySourceId(id),
												 UrlQueryString.fromParams(new UrlQueryStringParam("dateRange",dateRange.asString())));
		return _findDelegate.doFindEntities(securityContext,
											restResourceUrl);
	}
	@Override
	public FindResult<PB01AAlarmEvent> findBySourceId(final SecurityContext securityContext,
											   		 final PB01AOrgDivisionServiceLocationID id,
											   		 final Range<Date> dateRange) {
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(PB01ARESTServiceResourceUrlPathBuilderForAlarmEvent.class)
													   			  .pathOfAlarmsListBySourceId(id),
												 UrlQueryString.fromParams(new UrlQueryStringParam("dateRange",dateRange.asString())));
		return _findDelegate.doFindEntities(securityContext,
											restResourceUrl);
	}
	@Override
	public FindResult<PB01AAlarmEvent> findBySourceId(final SecurityContext securityContext,
											   		 final PB01AWorkPlaceID id,
											   		 final Range<Date> dateRange) {
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(PB01ARESTServiceResourceUrlPathBuilderForAlarmEvent.class)
													   			  .pathOfAlarmsListBySourceId(id),
												 UrlQueryString.fromParams(new UrlQueryStringParam("dateRange",dateRange.asString())));
		return _findDelegate.doFindEntities(securityContext,
											restResourceUrl);
	}
	@Override
	public FindResult<PB01AAlarmEvent> findByNotifiedPhone(final SecurityContext securityContext,
											   		 	  final Phone phone,
											   		 	  final Range<Date> dateRange) {
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(PB01ARESTServiceResourceUrlPathBuilderForAlarmEvent.class)
													   			  .pathOfAlarmsListByNotifiedPhone(phone),
												 UrlQueryString.fromParams(new UrlQueryStringParam("dateRange",dateRange.asString())));
		return _findDelegate.doFindEntities(securityContext,
											restResourceUrl);
	}
	@Override
	public FindResult<PB01AAlarmEvent> findByNotifiedEMail(final SecurityContext securityContext,
											   		 	  final EMail email,
											   		 	  final Range<Date> dateRange) {
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(PB01ARESTServiceResourceUrlPathBuilderForAlarmEvent.class)
													   			  .pathOfAlarmsListByNotifiedEMail(email),
												 UrlQueryString.fromParams(new UrlQueryStringParam("dateRange",dateRange.asString())));
		return _findDelegate.doFindEntities(securityContext,
											restResourceUrl);

	}
}
