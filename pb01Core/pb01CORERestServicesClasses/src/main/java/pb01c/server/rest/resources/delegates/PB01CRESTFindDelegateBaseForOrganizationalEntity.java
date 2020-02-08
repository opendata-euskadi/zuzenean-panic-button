package pb01c.server.rest.resources.delegates;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pb01a.api.interfaces.PB01AFindServicesForOrganizationalModelObjectBase;
import pb01a.model.oids.PB01AIDs.PB01APersistableObjectID;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import r01f.locale.Language;
import r01f.model.PersistableModelObject;
import r01f.model.persistence.FindResult;
import r01f.rest.RESTOperationsResponseBuilder;
import r01f.securitycontext.SecurityContext;

public class PB01CRESTFindDelegateBaseForOrganizationalEntity<O extends PB01APersistableObjectOID,ID extends PB01APersistableObjectID<O>,
															 M extends PersistableModelObject<O>>
	 extends PB01CRESTFindDelegateBaseForEntity<O,ID,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CRESTFindDelegateBaseForOrganizationalEntity(final Class<M> modelObjectType,
														   final PB01AFindServicesForOrganizationalModelObjectBase<O,ID,M> findServices) {
		super(modelObjectType,
			  findServices);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override @SuppressWarnings("unchecked")
	public Response findByNameIn(final SecurityContext securityContext,final String resourcePath,
								 final Language lang,final String name) {
		FindResult<M> findResult = this.getFindServicesAs(PB01AFindServicesForOrganizationalModelObjectBase.class)
												.findByNameIn(securityContext,
															  lang,name);
		return RESTOperationsResponseBuilder.findOn(_modelObjectType)
											.at(URI.create(resourcePath))
											.mediaType(MediaType.TEXT_XML_TYPE)
											.build(findResult);
	}
}
