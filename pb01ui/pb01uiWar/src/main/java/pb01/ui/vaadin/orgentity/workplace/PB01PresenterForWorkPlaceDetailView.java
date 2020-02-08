package pb01.ui.vaadin.orgentity.workplace;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.common.eventbus.EventBus;

import pb01.ui.vaadin.orgentity.PB01PresenterForOrgObjectDetailBase;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.org.PB01AWorkPlace;
import r01f.inject.annotations.EventBusSingleton;

@Singleton
public class PB01PresenterForWorkPlaceDetailView
	 extends PB01PresenterForOrgObjectDetailBase<PB01AWorkPlaceOID,PB01AWorkPlace,
	 											 PB01ViewObjForWorkPlace,
	 											 PB01COREMediatorForWorkPlace> {

	private static final long serialVersionUID = 4005752379270744L;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01PresenterForWorkPlaceDetailView(@EventBusSingleton(usedFor="uiPresenter") final EventBus eventBus,
											   final PB01COREMediatorForWorkPlace coreMediator) {
		super(eventBus,
			  coreMediator,
			  obj -> PB01ViewObjForWorkPlace.from(obj));		// factory from
	}
}
