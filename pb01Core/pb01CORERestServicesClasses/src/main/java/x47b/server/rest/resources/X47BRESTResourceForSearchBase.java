package x47b.server.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import r01f.model.search.SearchFilter;
import r01f.model.search.SearchResultItem;
import r01f.rest.RESTResourceBase;
import r01f.rest.resources.delegates.RESTSearchDelegateBase;
import x47b.api.context.X47BSecurityContext;

/**
 * This base type MUST be public... otherwise jersey throws a null exception WTF!
 * @param <O>
 * @param <M>
 * @param <F>
 * @param <I>
 */
public abstract class X47BRESTResourceForSearchBase<F extends SearchFilter,I extends SearchResultItem>
			  extends RESTResourceBase {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public <SD extends RESTSearchDelegateBase<F,I>>
		   X47BRESTResourceForSearchBase(final SD searchDelegate) {
		super(searchDelegate);	// do not use the base delegate field
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  SEARCH
/////////////////////////////////////////////////////////////////////////////////////////
	@GET 
	@Produces(MediaType.APPLICATION_XML)
	public Response search(@HeaderParam("securityContext")  final X47BSecurityContext securityContext,
						   @QueryParam("filter")	   		final F filter,
						   @QueryParam("start")	   	   	    final int startingRow,
						   @QueryParam("items")		   	    final int itemsToReturn) {
		return this.getDelegateAs(RESTSearchDelegateBase.class)
						.search(securityContext,_req.getPathInfo(),
					   		  	filter,null,		// no ordering
					   		  	startingRow,itemsToReturn);
	}

}
