package pb01.ui.vaadin.view;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.common.collect.FluentIterable;

import lombok.extern.slf4j.Slf4j;
import pb01.ui.vaadin.orgentity.organization.PB01COREMediatorForOrganization;
import pb01.ui.vaadin.orgentity.orgdivision.PB01COREMediatorForOrgDivision;
import pb01.ui.vaadin.orgentity.orgdivisionservice.PB01COREMediatorForOrgDivisionService;
import pb01.ui.vaadin.orgentity.orgdivisionservicelocation.PB01COREMediatorForOrgDivisionServiceLocation;
import pb01.ui.vaadin.orgentity.workplace.PB01COREMediatorForWorkPlace;
import pb01.ui.vaadin.view.components.PB01VaadinComboItem;
import r01f.locale.Language;
import r01f.ui.presenter.UIPresenter;
import r01f.ui.presenter.UIPresenterSubscriber;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BOrganizationalObjectRef;
import x47b.model.org.summaries.X47BSummarizedOrganizationalObject;
import x47b.model.search.X47BSearchFilterForPanicButtonOrganizationalEntity;

@Slf4j
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
    private final transient PB01COREMediatorForWorkPlace _coreMediatorForWorkPlace;

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
    @Inject
    public PB01MainViewPresenter(final PB01MainViewCOREMediator coreMediator,
    							 final PB01COREMediatorForOrganization coreMediatorForOrg,
    							 final PB01COREMediatorForOrgDivision coreMediatorForOrgDiv,
    							 final PB01COREMediatorForOrgDivisionService coreMediatorForOrgDivSrvc,
    							 final PB01COREMediatorForOrgDivisionServiceLocation coreMediatorForOrgDivSrvcLoc,
    							 final PB01COREMediatorForWorkPlace coreMediatorForWorkPlace) {
    	_coreMediator = coreMediator;

    	_coreMediatorForOrg = coreMediatorForOrg;
    	_coreMediatorForOrgDiv = coreMediatorForOrgDiv;
    	_coreMediatorForOrgDivSrvc = coreMediatorForOrgDivSrvc;
    	_coreMediatorForOrgDivSrvcLoc = coreMediatorForOrgDivSrvcLoc;
    	_coreMediatorForWorkPlace = coreMediatorForWorkPlace;
    }
/////////////////////////////////////////////////////////////////////////////////////////
//	GRID
/////////////////////////////////////////////////////////////////////////////////////////
	public void onGridDataNeeded(final X47BOrganizationalObjectRef<X47BOrganizationOID,X47BOrganizationID> selectedOrg,
								 final X47BOrganizationalObjectRef<X47BOrgDivisionOID,X47BOrgDivisionID> selectedOrgDiv,
								 final X47BOrganizationalObjectRef<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID> selectedOrgDivSrvc,
								 final X47BOrganizationalObjectRef<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID> selectedOrgDivSrvcLoc,
								 final X47BOrganizationalObjectRef<X47BWorkPlaceOID,X47BWorkPlaceID> selectedWorkPlace,
								 final int firstItemNum,final int numberOfItems,
								 final UIPresenterSubscriber<Collection<PB01ViewObjForSearchResultItem>> presenterSubscriber) {
		// [1] - Create the filter
		final X47BSearchFilterForPanicButtonOrganizationalEntity filter = new X47BSearchFilterForPanicButtonOrganizationalEntity();
		filter.setUILanguage(Language.SPANISH);
		if (selectedOrg != null) 			filter.belongingTo(selectedOrg.getOid());
		if (selectedOrgDiv != null)			filter.belongingTo(selectedOrgDiv.getOid());
		if (selectedOrgDivSrvc != null)		filter.belongingTo(selectedOrgDivSrvc.getOid());
		if (selectedOrgDivSrvcLoc != null)	filter.belongingTo(selectedOrgDivSrvcLoc.getOid());
		if (selectedWorkPlace != null)		filter.belongingTo(selectedWorkPlace.getOid());

		// [2] - Load the object list using the api
		_coreMediator.search(filter,
							 firstItemNum,numberOfItems,
							 searchResults -> {
													log.info("...returned {} search result items from a total of {} starting at item at {}",
															 searchResults.getPageItems().size(),
															 searchResults.getTotalItemsCount(),
															 searchResults.getStartPosition());

													// [2] - Transform into a view object
													Collection<PB01ViewObjForSearchResultItem> viewObjs = null;
													viewObjs = FluentIterable.from(searchResults.getPageItems())
																	 .transform(item -> new PB01ViewObjForSearchResultItem(item))
																	 .toList();

													//  [2] - Hand objects to the view
													presenterSubscriber.onSuccess(viewObjs);
							 				  });
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	COMBOS
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
    public void onWorkPlacesComboDataNeeded(final X47BOrgDivisionServiceLocationOID orgDivSrvcLocOid,
    										final Language lang,
    										final UIPresenterSubscriber<Collection<PB01VaadinComboItem>> presenterSubscriber) {
    	_coreMediatorForWorkPlace.loadWorkPlaces(orgDivSrvcLocOid,
    											 lang,
					    						 // just transform the Collection<M> into a Collection<PB01VaadinComboItem>
					    						 wpcs -> _onSuccessLoadingOrgEntitySumms(wpcs,presenterSubscriber));
    }
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
    private <S extends X47BSummarizedOrganizationalObject<?,?,?>> void _onSuccessLoadingOrgEntitySumms(final Collection<S> sums,
    																								   final UIPresenterSubscriber<Collection<PB01VaadinComboItem>> presenterSubscriber) {
		final Collection<PB01VaadinComboItem> cmbItems = FluentIterable.from(sums)
														.transform(srvc -> PB01VaadinComboItem.FROM_OBJ_SUMMARY.apply(srvc))
														.toList();
		// tell the view
		presenterSubscriber.onSuccess(cmbItems);
    }
}
