package x47b.client.servicesproxy.rest;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Singleton;

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
import r01f.xmlproperties.XMLPropertiesComponent;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import x47b.api.interfaces.X47BFindServicesForAlarmEvent;
import x47b.model.X47BAlarmEvent;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BPanicButtonOIDs.X47BAlarmEventOID;


@Singleton
public class X47BRESTFindServicesProxyForAlarmEvent
	 extends RESTServicesForDBFindProxyBase<X47BAlarmEventOID,X47BAlarmEvent>
  implements X47BFindServicesForAlarmEvent {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BRESTFindServicesProxyForAlarmEvent(@XMLPropertiesComponent("client") final XMLPropertiesForAppComponent clientProps,
												  @ModelObjectsMarshaller 			final Marshaller marshaller) {
		super(marshaller,
			  X47BAlarmEvent.class,
			  new X47BRESTServiceResourceUrlPathBuilderForAlarmEvent(clientProps));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
											   		 final X47BOrganizationID id,
											   		 final Range<Date> dateRange) {
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(X47BRESTServiceResourceUrlPathBuilderForAlarmEvent.class)
													   			  .pathOfAlarmsListBySourceId(id),
												 UrlQueryString.fromParams(new UrlQueryStringParam("dateRange",dateRange.asString())));
		return _findDelegate.doFindEntities(securityContext,
											restResourceUrl);
	}
	@Override
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
											   		 final X47BOrgDivisionID id,
											   		 final Range<Date> dateRange) {
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(X47BRESTServiceResourceUrlPathBuilderForAlarmEvent.class)
													   			  .pathOfAlarmsListBySourceId(id),
												 UrlQueryString.fromParams(new UrlQueryStringParam("dateRange",dateRange.asString())));
		return _findDelegate.doFindEntities(securityContext,
											restResourceUrl);
	}
	@Override
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
											   		 final X47BOrgDivisionServiceID id,
											   		 final Range<Date> dateRange) {
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(X47BRESTServiceResourceUrlPathBuilderForAlarmEvent.class)
													   			  .pathOfAlarmsListBySourceId(id),
												 UrlQueryString.fromParams(new UrlQueryStringParam("dateRange",dateRange.asString())));
		return _findDelegate.doFindEntities(securityContext,
											restResourceUrl);
	}
	@Override
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
											   		 final X47BOrgDivisionServiceLocationID id,
											   		 final Range<Date> dateRange) {
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(X47BRESTServiceResourceUrlPathBuilderForAlarmEvent.class)
													   			  .pathOfAlarmsListBySourceId(id),
												 UrlQueryString.fromParams(new UrlQueryStringParam("dateRange",dateRange.asString())));
		return _findDelegate.doFindEntities(securityContext,
											restResourceUrl);
	}
	@Override
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
											   		 final X47BWorkPlaceID id,
											   		 final Range<Date> dateRange) {
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(X47BRESTServiceResourceUrlPathBuilderForAlarmEvent.class)
													   			  .pathOfAlarmsListBySourceId(id),
												 UrlQueryString.fromParams(new UrlQueryStringParam("dateRange",dateRange.asString())));
		return _findDelegate.doFindEntities(securityContext,
											restResourceUrl);
	}
	@Override
	public FindResult<X47BAlarmEvent> findByNotifiedPhone(final SecurityContext securityContext,
											   		 	  final Phone phone,
											   		 	  final Range<Date> dateRange) {
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(X47BRESTServiceResourceUrlPathBuilderForAlarmEvent.class)
													   			  .pathOfAlarmsListByNotifiedPhone(phone),
												 UrlQueryString.fromParams(new UrlQueryStringParam("dateRange",dateRange.asString())));
		return _findDelegate.doFindEntities(securityContext,
											restResourceUrl);
	}
	@Override
	public FindResult<X47BAlarmEvent> findByNotifiedEMail(final SecurityContext securityContext,
											   		 	  final EMail email,
											   		 	  final Range<Date> dateRange) {
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(X47BRESTServiceResourceUrlPathBuilderForAlarmEvent.class)
													   			  .pathOfAlarmsListByNotifiedEMail(email),
												 UrlQueryString.fromParams(new UrlQueryStringParam("dateRange",dateRange.asString())));
		return _findDelegate.doFindEntities(securityContext,
											restResourceUrl);

	}
}
