package pb01a.client.api.sub.delegates;

import java.util.Collection;

import javax.inject.Provider;

import pb01a.api.interfaces.PB01AFindServicesForOrganizationalModelObjectBase;
import pb01a.model.oids.PB01AIDs.PB01APersistableObjectID;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import r01f.locale.Language;
import r01f.model.PersistableModelObject;
import r01f.model.persistence.FindResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;

public abstract class PB01AClientAPIDelegateForOrganizationalEntityFindServicesBase<O extends PB01APersistableObjectOID,ID extends PB01APersistableObjectID<O>,
																 	 			   M extends PersistableModelObject<O>>
	 		  extends PB01AClientAPIDelegateForFindServicesBase<O,ID,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01AClientAPIDelegateForOrganizationalEntityFindServicesBase(final Provider<SecurityContext> securityContextProvider,
																		final Marshaller modelObjectsMarshaller,
													  	  				final PB01AFindServicesForOrganizationalModelObjectBase<O,ID,M> findServicesProxy) {
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
		FindResult<M> opResult = this.getServiceProxyAs(PB01AFindServicesForOrganizationalModelObjectBase.class)
											.findByNameIn(this.getSecurityContext(),
												   		  lang,name);
		Collection<M> outOrgs = opResult.getOrThrow();
		return outOrgs;
	}
}
