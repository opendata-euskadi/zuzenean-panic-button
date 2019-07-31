package pb01.ui.vaadin.view;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.common.collect.FluentIterable;

import pb01.ui.vaadin.orgentity.organization.PB01COREMediatorForOrganization;
import pb01.ui.vaadin.orgentity.orgdivision.PB01COREMediatorForOrgDivision;
import pb01.ui.vaadin.orgentity.orgdivisionservice.PB01COREMediatorForOrgDivisionService;
import pb01.ui.vaadin.orgentity.orgdivisionservicelocation.PB01COREMediatorForOrgDivisionServiceLocation;
import pb01.ui.vaadin.view.components.PB01VaadinComboItem;
import r01f.locale.Language;
import r01f.ui.presenter.UIPresenter;
import r01f.ui.presenter.UIPresenterSubscriber;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.summaries.X47BSummarizedOrganizationalObject;

@Singleton
public class PB01MainViewPresenter
  implements UIPresenter {

    private static final long serialVersionUID = -4995313906513090391L;
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
    private final transient PB01MainViewCOREMediator _coreMediator;
    private final transient PB01COREMediatorForOrganization _coreMediatorForOrg;
    private final transient PB01COREMediatorForOrgDivision _coreMediatorForOrgDiv;
    private final transient PB01COREMediatorForOrgDivisionService _coreMediatorForOrgDivSrvc;
    private final transient PB01COREMediatorForOrgDivisionServiceLocation _coreMediatorForOrgDivSrvcLoc;

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
    @Inject
    public PB01MainViewPresenter(final PB01MainViewCOREMediator coreMediator,
    							 final PB01COREMediatorForOrganization coreMediatorForOrg,
    							 final PB01COREMediatorForOrgDivision coreMediatorForOrgDiv,
    							 final PB01COREMediatorForOrgDivisionService coreMediatorForOrgDivSrvc,
    							 final PB01COREMediatorForOrgDivisionServiceLocation coreMediatorForOrgDivSrvcLoc) {
    	_coreMediator = coreMediator;

    	_coreMediatorForOrg = coreMediatorForOrg;
    	_coreMediatorForOrgDiv = coreMediatorForOrgDiv;
    	_coreMediatorForOrgDivSrvc = coreMediatorForOrgDivSrvc;
    	_coreMediatorForOrgDivSrvcLoc = coreMediatorForOrgDivSrvcLoc;
    }
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
    public void onOrgsComboDataNeeded(final Language lang,
                                      final UIPresenterSubscriber<Collection<PB01VaadinComboItem>> presenterSubscriber) {
    	_coreMediatorForOrg.loadAllOrgs(lang,
    							  		// just transform the Collection<M> into a Collection<PB01VaadinComboItem>
	    							    orgs -> _onSuccessLoadingOrgEntitySumms(orgs,presenterSubscriber));
    }
    public void onOrgDivisionsComboDataNeeded(final X47BOrganizationOID orgOid,
    										  final Language lang,
                                      		  final UIPresenterSubscriber<Collection<PB01VaadinComboItem>> presenterSubscriber) {
    	_coreMediatorForOrgDiv.loadOrgDivisions(orgOid,
    											lang,
    											// just transform the Collection<M> into a Collection<PB01VaadinComboItem>
    											divs -> _onSuccessLoadingOrgEntitySumms(divs,presenterSubscriber));
    }
    public void onOrgDivisionServicesComboDataNeeded(final X47BOrgDivisionOID orgDivOid,
    										  		 final Language lang,
    										  		 final UIPresenterSubscriber<Collection<PB01VaadinComboItem>> presenterSubscriber) {
    	_coreMediatorForOrgDivSrvc.loadOrgDivisonServices(orgDivOid,
    													  lang,
		    											  // just transform the Collection<M> into a Collection<PB01VaadinComboItem>
		    											  srvcs -> _onSuccessLoadingOrgEntitySumms(srvcs,presenterSubscriber));
    }
    public void onOrgDivisionServiceLocationsComboDataNeeded(final X47BOrgDivisionServiceOID orgDivSrvcOid,
    										  		 		 final Language lang,
    										  		 		 final UIPresenterSubscriber<Collection<PB01VaadinComboItem>> presenterSubscriber) {
    	_coreMediatorForOrgDivSrvcLoc.loadOrgDivisionServiceLocations(orgDivSrvcOid,
    													  			  lang,
					    											  // just transform the Collection<M> into a Collection<PB01VaadinComboItem>
					    											  locs -> _onSuccessLoadingOrgEntitySumms(locs,presenterSubscriber));
    }
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
    private <S extends X47BSummarizedOrganizationalObject<?,?,?>> void _onSuccessLoadingOrgEntitySumms(final Collection<S> sums,
    																								   final UIPresenterSubscriber<Collection<PB01VaadinComboItem>> presenterSubscriber) {
		Collection<PB01VaadinComboItem> cmbItems = FluentIterable.from(sums)
														.transform(srvc -> PB01VaadinComboItem.FROM_OBJ_SUMMARY.apply(srvc))
														.toList();
		// tell the view
		presenterSubscriber.onSuccess(cmbItems);
    }
}
