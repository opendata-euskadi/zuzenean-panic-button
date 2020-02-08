package pb01c.services.delegates.search;

import com.google.common.eventbus.EventBus;

import pb01a.api.interfaces.PB01ASearchServicesForOrganizationalEntityObject;
import pb01a.model.search.PB01ASearchFilterForPanicButtonOrganizationalEntity;
import pb01a.model.search.PB01ASearchResultItemForPanicButtonOrganizationalEntity;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.search.Searcher;
import r01f.securitycontext.SecurityContext;
import r01f.services.delegates.persistence.search.SearchServicesForModelObjectDelegateBase;
import r01f.services.delegates.persistence.search.ValidatesSearchFilter;
import r01f.validation.ObjectValidationResult;
import r01f.validation.ObjectValidationResultBuilder;


public class PB01CSearchServicesDelegateForEntityModelObject 
     extends SearchServicesForModelObjectDelegateBase<PB01ASearchFilterForPanicButtonOrganizationalEntity,PB01ASearchResultItemForPanicButtonOrganizationalEntity>
  implements PB01ASearchServicesForOrganizationalEntityObject,
  			 ValidatesSearchFilter<PB01ASearchFilterForPanicButtonOrganizationalEntity> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR & BUILDER
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CSearchServicesDelegateForEntityModelObject(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
														  final Searcher<PB01ASearchFilterForPanicButtonOrganizationalEntity,PB01ASearchResultItemForPanicButtonOrganizationalEntity> searcher,
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
	public ObjectValidationResult<PB01ASearchFilterForPanicButtonOrganizationalEntity> validateSearchFilter(final SecurityContext securityContext, 
																							 			   final PB01ASearchFilterForPanicButtonOrganizationalEntity filter) {
		if (filter.getUILanguage() == null) return ObjectValidationResultBuilder.on(filter)
																				.isNotValidBecause("The language is null");
		return ObjectValidationResultBuilder.on(filter)
											.isValid();
	}
}
