package pb01c.db.search;

import java.util.Collection;

import javax.persistence.EntityManager;

import pb01a.api.interfaces.PB01ASearchServicesForOrganizationalEntityObject;
import pb01a.model.search.PB01ASearchFilterForPanicButtonOrganizationalEntity;
import pb01a.model.search.PB01ASearchResultItemForPanicButtonOrganizationalEntity;
import pb01c.bootstrap.core.panicbutton.PB01CDBModuleConfig;
import pb01c.db.entities.PB01CDBEntityForOrganizationalEntityBase;
import r01f.guids.PersistableObjectOID;
import r01f.objectstreamer.Marshaller;
import r01f.patterns.Factory;
import r01f.persistence.search.db.DBSearcherBase;
import r01f.securitycontext.SecurityContext;

public class PB01CDBSearcherForPanicButtonOrganizationalEntity
     extends DBSearcherBase<PB01ASearchFilterForPanicButtonOrganizationalEntity,PB01ASearchResultItemForPanicButtonOrganizationalEntity,
     						PB01CDBEntityForOrganizationalEntityBase>
  implements PB01ASearchServicesForOrganizationalEntityObject {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CDBSearcherForPanicButtonOrganizationalEntity(final PB01CDBModuleConfig dbModuleConfig,
															final EntityManager entityManager,
															final Marshaller marshaller) {
		super(entityManager,
			  new Factory<PB01CDBSearchQueryForPanicButtonOrganizationalEntity>() {
						@Override
						public PB01CDBSearchQueryForPanicButtonOrganizationalEntity create() {
							return new PB01CDBSearchQueryForPanicButtonOrganizationalEntity(dbModuleConfig,
																  	    				   entityManager,
																  	    				   null);			// the ui language
						}
			  },
			  new PB01CDBEntityToSearchResultItemTransformerForPanicButtonOrganizationalEntity(marshaller));
	}

	@Override
	public <O extends PersistableObjectOID> Collection<O> filterRecordsOids(final SecurityContext securityContext,
																			final PB01ASearchFilterForPanicButtonOrganizationalEntity filter) {
		throw new UnsupportedOperationException();
	}
}

