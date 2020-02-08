package pb01c.services.delegates.persistence;

import pb01a.api.interfaces.PB01AFindServicesForOrganizationalModelObjectBase;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgObjectID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgObjectOID;
import pb01a.model.org.PB01AOrganizationalPersistableObject;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindResultBuilder;
import r01f.persistence.db.DBFindForModelObject;
import r01f.securitycontext.SecurityContext;


abstract class PB01CFindServicesDelegateForOrganizationalEntityBase<O extends PB01AOrgObjectOID,ID extends PB01AOrgObjectID<O>,M extends PB01AOrganizationalPersistableObject<O,ID>>
	   extends PB01CFindServicesDelegateBase<O,ID,M>
    implements PB01AFindServicesForOrganizationalModelObjectBase<O,ID,M> {

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CFindServicesDelegateForOrganizationalEntityBase(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
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
		return this.getServiceImplAs(PB01AFindServicesForOrganizationalModelObjectBase.class)
						.findByNameIn(securityContext,
								   	  lang,name);
	}
}
