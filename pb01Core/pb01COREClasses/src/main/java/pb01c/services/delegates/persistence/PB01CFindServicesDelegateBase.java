package pb01c.services.delegates.persistence;

import pb01a.api.interfaces.PB01AFindServicesBase;
import pb01a.api.interfaces.PB01AFindServicesBaseForModelObject;
import pb01a.model.PB01APersistableObject;
import pb01a.model.oids.PB01AIDs.PB01APersistableObjectID;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindResultBuilder;
import r01f.persistence.db.DBFindForModelObject;
import r01f.securitycontext.SecurityContext;
import r01f.services.delegates.persistence.FindServicesForModelObjectDelegateBase;


abstract class PB01CFindServicesDelegateBase<O extends PB01APersistableObjectOID,ID extends PB01APersistableObjectID<O>,M extends PB01APersistableObject<O,ID>>
	   extends FindServicesForModelObjectDelegateBase<O,M>
    implements PB01AFindServicesBase<O,ID,M> {

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CFindServicesDelegateBase(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
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
		return this.getServiceImplAs(PB01AFindServicesBaseForModelObject.class)
						.findByNameIn(securityContext,
								   	  lang,name);
	}
}
