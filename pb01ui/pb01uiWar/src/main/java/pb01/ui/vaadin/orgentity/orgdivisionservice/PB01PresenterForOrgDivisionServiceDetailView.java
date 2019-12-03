package pb01.ui.vaadin.orgentity.orgdivisionservice;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.common.eventbus.EventBus;

import pb01.ui.vaadin.orgentity.PB01PresenterForOrgObjectDetailBase;
import r01f.inject.annotations.EventBusSingleton;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.org.X47BOrgDivisionService;

@Singleton
public class PB01PresenterForOrgDivisionServiceDetailView
	 extends PB01PresenterForOrgObjectDetailBase<X47BOrgDivisionServiceOID,X47BOrgDivisionService,
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
