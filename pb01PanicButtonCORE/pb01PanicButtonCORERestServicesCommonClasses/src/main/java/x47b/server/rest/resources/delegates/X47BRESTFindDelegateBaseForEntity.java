package x47b.server.rest.resources.delegates;

import java.net.URI;

import javax.ws.rs.core.Response;

import r01f.locale.Language;
import r01f.model.PersistableModelObject;
import r01f.model.persistence.FindResult;
import r01f.rest.RESTOperationsResponseBuilder;
import r01f.rest.resources.delegates.RESTFindDelegateBase;
import r01f.securitycontext.SecurityContext;
import x47b.api.interfaces.X47BFindServicesBase;
import x47b.api.interfaces.X47BFindServicesBaseForModelObject;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;

public class X47BRESTFindDelegateBaseForEntity<O extends X47BPersistableObjectOID,ID extends X47BPersistableObjectID<O>,M extends PersistableModelObject<O>>
	 extends RESTFindDelegateBase<O,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BRESTFindDelegateBaseForEntity(final Class<M> modelObjectType,
											 final X47BFindServicesBase<O,ID,M> findServices) {
		super(modelObjectType,
			  findServices);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	public Response findByNameIn(final SecurityContext securityContext,final String resourcePath,
								 final Language lang,final String name) {
		FindResult<M> findResult = this.getFindServicesAs(X47BFindServicesBaseForModelObject.class)
												.findByNameIn(securityContext,
															  lang,name);
		return RESTOperationsResponseBuilder.findOn(_modelObjectType)
											.at(URI.create(resourcePath))
											.build(findResult);
	}
}
