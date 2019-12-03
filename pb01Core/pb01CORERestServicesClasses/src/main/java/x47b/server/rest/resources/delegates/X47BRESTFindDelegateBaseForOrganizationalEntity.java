package x47b.server.rest.resources.delegates;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import r01f.locale.Language;
import r01f.model.PersistableModelObject;
import r01f.model.persistence.FindResult;
import r01f.rest.RESTOperationsResponseBuilder;
import r01f.securitycontext.SecurityContext;
import x47b.api.interfaces.X47BFindServicesForOrganizationalModelObjectBase;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;

public class X47BRESTFindDelegateBaseForOrganizationalEntity<O extends X47BPersistableObjectOID,ID extends X47BPersistableObjectID<O>,
															 M extends PersistableModelObject<O>>
	 extends X47BRESTFindDelegateBaseForEntity<O,ID,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BRESTFindDelegateBaseForOrganizationalEntity(final Class<M> modelObjectType,
														   final X47BFindServicesForOrganizationalModelObjectBase<O,ID,M> findServices) {
		super(modelObjectType,
			  findServices);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override @SuppressWarnings("unchecked")
	public Response findByNameIn(final SecurityContext securityContext,final String resourcePath,
								 final Language lang,final String name) {
		FindResult<M> findResult = this.getFindServicesAs(X47BFindServicesForOrganizationalModelObjectBase.class)
												.findByNameIn(securityContext,
															  lang,name);
		return RESTOperationsResponseBuilder.findOn(_modelObjectType)
											.at(URI.create(resourcePath))
											.mediaType(MediaType.TEXT_XML_TYPE)
											.build(findResult);
	}
}
