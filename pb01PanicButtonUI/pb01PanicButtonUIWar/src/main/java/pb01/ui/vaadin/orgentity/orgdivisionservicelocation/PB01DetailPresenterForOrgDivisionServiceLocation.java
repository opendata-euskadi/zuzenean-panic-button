package pb01.ui.vaadin.orgentity.orgdivisionservicelocation;

import javax.inject.Inject;
import javax.inject.Singleton;

import pb01.ui.vaadin.orgentity.PB01DetailPresenterForOrgEntityBase;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.org.X47BOrgDivisionServiceLocation;

@Singleton
public class PB01DetailPresenterForOrgDivisionServiceLocation
	 extends PB01DetailPresenterForOrgEntityBase<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocation,
	 											 PB01ViewObjForOrgDivisionServiceLocation,
	 											 PB01COREMediatorForOrgDivisionServiceLocation> {

	private static final long serialVersionUID = 4005752379270744L;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01DetailPresenterForOrgDivisionServiceLocation(final PB01COREMediatorForOrgDivisionServiceLocation coreMediator) {
		super(coreMediator,
			  obj -> PB01ViewObjForOrgDivisionServiceLocation.from(obj));		// factory from
	}
}
