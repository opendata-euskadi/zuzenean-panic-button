package pb01.ui.vaadin.orgentity.orgdivision;

import javax.inject.Inject;
import javax.inject.Singleton;

import pb01.ui.vaadin.orgentity.PB01DetailPresenterForOrgEntityBase;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.org.X47BOrgDivision;

@Singleton
public class PB01DetailPresenterForOrgDivision
	 extends PB01DetailPresenterForOrgEntityBase<X47BOrgDivisionOID,X47BOrgDivision,
	 											 PB01ViewObjForOrgDivision,
	 											 PB01COREMediatorForOrgDivision> {

	private static final long serialVersionUID = 4005752379270744L;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01DetailPresenterForOrgDivision(final PB01COREMediatorForOrgDivision coreMediator) {
		super(coreMediator,
			  obj -> PB01ViewObjForOrgDivision.from(obj));		// factory from
	}
}
