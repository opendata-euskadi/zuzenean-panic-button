package pb01c.db.search;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;

import com.google.inject.TypeLiteral;

import lombok.experimental.Accessors;
import pb01a.model.PB01APersistableObject;
import pb01a.model.search.PB01ASearchFilterForPanicButtonOrganizationalEntity;
import pb01a.model.search.PB01ASearchResultItemForPanicButtonOrganizationalEntity;
import pb01c.bootstrap.core.panicbutton.PB01CDBModuleConfig;
import r01f.bootstrap.persistence.SearcherProviderBinding;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfig;
import r01f.persistence.search.Searcher;
import r01f.persistence.search.SearcherProvider;
import r01f.persistence.search.db.DBSearcherProviderBase;

public class PB01CPanicButtonSearcherProviders {
/////////////////////////////////////////////////////////////////////////////////////////
// 	ALARM EVENT
/////////////////////////////////////////////////////////////////////////////////////////
	@Accessors(prefix="_")
	public static class PB01CDBSearcherProviderForPanicButtonObject
	            extends DBSearcherProviderBase<PB01ASearchFilterForPanicButtonOrganizationalEntity,PB01ASearchResultItemForPanicButtonOrganizationalEntity> {
		@Inject
		public PB01CDBSearcherProviderForPanicButtonObject(@ModelObjectsMarshaller final Marshaller marshaller,
																				   final DBModuleConfig dbModuleConfig,
																		   		   final Provider<EntityManager> entityManagerProvider) {
			super(marshaller,
				  dbModuleConfig,
				  entityManagerProvider);
		}
		@Override
		public Searcher<PB01ASearchFilterForPanicButtonOrganizationalEntity,PB01ASearchResultItemForPanicButtonOrganizationalEntity> get() {
			return new PB01CDBSearcherForPanicButtonOrganizationalEntity((PB01CDBModuleConfig)_dbModuleConfig,
												 		  				 _entityManagerProvider.get(),
												 		  				 _marshaller);
		}
		public static SearcherProviderBinding<PB01ASearchFilterForPanicButtonOrganizationalEntity,PB01ASearchResultItemForPanicButtonOrganizationalEntity> createGuiceBinding() {
			return SearcherProviderBinding.of(new TypeLiteral<SearcherProvider<PB01ASearchFilterForPanicButtonOrganizationalEntity,PB01ASearchResultItemForPanicButtonOrganizationalEntity>>() { /* nothing */ },
											  PB01CDBSearcherProviderForPanicButtonObject.class);
		}
	}
}
