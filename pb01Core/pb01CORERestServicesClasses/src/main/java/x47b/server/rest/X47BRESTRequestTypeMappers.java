package x47b.server.rest;

import javax.inject.Inject;
import javax.ws.rs.ext.Provider;

import r01f.guids.OID;
import r01f.model.ModelObject;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.objectstreamer.Marshaller;
import r01f.rest.RESTRequestTypeMappersForBasicTypes.DateRangeRequestTypeMapperBase;
import r01f.rest.RESTRequestTypeMappersForModelObjects.EnqueuedJobRequestTypeMapperBase;
import r01f.rest.RESTRequestTypeMappersForModelObjects.IndexManagementCommandRequestTypeMapperBase;
import r01f.rest.RESTRequestTypeMappersForModelObjects.ModelObjectRequestTypeMapperBase;
import r01f.rest.RESTRequestTypeMappersForModelObjects.OIDRequestTypeMapperBase;

/**
 * Type mappers for user types
 */
public class X47BRESTRequestTypeMappers {

/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Provider
	public static class X47BModelObjectRequestTypeMapper 
		  	    extends ModelObjectRequestTypeMapperBase<ModelObject> {
		@Inject
		public X47BModelObjectRequestTypeMapper(@ModelObjectsMarshaller final Marshaller marshaller) {
			super(marshaller);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	
/////////////////////////////////////////////////////////////////////////////////////////
	@Provider
	public static class X47BOIDRequestTypeMapper 
		  	    extends OIDRequestTypeMapperBase<OID> {
		@Inject
		public X47BOIDRequestTypeMapper(@ModelObjectsMarshaller final Marshaller marshaller) {
			super(marshaller);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Provider
	public static class X47BIndexManagementCommandRequestTypeMapper 
		  	    extends IndexManagementCommandRequestTypeMapperBase {
		@Inject
		public X47BIndexManagementCommandRequestTypeMapper(@ModelObjectsMarshaller final Marshaller marshaller) {
			super(marshaller);
		}
	}
	@Provider
	public static class X47BEnqueuedJobRequestTypeMapper 
		  	    extends EnqueuedJobRequestTypeMapperBase {
		@Inject
		public X47BEnqueuedJobRequestTypeMapper(@ModelObjectsMarshaller final Marshaller marshaller) {
			super(marshaller);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	Ranges
// 	Jersey only scans at the locations defined under web.xml com.sun.jersey.config.property.packages
//  <init-param>
/////////////////////////////////////////////////////////////////////////////////////////
	@Provider
	public static class DateRangeRequestTypeMapper
	  		    extends DateRangeRequestTypeMapperBase {
		/* just extends */
	}
}
