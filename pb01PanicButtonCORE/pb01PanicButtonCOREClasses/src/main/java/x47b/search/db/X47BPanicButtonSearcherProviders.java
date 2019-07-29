package x47b.search.db;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;

import lombok.experimental.Accessors;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfig;
import r01f.persistence.search.Searcher;
import r01f.persistence.search.db.DBSearcherProviderBase;
import x47b.bootstrap.core.panicbutton.X42TDBModuleConfig;
import x47b.model.search.X47BSearchFilterForPanicButtonOrganizationalEntity;
import x47b.model.search.X47BSearchResultItemForPanicButtonOrganizationalEntity;

public class X47BPanicButtonSearcherProviders {
/////////////////////////////////////////////////////////////////////////////////////////
// 	ALARM EVENT 
/////////////////////////////////////////////////////////////////////////////////////////	
	@Accessors(prefix="_")
	public static class X47BDBSearcherProviderForPanicButtonObject
	            extends DBSearcherProviderBase<X47BSearchFilterForPanicButtonOrganizationalEntity,X47BSearchResultItemForPanicButtonOrganizationalEntity> {
		@Inject
		public X47BDBSearcherProviderForPanicButtonObject(						  final DBModuleConfig dbModuleConfig,
																		   		  final Provider<EntityManager> entityManagerProvider,
														  @ModelObjectsMarshaller final Marshaller marshaller) {
			super(dbModuleConfig,
				  entityManagerProvider,
				  marshaller);
		}
		@Override
		public Searcher<X47BSearchFilterForPanicButtonOrganizationalEntity,X47BSearchResultItemForPanicButtonOrganizationalEntity> get() {
			return new X47BDBSearcherForPanicButtonOrganizationalEntity((X42TDBModuleConfig)_dbModuleConfig,
												 		  				_entityManagerProvider.get(),
												 		  				_marshaller);
		}
	}
}
