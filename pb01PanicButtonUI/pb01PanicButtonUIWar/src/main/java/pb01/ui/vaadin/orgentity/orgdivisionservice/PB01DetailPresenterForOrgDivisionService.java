package pb01.ui.vaadin.orgentity.orgdivisionservice;

import javax.inject.Inject;
import javax.inject.Singleton;

import pb01.ui.vaadin.orgentity.PB01DetailPresenterForOrgEntityBase;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.org.X47BOrgDivisionService;

@Singleton
public class PB01DetailPresenterForOrgDivisionService
	 extends PB01DetailPresenterForOrgEntityBase<X47BOrgDivisionServiceOID,X47BOrgDivisionService,
	 											 PB01ViewObjForOrgDivisionService,
	 											 PB01COREMediatorForOrgDivisionService> {

	private static final long serialVersionUID = 4005752379270744L;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01DetailPresenterForOrgDivisionService(final PB01COREMediatorForOrgDivisionService coreMediator) {
		super(coreMediator,
			  obj -> PB01ViewObjForOrgDivisionService.from(obj));		// factory from
	}
}
