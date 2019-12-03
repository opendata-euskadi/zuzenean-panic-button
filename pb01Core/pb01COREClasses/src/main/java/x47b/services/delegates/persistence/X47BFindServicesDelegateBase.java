package x47b.services.delegates.persistence;

import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindResultBuilder;
import r01f.persistence.db.DBFindForModelObject;
import r01f.securitycontext.SecurityContext;
import r01f.services.delegates.persistence.FindServicesForModelObjectDelegateBase;
import x47b.api.interfaces.X47BFindServicesBase;
import x47b.api.interfaces.X47BFindServicesBaseForModelObject;
import x47b.model.X47BPersistableObject;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;


abstract class X47BFindServicesDelegateBase<O extends X47BPersistableObjectOID,ID extends X47BPersistableObjectID<O>,M extends X47BPersistableObject<O,ID>>
	   extends FindServicesForModelObjectDelegateBase<O,M>
    implements X47BFindServicesBase<O,ID,M> {

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BFindServicesDelegateBase(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
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
		return this.getServiceImplAs(X47BFindServicesBaseForModelObject.class)
						.findByNameIn(securityContext,
								   	  lang,name);
	}
}
