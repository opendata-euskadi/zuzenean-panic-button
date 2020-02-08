package pb01c.server.rest;

import java.io.InputStream;
import java.io.OutputStream;

import javax.inject.Inject;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import r01f.model.ModelObject;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.model.search.SearchModelObject;
import r01f.objectstreamer.Marshaller;
import r01f.rest.RESTResponseTypeMappersForBasicTypes.BooleanResponseTypeMapperBase;
import r01f.rest.RESTResponseTypeMappersForBasicTypes.CollectionResponseTypeMapperBase;
import r01f.rest.RESTResponseTypeMappersForBasicTypes.DateResponseTypeMapperBase;
import r01f.rest.RESTResponseTypeMappersForBasicTypes.LongResponseTypeMapperBase;
import r01f.rest.RESTResponseTypeMappersForBasicTypes.MapResponseTypeMapperBase;
import r01f.rest.RESTResponseTypeMappersForBasicTypes.RangeResponseTypeMapperBase;
import r01f.rest.RESTResponseTypeMappersForModelObjects.EnqueuedJobResponseTypeMapperBase;
import r01f.rest.RESTResponseTypeMappersForModelObjects.IndexManagementCommandResponseTypeMapperBase;
import r01f.rest.RESTResponseTypeMappersForModelObjects.ModelObjectResponseTypeMapperBase;
import r01f.rest.RESTResponseTypeMappersForModelObjects.OIDResponseTypeMapperBase;
import r01f.rest.RESTResponseTypeMappersForModelObjects.PersistenceOperationResultTypeMapperBase;
import r01f.rest.RESTResponseTypeMappersForModelObjects.SearchModelObjectResponseTypeMapperBase;

/**
 * Types in charge of convert the {@link Response} of a REST method form the business type returned from the method
 * to the bytes returned by the servlet in the {@link OutputStream}
 * ie: if inside a REST module exists a method like
 * <pre class='brush:java'>
 * 		@DELETE @Path("record/{id}")
 *		@Produces(application/xml)
 *		public Record deleteRecord(@PathParam("id") final String id)  {
 *			....
 *		}
 * </pre>
 * In order to return in the OutputStream an instanceof Record a serialization to bytes of this java object must be done
 * This kind of serialization is done at the type-mappers which implements the {@link MessageBodyWriter} or {@link MessageBodyReader}
 * interfaces, wether it:
 * <ul>
 * 		<li>serializes the method return type TO the {@link Response} {@link OutputStream}</li>
 * 		<li>... or serializes a method param FROM the {@link Request} {@link InputStream}</li>
 * </ul>
 */
@NoArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class PB01CRESTResponseTypeMappers {
/////////////////////////////////////////////////////////////////////////////////////////
//	Boolean
// 	Jersey only scans at the locations defined under web.xml com.sun.jersey.config.property.packages
//  <init-param>
/////////////////////////////////////////////////////////////////////////////////////////
	@Provider
	public static class PB01CBooleanResponseTypeMapper
	  		    extends BooleanResponseTypeMapperBase {
		/* just extends */
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	Dates
// 	Jersey only scans at the locations defined under web.xml com.sun.jersey.config.property.packages
//  <init-param>
/////////////////////////////////////////////////////////////////////////////////////////
	@Provider
	public static class DateResponseTypeMapper
	  		    extends DateResponseTypeMapperBase {
		/* just extends */
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	Long
// 	Jersey only scans at the locations defined under web.xml com.sun.jersey.config.property.packages
//  <init-param>
/////////////////////////////////////////////////////////////////////////////////////////
	@Provider
	public static class PB01CLongResponseTypeMapper
	  		    extends LongResponseTypeMapperBase {
		/* just extends */
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	Ranges
// 	Jersey only scans at the locations defined under web.xml com.sun.jersey.config.property.packages
//  <init-param>
/////////////////////////////////////////////////////////////////////////////////////////
	@Provider
	public static class PB01CRangeResponseTypeMapper
	  		    extends RangeResponseTypeMapperBase {
		/* just extends */
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	Collections & Maps
// 	Jersey only scans at the locations defined under web.xml com.sun.jersey.config.property.packages
//  <init-param>
/////////////////////////////////////////////////////////////////////////////////////////
	@Provider
	public static class PB01CCollectionResponseTypeMapper
			    extends CollectionResponseTypeMapperBase {
		@Inject
		public PB01CCollectionResponseTypeMapper(@ModelObjectsMarshaller final Marshaller marshaller) {
			super(marshaller);
		}
	}
	@Provider
	public static class PB01CMapResponseTypeMapper
			    extends MapResponseTypeMapperBase {
		@Inject
		public PB01CMapResponseTypeMapper(@ModelObjectsMarshaller final Marshaller marshaller) {
			super(marshaller);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  PersistenceOperationResult
/////////////////////////////////////////////////////////////////////////////////////////
	@Provider
	public static class PB01CPersistenceOperationResultTypeMapper
			    extends PersistenceOperationResultTypeMapperBase {
		@Inject
		public PB01CPersistenceOperationResultTypeMapper(@ModelObjectsMarshaller final Marshaller marshaller) {
			super(marshaller);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	PB01AModelObject
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * MessageBodyWriter for all {@link ModelObject}s
	 */
	@Provider
	public static class PB01CModelObjectResponseTypeMapper
			    extends ModelObjectResponseTypeMapperBase<ModelObject> {
		@Inject
		public PB01CModelObjectResponseTypeMapper(@ModelObjectsMarshaller final Marshaller marshaller) {
			super(marshaller);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	SearchModelObject
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * MessageBodyWriter for all {@link SearchModelObject}s
	 */
	@Provider
	public static class PB01CSearchModelObjectResponseTypeMapper
			    extends SearchModelObjectResponseTypeMapperBase {
		@Inject
		public PB01CSearchModelObjectResponseTypeMapper(@ModelObjectsMarshaller final Marshaller marshaller) {
			super(marshaller);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	PB01AOID
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * MessageBodyWriter for all {@link PB01AOID}s
	 */
	@Provider
	public static class PB01COIDResponseTypeMapper
		        extends OIDResponseTypeMapperBase {
		public PB01COIDResponseTypeMapper() {
			super();
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  PersistenceOperationResult & EnqueuedJob
/////////////////////////////////////////////////////////////////////////////////////////
	@Provider
	public static class PB01CIndexManagementCommandResponseTypeMapper
			    extends IndexManagementCommandResponseTypeMapperBase {
		@Inject
		public PB01CIndexManagementCommandResponseTypeMapper(@ModelObjectsMarshaller final Marshaller marshaller) {
			super(marshaller);
		}
	}
	@Provider
	public static class PB01CEnqueuedJobdResponseTypeMapper
			    extends EnqueuedJobResponseTypeMapperBase {
		@Inject
		public PB01CEnqueuedJobdResponseTypeMapper(@ModelObjectsMarshaller final Marshaller marshaller) {
			super(marshaller);
		}
	}
}
