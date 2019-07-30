package pb01.ui.bootstrap;

import javax.inject.Singleton;
import javax.script.ScriptEngineManager;

import com.google.inject.Binder;
import com.google.inject.servlet.ServletModule;
import com.vaadin.navigator.ViewProvider;

import lombok.extern.slf4j.Slf4j;
import pb01.ui.i18n.PB01UII18NManager;
import pb01.ui.vaadin.PB01UIVaadinServlet;
import pb01.ui.vaadin.PB01UIVaadinUIProvider;
import pb01.ui.vaadin.PB01UIVaadinViewProvider;
import pb01.ui.vaadin.view.PB01MainViewCOREMediator;
import pb01.ui.vaadin.view.PB01MainViewPresenter;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenServletExposed;
import r01f.bootstrap.services.core.ServletImplementedServicesCoreBootstrapGuiceModuleBase;
import r01f.ui.i18n.UII18NService;

@Slf4j
public class PB01UIWarBootstrapGuiceModule
     extends ServletImplementedServicesCoreBootstrapGuiceModuleBase {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01UIWarBootstrapGuiceModule( final ServicesCoreBootstrapConfigWhenServletExposed coreBootstrapCfg ) {
		super( coreBootstrapCfg );
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void configure( final Binder binder ) {
		log.warn("START: PB01 UI Bootstraping _____________________________");

		// the servlet module is a kind of replacement for web.xml file
		binder.install(new ServletModule() {
								@Override
								protected void configureServlets() {
									// controller
									this.serve( "/*" )
										.with(PB01UIVaadinServlet.class);
								}
					  });
		// Bind the i18n service
		binder.bind(UII18NService.class)
			  .to(PB01UII18NManager.class);		// loads i18n resource bundles from r01ui.i18n
												// see @R01UIMessageBundle annotation at R01UIDemoVaadinUI

		// UI & view provider
		binder.bind(PB01UIVaadinUIProvider.class)
			  .in(Singleton.class);

		binder.bind(ViewProvider.class)
			  .to(PB01UIVaadinViewProvider.class )
			  .in(Singleton.class);

		// Script engine used to compute expressions
		binder.bind(ScriptEngineManager.class)
			  .in(Singleton.class);

		// api proxy modules: Presenters
		_bindCOREMediators(binder);
		_bindPresenters(binder);

		log.warn("END: PB01 UI Bootstraping _____________________________");
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	private void _bindCOREMediators(final Binder binder) {
		// main
		binder.bind(PB01MainViewCOREMediator.class)
			  .in(Singleton.class);
	}
	private void _bindPresenters(final Binder binder) {
		// person
		binder.bind(PB01MainViewPresenter.class)
			  .in(Singleton.class);
	}
}