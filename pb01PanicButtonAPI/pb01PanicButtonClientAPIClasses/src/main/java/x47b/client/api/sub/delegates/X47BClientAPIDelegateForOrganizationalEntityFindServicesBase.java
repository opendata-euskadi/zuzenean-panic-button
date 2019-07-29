package x47b.client.api.sub.delegates;

import java.util.Collection;

import javax.inject.Provider;

import r01f.locale.Language;
import r01f.model.PersistableModelObject;
import r01f.model.persistence.FindResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import x47b.api.interfaces.X47BFindServicesForOrganizationalModelObjectBase;
import x47b.model.oids.X47BIDs.X47BModelObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;

public abstract class X47BClientAPIDelegateForOrganizationalEntityFindServicesBase<O extends X47BPersistableObjectOID,ID extends X47BModelObjectID<O>,
																 	 			   M extends PersistableModelObject<O>>
	 		  extends X47BClientAPIDelegateForFindServicesBase<O,ID,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BClientAPIDelegateForOrganizationalEntityFindServicesBase(final Provider<SecurityContext> securityContextProvider,
																		final Marshaller modelObjectsMarshaller,
													  	  				final X47BFindServicesForOrganizationalModelObjectBase<O,ID,M> findServicesProxy) {
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
	@SuppressWarnings("unchecked")
	public Collection<M> findByNameIn(final Language lang,
									  final String name) {
		FindResult<M> opResult = this.getServiceProxyAs(X47BFindServicesForOrganizationalModelObjectBase.class)
											.findByNameIn(this.getSecurityContext(),
												   		  lang,name);
		Collection<M> outOrgs = opResult.getOrThrow();
		return outOrgs;
	}
}
