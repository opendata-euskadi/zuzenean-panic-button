package pb01.ui.vaadin.orgentity.organization;

import javax.inject.Inject;
import javax.inject.Singleton;

import pb01.ui.vaadin.orgentity.PB01DetailPresenterForOrgEntityBase;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrganization;

@Singleton
public class PB01DetailPresenterForOrganization
	 extends PB01DetailPresenterForOrgEntityBase<X47BOrganizationOID,X47BOrganization,
	 											 PB01ViewObjForOrganization,
	 											 PB01COREMediatorForOrganization> {

	private static final long serialVersionUID = 4005752379270744L;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01DetailPresenterForOrganization(final PB01COREMediatorForOrganization coreMediator) {
		super(coreMediator,
			  obj -> PB01ViewObjForOrganization.from(obj));		// factory from
	}
}
