package pb01.ui.vaadin.orgentity.organization;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.common.eventbus.EventBus;

import pb01.ui.vaadin.orgentity.PB01PresenterForOrgObjectDetailBase;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrganization;
import r01f.inject.annotations.EventBusSingleton;

@Singleton
public class PB01PresenterForOrganizationDetailView
	 extends PB01PresenterForOrgObjectDetailBase<PB01AOrganizationOID,PB01AOrganization,
	 											 PB01ViewObjForOrganization,
	 											 PB01COREMediatorForOrganization> {

	private static final long serialVersionUID = 4005752379270744L;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01PresenterForOrganizationDetailView(@EventBusSingleton(usedFor="uiPresenter") final EventBus eventBus,
												  final PB01COREMediatorForOrganization coreMediator) {
		super(eventBus,
			  coreMediator,
			  obj -> PB01ViewObjForOrganization.from(obj));		// factory from
	}
}
