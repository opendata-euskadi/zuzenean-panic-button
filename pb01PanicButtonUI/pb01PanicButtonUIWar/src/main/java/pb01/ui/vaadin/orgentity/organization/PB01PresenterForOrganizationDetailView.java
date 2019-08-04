package pb01.ui.vaadin.orgentity.organization;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.google.common.eventbus.EventBus;

import pb01.ui.vaadin.orgentity.PB01PresenterForOrgObjectDetailBase;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrganization;

@Singleton
public class PB01PresenterForOrganizationDetailView
	 extends PB01PresenterForOrgObjectDetailBase<X47BOrganizationOID,X47BOrganization,
	 											 PB01ViewObjForOrganization,
	 											 PB01COREMediatorForOrganization> {

	private static final long serialVersionUID = 4005752379270744L;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01PresenterForOrganizationDetailView(@Named("uiPresenterEventBus") final EventBus eventBus,
												  final PB01COREMediatorForOrganization coreMediator) {
		super(eventBus,
			  coreMediator,
			  obj -> PB01ViewObjForOrganization.from(obj));		// factory from
	}
}
