package pb01.ui.vaadin.orgentity.orgdivision;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.common.eventbus.EventBus;

import pb01.ui.vaadin.orgentity.PB01PresenterForOrgObjectDetailBase;
import r01f.inject.annotations.EventBusSingleton;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.org.X47BOrgDivision;

@Singleton
public class PB01PresenterForOrgDivisionDetailView
	 extends PB01PresenterForOrgObjectDetailBase<X47BOrgDivisionOID,X47BOrgDivision,
	 											 PB01ViewObjForOrgDivision,
	 											 PB01COREMediatorForOrgDivision> {

	private static final long serialVersionUID = 4005752379270744L;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01PresenterForOrgDivisionDetailView(@EventBusSingleton(usedFor="uiPresenter") final EventBus eventBus,
												 final PB01COREMediatorForOrgDivision coreMediator) {
		super(eventBus,
			  coreMediator,
			  obj -> PB01ViewObjForOrgDivision.from(obj));		// factory from
	}
}
