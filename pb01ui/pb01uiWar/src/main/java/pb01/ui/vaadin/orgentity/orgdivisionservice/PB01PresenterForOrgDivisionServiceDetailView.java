package pb01.ui.vaadin.orgentity.orgdivisionservice;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.common.eventbus.EventBus;

import pb01.ui.vaadin.orgentity.PB01PresenterForOrgObjectDetailBase;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.org.PB01AOrgDivisionService;
import r01f.inject.annotations.EventBusSingleton;

@Singleton
public class PB01PresenterForOrgDivisionServiceDetailView
	 extends PB01PresenterForOrgObjectDetailBase<PB01AOrgDivisionServiceOID,PB01AOrgDivisionService,
	 											 PB01ViewObjForOrgDivisionService,
	 											 PB01COREMediatorForOrgDivisionService> {

	private static final long serialVersionUID = 4005752379270744L;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01PresenterForOrgDivisionServiceDetailView(@EventBusSingleton(usedFor="uiPresenter") final EventBus eventBus,
														final PB01COREMediatorForOrgDivisionService coreMediator) {
		super(eventBus,
			  coreMediator,
			  obj -> PB01ViewObjForOrgDivisionService.from(obj));		// factory from
	}
}
