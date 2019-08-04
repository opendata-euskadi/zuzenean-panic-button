package pb01.ui.vaadin.orgentity.workplace;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.google.common.eventbus.EventBus;

import pb01.ui.vaadin.orgentity.PB01PresenterForOrgObjectDetailBase;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BWorkPlace;

@Singleton
public class PB01PresenterForWorkPlaceDetailView
	 extends PB01PresenterForOrgObjectDetailBase<X47BWorkPlaceOID,X47BWorkPlace,
	 											 PB01ViewObjForWorkPlace,
	 											 PB01COREMediatorForWorkPlace> {

	private static final long serialVersionUID = 4005752379270744L;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01PresenterForWorkPlaceDetailView(@Named("uiPresenterEventBus") final EventBus eventBus,
											   final PB01COREMediatorForWorkPlace coreMediator) {
		super(eventBus,
			  coreMediator,
			  obj -> PB01ViewObjForWorkPlace.from(obj));		// factory from
	}
}
