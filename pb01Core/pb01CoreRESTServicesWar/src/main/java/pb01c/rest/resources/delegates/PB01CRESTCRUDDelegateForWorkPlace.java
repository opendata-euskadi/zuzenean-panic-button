package pb01c.rest.resources.delegates;

import pb01a.api.interfaces.PB01ACRUDServicesForWorkPlace;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.org.PB01AWorkPlace;
import pb01c.server.rest.resources.delegates.PB01CRESTCRUDDelegateBaseForOrganizationalEntity;

public class PB01CRESTCRUDDelegateForWorkPlace
	 extends PB01CRESTCRUDDelegateBaseForOrganizationalEntity<PB01AWorkPlaceOID,PB01AWorkPlaceID,PB01AWorkPlace> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CRESTCRUDDelegateForWorkPlace(final PB01ACRUDServicesForWorkPlace crudServices) {
		super(PB01AWorkPlace.class,
			  crudServices);
	}
}
