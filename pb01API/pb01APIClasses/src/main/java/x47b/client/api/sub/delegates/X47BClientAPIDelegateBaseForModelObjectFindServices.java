package x47b.client.api.sub.delegates;

import java.util.Collection;

import javax.inject.Provider;

import r01f.locale.Language;
import r01f.model.PersistableModelObject;
import r01f.model.persistence.FindResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.client.api.delegates.ClientAPIDelegateForModelObjectFindServices;
import x47b.api.interfaces.X47BFindServicesBaseForModelObject;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;

public abstract class X47BClientAPIDelegateBaseForModelObjectFindServices<O extends X47BPersistableObjectOID,ID extends X47BPersistableObjectID<O>,
																 M extends PersistableModelObject<O>>
	 		  extends ClientAPIDelegateForModelObjectFindServices<O,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BClientAPIDelegateBaseForModelObjectFindServices(final Provider<SecurityContext> securityContextProvider,
															   final Marshaller modelObjectsMarshaller,
															   final X47BFindServicesBaseForModelObject<O,ID,M> findServicesProxy) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  findServicesProxy);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Returns all entities that has a certain name in a language
	 * @param name
	 * @param lang
	 * @return
	 */
	public Collection<M> findByNameIn(final Language lang,
									  final String name) {
		FindResult<M> opResult = this.getServiceProxyAs(X47BFindServicesBaseForModelObject.class)
											.findByNameIn(this.getSecurityContext(),
												   		  lang,name);
		Collection<M> outOrgs = opResult.getOrThrow();
		return outOrgs;
	}
}
