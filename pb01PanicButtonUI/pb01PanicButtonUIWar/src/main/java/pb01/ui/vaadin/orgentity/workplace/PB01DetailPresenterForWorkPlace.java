package pb01.ui.vaadin.orgentity.workplace;

import javax.inject.Inject;
import javax.inject.Singleton;

import pb01.ui.vaadin.orgentity.PB01DetailPresenterForOrgEntityBase;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BWorkPlace;

@Singleton
public class PB01DetailPresenterForWorkPlace
	 extends PB01DetailPresenterForOrgEntityBase<X47BWorkPlaceOID,X47BWorkPlace,
	 											 PB01ViewObjForWorkPlace,
	 											 PB01COREMediatorForWorkPlace> {

	private static final long serialVersionUID = 4005752379270744L;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01DetailPresenterForWorkPlace(final PB01COREMediatorForWorkPlace coreMediator) {
		super(coreMediator,
			  obj -> PB01ViewObjForWorkPlace.from(obj));		// factory from
	}
}
