package pb01.ui.vaadin.orgentity.orgdivisionservicelocation;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.google.common.eventbus.EventBus;

import pb01.ui.vaadin.orgentity.PB01PresenterForOrgObjectDetailBase;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.org.X47BOrgDivisionServiceLocation;

@Singleton
public class PB01PresenterForOrgDivisionServiceLocationDetailView
	 extends PB01PresenterForOrgObjectDetailBase<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocation,
	 											 PB01ViewObjForOrgDivisionServiceLocation,
	 											 PB01COREMediatorForOrgDivisionServiceLocation> {

	private static final long serialVersionUID = 4005752379270744L;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01PresenterForOrgDivisionServiceLocationDetailView(@Named("uiPresenterEventBus") final EventBus eventBus,
																final PB01COREMediatorForOrgDivisionServiceLocation coreMediator) {
		super(eventBus,
			  coreMediator,
			  obj -> PB01ViewObjForOrgDivisionServiceLocation.from(obj));		// factory from
	}
}
