package x47b.db.search;

import java.util.Collection;

import javax.persistence.EntityManager;

import r01f.guids.PersistableObjectOID;
import r01f.objectstreamer.Marshaller;
import r01f.patterns.Factory;
import r01f.persistence.search.db.DBSearcherBase;
import r01f.securitycontext.SecurityContext;
import x47b.api.interfaces.X47BSearchServicesForOrganizationalEntityObject;
import x47b.bootstrap.core.panicbutton.X42TDBModuleConfig;
import x47b.db.entities.X47BDBEntityForOrganizationalEntityBase;
import x47b.model.search.X47BSearchFilterForPanicButtonOrganizationalEntity;
import x47b.model.search.X47BSearchResultItemForPanicButtonOrganizationalEntity;

public class X47BDBSearcherForPanicButtonOrganizationalEntity
     extends DBSearcherBase<X47BSearchFilterForPanicButtonOrganizationalEntity,X47BSearchResultItemForPanicButtonOrganizationalEntity,
     						X47BDBEntityForOrganizationalEntityBase>
  implements X47BSearchServicesForOrganizationalEntityObject {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BDBSearcherForPanicButtonOrganizationalEntity(final X42TDBModuleConfig dbModuleConfig,
															final EntityManager entityManager,
															final Marshaller marshaller) {
		super(entityManager,
			  new Factory<X47BDBSearchQueryForPanicButtonOrganizationalEntity>() {
						@Override
						public X47BDBSearchQueryForPanicButtonOrganizationalEntity create() {
							return new X47BDBSearchQueryForPanicButtonOrganizationalEntity(dbModuleConfig,
																  	    				   entityManager,
																  	    				   null);			// the ui language
						}
			  },
			  new X47BDBEntityToSearchResultItemTransformerForPanicButtonOrganizationalEntity(marshaller));
	}

	@Override
	public <O extends PersistableObjectOID> Collection<O> filterRecordsOids(final SecurityContext securityContext,
																			final X47BSearchFilterForPanicButtonOrganizationalEntity filter) {
		throw new UnsupportedOperationException();
	}
}

