package x47b.server.rest;

import java.io.InputStream;
import java.io.OutputStream;

import javax.inject.Inject;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

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
 * Types in charge of convert the {@link Response} of a REST method form the business type returned from the method (ie {@link X47BStructureLabel})
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
public class X47BRESTResponseTypeMappers {
/////////////////////////////////////////////////////////////////////////////////////////
//	Boolean
// 	Jersey only scans at the locations defined under web.xml com.sun.jersey.config.property.packages
//  <init-param>
/////////////////////////////////////////////////////////////////////////////////////////
	@Provider
	public static class BooleanResponseTypeMapper
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
	public static class LongResponseTypeMapper
	  		    extends LongResponseTypeMapperBase {
		/* just extends */
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	Ranges
// 	Jersey only scans at the locations defined under web.xml com.sun.jersey.config.property.packages
//  <init-param>
/////////////////////////////////////////////////////////////////////////////////////////
	@Provider
	public static class RangeResponseTypeMapper
	  		    extends RangeResponseTypeMapperBase {
		/* just extends */
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	Collections & Maps
// 	Jersey only scans at the locations defined under web.xml com.sun.jersey.config.property.packages
//  <init-param>
/////////////////////////////////////////////////////////////////////////////////////////
	@Provider
	public static class CollectionResponseTypeMapper
			    extends CollectionResponseTypeMapperBase {
		@Inject
		public CollectionResponseTypeMapper(@ModelObjectsMarshaller final Marshaller marshaller) {
			super(marshaller);
		}
	}
	@Provider
	public static class MapResponseTypeMapper
			    extends MapResponseTypeMapperBase {
		@Inject
		public MapResponseTypeMapper(@ModelObjectsMarshaller final Marshaller marshaller) {
			super(marshaller);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  PersistenceOperationResult
/////////////////////////////////////////////////////////////////////////////////////////
	@Provider
	public static class X47BPersistenceOperationResultTypeMapper
			    extends PersistenceOperationResultTypeMapperBase {
		@Inject
		public X47BPersistenceOperationResultTypeMapper(@ModelObjectsMarshaller final Marshaller marshaller) {
			super(marshaller);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	X47BModelObject
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * MessageBodyWriter for all {@link ModelObject}s
	 */
	@Provider
	public static class X47BModelObjectResponseTypeMapper 
			    extends ModelObjectResponseTypeMapperBase<ModelObject> {
		@Inject
		public X47BModelObjectResponseTypeMapper(@ModelObjectsMarshaller final Marshaller marshaller) {
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
	public static class X47BSearchModelObjectResponseTypeMapper 
			    extends SearchModelObjectResponseTypeMapperBase {
		@Inject
		public X47BSearchModelObjectResponseTypeMapper(@ModelObjectsMarshaller final Marshaller marshaller) {
			super(marshaller);
		}
	}		
/////////////////////////////////////////////////////////////////////////////////////////
//	X47BOID
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * MessageBodyWriter for all {@link X47BOID}s
	 */
	@Provider
	public static class X47BOIDResponseTypeMapper 
		        extends OIDResponseTypeMapperBase {
		public X47BOIDResponseTypeMapper() {
			super();
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  PersistenceOperationResult & EnqueuedJob
/////////////////////////////////////////////////////////////////////////////////////////
	@Provider
	public static class X47BIndexManagementCommandResponseTypeMapper
			    extends IndexManagementCommandResponseTypeMapperBase {
		@Inject
		public X47BIndexManagementCommandResponseTypeMapper(@ModelObjectsMarshaller final Marshaller marshaller) {
			super(marshaller);
		}
	}
	@Provider
	public static class X47BEnqueuedJobdResponseTypeMapper
			    extends EnqueuedJobResponseTypeMapperBase {
		@Inject
		public X47BEnqueuedJobdResponseTypeMapper(@ModelObjectsMarshaller final Marshaller marshaller) {
			super(marshaller);
		}
	}
}
