package x47b.services.delegates.persistence;

import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindResultBuilder;
import r01f.persistence.db.DBFindForModelObject;
import r01f.securitycontext.SecurityContext;
import x47b.api.interfaces.X47BFindServicesForOrganizationalModelObjectBase;
import x47b.model.oids.X47BIDs.X47BModelObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;
import x47b.model.org.X47BOrganizationalModelObject;


abstract class X47BFindServicesDelegateForOrganizationalEntityBase<O extends X47BPersistableObjectOID,ID extends X47BModelObjectID<O>,M extends X47BOrganizationalModelObject<O,ID>>
	   extends X47BFindServicesDelegateBase<O,ID,M>
    implements X47BFindServicesForOrganizationalModelObjectBase<O,ID,M> {

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BFindServicesDelegateForOrganizationalEntityBase(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
															   final Class<M> modelObjectType,
									    					   final DBFindForModelObject<O,M> find) {
		super(coreCfg,
			  modelObjectType,
			  find);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override 
	public FindResult<M> findByNameIn(final SecurityContext securityContext,
									  final Language lang,final String name) {
		if (name == null || lang == null) {
			return FindResultBuilder.using(securityContext)
								    .on(_modelObjectType)
								    .errorFindingEntities()
								   		.causedByClientBadRequest("The name or lang cannot be null");
		}
		return this.getServiceImplAs(X47BFindServicesForOrganizationalModelObjectBase.class)
						.findByNameIn(securityContext,
								   	  lang,name);
	}
}
