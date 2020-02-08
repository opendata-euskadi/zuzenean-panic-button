package pb01.ui.vaadin.orgentity.orgdivisionservicelocation;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.common.eventbus.EventBus;

import pb01.ui.vaadin.orgentity.PB01PresenterForOrgObjectDetailBase;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import r01f.inject.annotations.EventBusSingleton;

@Singleton
public class PB01PresenterForOrgDivisionServiceLocationDetailView
	 extends PB01PresenterForOrgObjectDetailBase<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocation,
	 											 PB01ViewObjForOrgDivisionServiceLocation,
	 											 PB01COREMediatorForOrgDivisionServiceLocation> {

	private static final long serialVersionUID = 4005752379270744L;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01PresenterForOrgDivisionServiceLocationDetailView(@EventBusSingleton(usedFor="uiPresenter") final EventBus eventBus,
																final PB01COREMediatorForOrgDivisionServiceLocation coreMediator) {
		super(eventBus,
			  coreMediator,
			  obj -> PB01ViewObjForOrgDivisionServiceLocation.from(obj));		// factory from
	}
}
