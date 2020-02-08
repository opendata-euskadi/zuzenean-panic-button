package pb01c.server.rest;

import javax.inject.Inject;
import javax.ws.rs.ext.Provider;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class PB01CRESTRequestTypeMappers {

/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Provider
	public static class PB01CModelObjectRequestTypeMapper
		  	    extends ModelObjectRequestTypeMapperBase<ModelObject> {
		@Inject
		public PB01CModelObjectRequestTypeMapper(@ModelObjectsMarshaller final Marshaller marshaller) {
			super(marshaller);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Provider
	public static class PB01COIDRequestTypeMapper
		  	    extends OIDRequestTypeMapperBase<OID> {
		@Inject
		public PB01COIDRequestTypeMapper(@ModelObjectsMarshaller final Marshaller marshaller) {
			super(marshaller);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Provider
	public static class PB01CIndexManagementCommandRequestTypeMapper
		  	    extends IndexManagementCommandRequestTypeMapperBase {
		@Inject
		public PB01CIndexManagementCommandRequestTypeMapper(@ModelObjectsMarshaller final Marshaller marshaller) {
			super(marshaller);
		}
	}
	@Provider
	public static class PB01CEnqueuedJobRequestTypeMapper
		  	    extends EnqueuedJobRequestTypeMapperBase {
		@Inject
		public PB01CEnqueuedJobRequestTypeMapper(@ModelObjectsMarshaller final Marshaller marshaller) {
			super(marshaller);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	Ranges
// 	Jersey only scans at the locations defined under web.xml com.sun.jersey.config.property.packages
//  <init-param>
/////////////////////////////////////////////////////////////////////////////////////////
	@Provider
	public static class PB01CDateRangeRequestTypeMapper
	  		    extends DateRangeRequestTypeMapperBase {
		/* just extends */
	}
}
