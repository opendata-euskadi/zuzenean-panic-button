package pb01.ui.bootstrap;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;
import javax.script.ScriptEngineManager;

import org.atmosphere.cpr.ApplicationConfig;

import com.google.common.eventbus.EventBus;
import com.google.inject.Binder;
import com.google.inject.servlet.ServletModule;
import com.vaadin.navigator.ViewProvider;

import lombok.extern.slf4j.Slf4j;
import pb01.ui.i18n.PB01UII18NManager;
import pb01.ui.vaadin.PB01UIVaadinUIProvider;
import pb01.ui.vaadin.PB01UIVaadinViewProvider;
import pb01.ui.vaadin.alarmevent.PB01COREMediatorForRaisedAlarmsListView;
import pb01.ui.vaadin.alarmevent.PB01PresenterForRaisedAlarmsListView;
import pb01.ui.vaadin.orgentity.organization.PB01COREMediatorForOrganization;
import pb01.ui.vaadin.orgentity.organization.PB01PresenterForOrganizationDetailView;
import pb01.ui.vaadin.orgentity.orgdivision.PB01COREMediatorForOrgDivision;
import pb01.ui.vaadin.orgentity.orgdivision.PB01PresenterForOrgDivisionDetailView;
import pb01.ui.vaadin.orgentity.orgdivisionservice.PB01COREMediatorForOrgDivisionService;
import pb01.ui.vaadin.orgentity.orgdivisionservice.PB01PresenterForOrgDivisionServiceDetailView;
import pb01.ui.vaadin.orgentity.orgdivisionservicelocation.PB01COREMediatorForOrgDivisionServiceLocation;
import pb01.ui.vaadin.orgentity.orgdivisionservicelocation.PB01PresenterForOrgDivisionServiceLocationDetailView;
import pb01.ui.vaadin.ui.login.PB01SecurityContextServletFilter;
import pb01.ui.vaadin.ui.login.PB01UIVaadinLoginServlet;
import pb01.ui.vaadin.ui.main.PB01UIMainVaadinServlet;
import pb01.ui.vaadin.view.PB01MainViewCOREMediator;
import pb01.ui.vaadin.view.PB01MainViewPresenter;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenServletExposed;
import r01f.bootstrap.services.core.ServletImplementedServicesCoreBootstrapGuiceModuleBase;
import r01f.inject.annotations.EventBusSingletonImpl;
import r01f.ui.i18n.UII18NService;
import r01f.ui.vaadin.serverpush.VaadinServerPushedMessagesBroadcaster;

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
//	MODULE
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void configure( final Binder binder ) {
		log.warn("START: PB01 UI Bootstraping _____________________________");

		// the servlet module is a kind of replacement for web.xml file
		binder.install(new ServletModule() {
								@Override
								protected void configureServlets() {
									// security context filter
									this.bind(PB01SecurityContextServletFilter.class)
										.in(Singleton.class);
									this.filterRegex(".*")
											.through(PB01SecurityContextServletFilter.class);

									// BEWARE!! server push: force tomcat native API to take the precedence over JSR356
									Map<String, String> params = new HashMap<>();
									params.put(ApplicationConfig.WEBSOCKET_SUPPRESS_JSR356,"true");

									// login vaadin servlet
									this.serve("/login")
										.with(PB01UIVaadinLoginServlet.class,params);

									// main vaadin servlet
									this.serve( "/*" )													// see: https://stackoverflow.com/questions/36917059/atmosphere-is-unable-to-configure-jsr-356
										.with(PB01UIMainVaadinServlet.class,params);						// If this param is NOT set: java.lang.IllegalStateException: Unable to configure jsr356 at that stage
								}
					  });
		// Bind the i18n service
		binder.bind(UII18NService.class)
			  .to(PB01UII18NManager.class);		// loads i18n resource bundles from r01ui.i18n
												// see @UIMessageBundle annotation at PB01UIVaadinUI
		// bind an event bus instance
		binder.bind(EventBus.class)
			  .annotatedWith(new EventBusSingletonImpl("uiPresenter"))
			  .toInstance(new EventBus());

		// bind the server-side pushed messages broadcaster
		binder.bind(VaadinServerPushedMessagesBroadcaster.class)
			  .in(Singleton.class);


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

		// organizational entities
		binder.bind(PB01COREMediatorForOrganization.class)
			  .in(Singleton.class);
		binder.bind(PB01COREMediatorForOrgDivision.class)
			  .in(Singleton.class);
		binder.bind(PB01COREMediatorForOrgDivisionService.class)
			  .in(Singleton.class);
		binder.bind(PB01COREMediatorForOrgDivisionServiceLocation.class)
			  .in(Singleton.class);
		// alarm list
		binder.bind(PB01COREMediatorForRaisedAlarmsListView.class)
			  .in(Singleton.class);
	}
	private void _bindPresenters(final Binder binder) {
		// main
		binder.bind(PB01MainViewPresenter.class)
			  .in(Singleton.class);

		// organizational entities
		binder.bind(PB01PresenterForOrganizationDetailView.class)
			  .in(Singleton.class);
		binder.bind(PB01PresenterForOrgDivisionDetailView.class)
			  .in(Singleton.class);
		binder.bind(PB01PresenterForOrgDivisionServiceDetailView.class)
			  .in(Singleton.class);
		binder.bind(PB01PresenterForOrgDivisionServiceLocationDetailView.class)
			  .in(Singleton.class);
		// alarm list
		binder.bind(PB01PresenterForRaisedAlarmsListView.class)
			  .in(Singleton.class);

	}
}