package pb01.ui.vaadin.view;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.common.collect.FluentIterable;

import lombok.extern.slf4j.Slf4j;
import pb01.ui.vaadin.alarmevent.PB01ViewAlarmEvent;
import pb01.ui.vaadin.orgentity.organization.PB01COREMediatorForOrganization;
import pb01.ui.vaadin.orgentity.orgdivision.PB01COREMediatorForOrgDivision;
import pb01.ui.vaadin.orgentity.orgdivisionservice.PB01COREMediatorForOrgDivisionService;
import pb01.ui.vaadin.orgentity.orgdivisionservicelocation.PB01COREMediatorForOrgDivisionServiceLocation;
import pb01.ui.vaadin.orgentity.workplace.PB01COREMediatorForWorkPlace;
import pb01.ui.vaadin.view.components.PB01VaadinComboItem;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgObjectOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.org.PB01AOrgObjectRef;
import pb01a.model.org.PB01AOrgObjectType;
import pb01a.model.org.summaries.PB01ASummarizedOrganizationalObject;
import pb01a.model.search.PB01ASearchFilterForPanicButtonOrganizationalEntity;
import r01f.locale.Language;
import r01f.ui.presenter.UIPresenter;
import r01f.ui.presenter.UIPresenterSubscriber;
import r01f.ui.vaadin.serverpush.VaadinServerPushedMessagesBroadcaster;
import r01f.util.types.collections.CollectionUtils;
import r01f.util.types.collections.Lists;

@Slf4j
@Singleton
public class PB01MainViewPresenter
  implements UIPresenter {

    private static final long serialVersionUID = -4995313906513090391L;
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
    // main core mediator
    private final transient PB01MainViewCOREMediator _coreMediator;

    // server-pushed events broadcaster (used server-push a message to the client when an alarm event is fired)
    private final VaadinServerPushedMessagesBroadcaster _serverPushedMessagesBroadcaster;

    // org entities core mediators
    private final transient PB01COREMediatorForOrganization _coreMediatorForOrg;
    private final transient PB01COREMediatorForOrgDivision _coreMediatorForOrgDiv;
    private final transient PB01COREMediatorForOrgDivisionService _coreMediatorForOrgDivSrvc;
    private final transient PB01COREMediatorForOrgDivisionServiceLocation _coreMediatorForOrgDivSrvcLoc;
    private final transient PB01COREMediatorForWorkPlace _coreMediatorForWorkPlace;

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
    @Inject
    public PB01MainViewPresenter(// main core mediator
    							 final PB01MainViewCOREMediator coreMediator,
								 // server-pushed events broadcaster (used server-push a message to the client when an alarm event is fired)
								 final VaadinServerPushedMessagesBroadcaster serverPushedMessagesBroadcaster,
    							 // org entities core mediators
    							 final PB01COREMediatorForOrganization coreMediatorForOrg,
    							 final PB01COREMediatorForOrgDivision coreMediatorForOrgDiv,
    							 final PB01COREMediatorForOrgDivisionService coreMediatorForOrgDivSrvc,
    							 final PB01COREMediatorForOrgDivisionServiceLocation coreMediatorForOrgDivSrvcLoc,
    							 final PB01COREMediatorForWorkPlace coreMediatorForWorkPlace) {
    	_coreMediator = coreMediator;

    	_serverPushedMessagesBroadcaster = serverPushedMessagesBroadcaster;

    	_coreMediatorForOrg = coreMediatorForOrg;
    	_coreMediatorForOrgDiv = coreMediatorForOrgDiv;
    	_coreMediatorForOrgDivSrvc = coreMediatorForOrgDivSrvc;
    	_coreMediatorForOrgDivSrvcLoc = coreMediatorForOrgDivSrvcLoc;
    	_coreMediatorForWorkPlace = coreMediatorForWorkPlace;
    }
/////////////////////////////////////////////////////////////////////////////////////////
//	GRID
/////////////////////////////////////////////////////////////////////////////////////////
	public void onGridDataNeeded(final PB01AOrganizationOID orgOid,
								 final PB01AOrgDivisionOID orgDivOid,
								 final PB01AOrgDivisionServiceOID orgDivSrvcOid,
								 final PB01AOrgDivisionServiceLocationOID orgDivSrvcLocOid,
								 final PB01AWorkPlaceOID workPlaceOid,
								 final int firstItemNum,final int numberOfItems,
								 final UIPresenterSubscriber<Collection<PB01ViewObjForSearchResultItem>> presenterSubscriber) {
		// [1] - Create the filter
		final PB01ASearchFilterForPanicButtonOrganizationalEntity filter = new PB01ASearchFilterForPanicButtonOrganizationalEntity();
		filter.setUILanguage(Language.SPANISH);
		if (orgOid != null) 			filter.belongingTo(orgOid);
		if (orgDivOid != null)			filter.belongingTo(orgDivOid);
		if (orgDivSrvcOid != null)		filter.belongingTo(orgDivSrvcOid);
		if (orgDivSrvcLocOid != null)	filter.belongingTo(orgDivSrvcLocOid);
		if (workPlaceOid != null)		filter.belongingTo(workPlaceOid);

		// [2] - Load the object list using the api
		_coreMediator.search(filter,
							 firstItemNum,numberOfItems,
							 searchResults -> {
													if (searchResults != null
													 && CollectionUtils.hasData(searchResults.getPageItems())) {
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
													} else {
														log.info("NO search results returned!");
														presenterSubscriber.onSuccess(Lists.newArrayList());	// no search results
													}
							 				  });
	}
	public void raiseAlarm(final PB01AWorkPlaceOID workPlaceOid,
						   final UIPresenterSubscriber<PB01ViewAlarmEvent> subscriber) {
		_coreMediator.raiseAlarm(workPlaceOid,
								 alarmEvent -> subscriber.onSuccess(new PB01ViewAlarmEvent(alarmEvent)));	// Tell the subscriber (the view) that the event was sent
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	COMBOS
/////////////////////////////////////////////////////////////////////////////////////////
	public void onOrgObjectComboDataNeeded(final PB01AOrgObjectRef<?,?> parentOrgObjectRef,
										   final Language lang,
										   final UIPresenterSubscriber<Collection<PB01VaadinComboItem>> presenterSubscriber) {
		// use the parent combo oid to guess the object type
		PB01AOrgObjectOID parentObjOid = parentOrgObjectRef.getOid();
		final PB01AOrgObjectType parentObjType = PB01AOrgObjectType.ofOIDType(parentObjOid.getClass());

		// call the correct presenter method depending on the object type
		switch(parentObjType) {
		case ORGANIZATION:
			this.onOrgDivisionsComboDataNeeded((PB01AOrganizationOID)parentObjOid,
											   lang,
											   presenterSubscriber);			// when loaded the combo data, just refresh the combo
			break;
		case ORG_DIVISION:
			this.onOrgDivisionServicesComboDataNeeded((PB01AOrgDivisionOID)parentObjOid,
											 		  lang,
											 		  presenterSubscriber);	// when loaded the combo data, just refresh the combo
			break;
		case ORG_DIVISION_SERVICE:
			this.onOrgDivisionServiceLocationsComboDataNeeded((PB01AOrgDivisionServiceOID)parentObjOid,
											 		 		  lang,
											 		 		  presenterSubscriber);	// when loaded the combo data, just refresh the combo
			break;
		case ORG_DIVISION_SERVICE_LOCATION:
			this.onWorkPlacesComboDataNeeded((PB01AOrgDivisionServiceLocationOID)parentObjOid,
											 lang,
											 presenterSubscriber);	// when loaded the combo data, just refresh the combo
			break;
		case WORKPLACE:
			throw new IllegalStateException();
		default:
			throw new IllegalStateException();
		}
	}
    public void onOrgsComboDataNeeded(final Language lang,
                                      final UIPresenterSubscriber<Collection<PB01VaadinComboItem>> presenterSubscriber) {
    	_coreMediatorForOrg.loadAllOrgs(lang,
    							  		// just transform the Collection<M> into a Collection<PB01VaadinComboItem>
	    							    orgs -> _onSuccessLoadingOrgObjectSumms(orgs,presenterSubscriber));
    }
    public void onOrgDivisionsComboDataNeeded(final PB01AOrganizationOID orgOid,
    										  final Language lang,
                                      		  final UIPresenterSubscriber<Collection<PB01VaadinComboItem>> presenterSubscriber) {
    	_coreMediatorForOrgDiv.loadOrgDivisions(orgOid,
    											lang,
    											// just transform the Collection<M> into a Collection<PB01VaadinComboItem>
    											divs -> _onSuccessLoadingOrgObjectSumms(divs,presenterSubscriber));
    }
    public void onOrgDivisionServicesComboDataNeeded(final PB01AOrgDivisionOID orgDivOid,
    										  		 final Language lang,
    										  		 final UIPresenterSubscriber<Collection<PB01VaadinComboItem>> presenterSubscriber) {
    	_coreMediatorForOrgDivSrvc.loadOrgDivisonServices(orgDivOid,
    													  lang,
		    											  // just transform the Collection<M> into a Collection<PB01VaadinComboItem>
		    											  srvcs -> _onSuccessLoadingOrgObjectSumms(srvcs,presenterSubscriber));
    }
    public void onOrgDivisionServiceLocationsComboDataNeeded(final PB01AOrgDivisionServiceOID orgDivSrvcOid,
    										  		 		 final Language lang,
    										  		 		 final UIPresenterSubscriber<Collection<PB01VaadinComboItem>> presenterSubscriber) {
    	_coreMediatorForOrgDivSrvcLoc.loadOrgDivisionServiceLocations(orgDivSrvcOid,
    													  			  lang,
					    											  // just transform the Collection<M> into a Collection<PB01VaadinComboItem>
					    											  locs -> _onSuccessLoadingOrgObjectSumms(locs,presenterSubscriber));
    }
    public void onWorkPlacesComboDataNeeded(final PB01AOrgDivisionServiceLocationOID orgDivSrvcLocOid,
    										final Language lang,
    										final UIPresenterSubscriber<Collection<PB01VaadinComboItem>> presenterSubscriber) {
    	_coreMediatorForWorkPlace.loadWorkPlaces(orgDivSrvcLocOid,
    											 lang,
					    						 // just transform the Collection<M> into a Collection<PB01VaadinComboItem>
					    						 wpcs -> _onSuccessLoadingOrgObjectSumms(wpcs,presenterSubscriber));
    }
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
    private <S extends PB01ASummarizedOrganizationalObject<?,?,?>> void _onSuccessLoadingOrgObjectSumms(final Collection<S> sums,
    																								   final UIPresenterSubscriber<Collection<PB01VaadinComboItem>> presenterSubscriber) {
		final Collection<PB01VaadinComboItem> cmbItems = FluentIterable.from(sums)
														.transform(srvc -> PB01VaadinComboItem.FROM_OBJ_SUMMARY.apply(srvc))
														.toList();
		// tell the view
		presenterSubscriber.onSuccess(cmbItems);
    }
}
