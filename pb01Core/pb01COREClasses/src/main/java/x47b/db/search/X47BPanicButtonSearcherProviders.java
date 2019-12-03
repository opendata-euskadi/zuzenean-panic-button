package x47b.db.search;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;

import com.google.inject.TypeLiteral;

import lombok.experimental.Accessors;
import r01f.bootstrap.persistence.SearcherProviderBinding;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfig;
import r01f.persistence.search.Searcher;
import r01f.persistence.search.SearcherProvider;
import r01f.persistence.search.db.DBSearcherProviderBase;
import x47b.bootstrap.core.panicbutton.X47BDBModuleConfig;
import x47b.model.X47BPersistableObject;
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
		public X47BDBSearcherProviderForPanicButtonObject(@ModelObjectsMarshaller final Marshaller marshaller,
																				  final DBModuleConfig dbModuleConfig,
																		   		  final Provider<EntityManager> entityManagerProvider) {
			super(X47BPersistableObject.class,
				  marshaller,
				  dbModuleConfig,
				  entityManagerProvider);
		}
		@Override
		public Searcher<X47BSearchFilterForPanicButtonOrganizationalEntity,X47BSearchResultItemForPanicButtonOrganizationalEntity> get() {
			return new X47BDBSearcherForPanicButtonOrganizationalEntity((X47BDBModuleConfig)_dbModuleConfig,
												 		  				_entityManagerProvider.get(),
												 		  				_marshaller);
		}
		public static SearcherProviderBinding<X47BSearchFilterForPanicButtonOrganizationalEntity,X47BSearchResultItemForPanicButtonOrganizationalEntity> createGuiceBinding() {
			return SearcherProviderBinding.of(new TypeLiteral<SearcherProvider<X47BSearchFilterForPanicButtonOrganizationalEntity,X47BSearchResultItemForPanicButtonOrganizationalEntity>>() { /* nothing */ },
											  X47BDBSearcherProviderForPanicButtonObject.class);
		}
	}
}
