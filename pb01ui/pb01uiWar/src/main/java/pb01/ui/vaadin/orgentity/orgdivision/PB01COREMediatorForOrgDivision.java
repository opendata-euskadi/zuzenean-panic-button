package pb01.ui.vaadin.orgentity.orgdivision;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;
import pb01.ui.vaadin.orgentity.PB01COREMediatorForOrganizationalEntityBase;
import pb01a.client.api.PB01APanicButtonClientAPI;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrgDivision;
import pb01a.model.org.summaries.PB01ASummarizedOrgDivision;
import r01f.locale.Language;
import r01f.ui.coremediator.UICOREMediatorSubscriber;

@Slf4j
@Singleton
public class PB01COREMediatorForOrgDivision
	 extends PB01COREMediatorForOrganizationalEntityBase<PB01AOrgDivisionOID,PB01AOrgDivision> {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01COREMediatorForOrgDivision(final PB01APanicButtonClientAPI api) {
		super(api);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public PB01AOrgDivision load(final PB01AOrgDivisionOID oid) {
		return _api.orgDivisionsAPI()
				   .getForCRUD()
				   .load(oid);
	}
	@Override
	public PB01AOrgDivision save(final PB01AOrgDivision obj) {
		return _api.orgDivisionsAPI()
				   .getForCRUD()
				   .save(obj);
	}
	@Override
	public PB01AOrgDivision delete(final PB01AOrgDivisionOID oid) {
		return _api.orgDivisionsAPI()
				   .getForCRUD()
				   .delete(oid);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public void loadOrgDivisions(final PB01AOrganizationOID orgOid,
								 final Language lang,
								 final UICOREMediatorSubscriber<Collection<PB01ASummarizedOrgDivision>> subscriber) {
		Collection<PB01ASummarizedOrgDivision> divs = _api.orgDivisionsAPI()
														 .getForFind()
														 .findSummariesByOrganization(orgOid,
																 					  lang);
		log.debug("...{} divisions of org with oid={} loaded",
				  divs != null ? divs.size() : 0,
				  orgOid);
		subscriber.onSuccess(divs);
	}
}
