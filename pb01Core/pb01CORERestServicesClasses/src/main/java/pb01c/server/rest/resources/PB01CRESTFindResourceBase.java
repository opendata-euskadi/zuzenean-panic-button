package pb01c.server.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pb01a.api.context.PB01ASecurityContext;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import r01f.guids.CommonOIDs.UserCode;
import r01f.model.PersistableModelObject;
import r01f.rest.RESTResourceBase;
import r01f.rest.resources.delegates.RESTDelegate;
import r01f.rest.resources.delegates.RESTFindDelegateBase;
import r01f.util.types.Ranges.DateRange;

/**
 * This base type MUST be public... otherwise jersey throws a null exception WTF!
 * @param <O>
 * @param <M>
 */
public abstract class PB01CRESTFindResourceBase<O extends PB01APersistableObjectOID,M extends PersistableModelObject<O>>
		      extends RESTResourceBase {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CRESTFindResourceBase(final RESTDelegate persistenceDelegate) {
		super(persistenceDelegate);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  FIND
/////////////////////////////////////////////////////////////////////////////////////////
	@GET 
	@Produces(MediaType.APPLICATION_XML)
	public Response findAll(@HeaderParam("securityContext") 	final PB01ASecurityContext securityContext) {
		return this.getDelegateAs(RESTFindDelegateBase.class)
						.findAll(securityContext,_req.getPathInfo());
	}
	@GET @Path("byCreateDate/{dateRange}") 
	@Produces(MediaType.APPLICATION_XML)
	@SuppressWarnings("unchecked")
	public Response findByCreateDate(@HeaderParam("securityContext") 	final PB01ASecurityContext securityContext,
									 @PathParam("dateRange")		final DateRange createDate) {
		return this.getDelegateAs(RESTFindDelegateBase.class)
						.findByCreateDate(securityContext,_req.getPathInfo(),
										  createDate.getRange());
	}
	@GET @Path("byLastUpdateDate/{dateRange}") 
	@Produces(MediaType.APPLICATION_XML)
	@SuppressWarnings("unchecked")
	public Response findByLastUpdateDate(@HeaderParam("securityContext") 	final PB01ASecurityContext securityContext,
										 @PathParam("dateRange")  		final DateRange lastUpdateDate) {
		return this.getDelegateAs(RESTFindDelegateBase.class)
						.findByLastUpdateDate(securityContext,_req.getPathInfo(),
										  	  lastUpdateDate.getRange());
	}
	@GET @Path("byCreator/{userCode}")
	@Produces(MediaType.APPLICATION_XML)
	public Response findByCreator(@HeaderParam("securityContext") 	final PB01ASecurityContext securityContext,
								  @PathParam("userCode")		final UserCode creatorUserCode) {
		return this.getDelegateAs(RESTFindDelegateBase.class)
						.findByCreator(securityContext,_req.getPathInfo(),
									   creatorUserCode);
	}
	@GET @Path("byLastUpdator/{userCode}") 
	@Produces(MediaType.APPLICATION_XML)
	public Response findByLastUpdator(@HeaderParam("securityContext") 	final PB01ASecurityContext securityContext,
									  @PathParam("userCode")		final UserCode lastUpdatorUserCode) {
		return this.getDelegateAs(RESTFindDelegateBase.class)
						.findByLastUpdator(securityContext,_req.getPathInfo(),
									   	   lastUpdatorUserCode);
	}
}
