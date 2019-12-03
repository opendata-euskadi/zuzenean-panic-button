package x47b.services.delegates.search;

import com.google.common.eventbus.EventBus;

import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.search.Searcher;
import r01f.securitycontext.SecurityContext;
import r01f.services.delegates.persistence.search.SearchServicesForModelObjectDelegateBase;
import r01f.services.delegates.persistence.search.ValidatesSearchFilter;
import r01f.validation.ObjectValidationResult;
import r01f.validation.ObjectValidationResultBuilder;
import x47b.api.interfaces.X47BSearchServicesForOrganizationalEntityObject;
import x47b.model.search.X47BSearchFilterForPanicButtonOrganizationalEntity;
import x47b.model.search.X47BSearchResultItemForPanicButtonOrganizationalEntity;


public class X47BSearchServicesDelegateForEntityModelObject 
     extends SearchServicesForModelObjectDelegateBase<X47BSearchFilterForPanicButtonOrganizationalEntity,X47BSearchResultItemForPanicButtonOrganizationalEntity>
  implements X47BSearchServicesForOrganizationalEntityObject,
  			 ValidatesSearchFilter<X47BSearchFilterForPanicButtonOrganizationalEntity> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR & BUILDER
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BSearchServicesDelegateForEntityModelObject(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
														  final Searcher<X47BSearchFilterForPanicButtonOrganizationalEntity,X47BSearchResultItemForPanicButtonOrganizationalEntity> searcher,
														  final Marshaller marshaller,
														  final EventBus eventBus) {
		super(coreCfg,
			  searcher,
			  eventBus);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public ObjectValidationResult<X47BSearchFilterForPanicButtonOrganizationalEntity> validateSearchFilter(final SecurityContext securityContext, 
																							 			   final X47BSearchFilterForPanicButtonOrganizationalEntity filter) {
		if (filter.getUILanguage() == null) return ObjectValidationResultBuilder.on(filter)
																				.isNotValidBecause("The language is null");
		return ObjectValidationResultBuilder.on(filter)
											.isValid();
	}
}
