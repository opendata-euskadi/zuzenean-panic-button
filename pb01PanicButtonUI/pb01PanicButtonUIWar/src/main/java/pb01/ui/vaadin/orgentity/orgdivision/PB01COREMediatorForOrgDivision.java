package pb01.ui.vaadin.orgentity.orgdivision;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;
import pb01.ui.vaadin.orgentity.PB01COREMediatorForOrganizationalEntityBase;
import r01f.locale.Language;
import r01f.ui.coremediator.UICOREMediatorSubscriber;
import x47b.client.api.X47BPanicButtonClientAPI;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrgDivision;
import x47b.model.org.summaries.X47BSummarizedOrgDivision;

@Slf4j
@Singleton
public class PB01COREMediatorForOrgDivision
	 extends PB01COREMediatorForOrganizationalEntityBase<X47BOrgDivisionOID,X47BOrgDivision> {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01COREMediatorForOrgDivision(final X47BPanicButtonClientAPI api) {
		super(api);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public X47BOrgDivision load(final X47BOrgDivisionOID oid) {
		return _api.orgDivisionsAPI()
				   .getForCRUD()
				   .load(oid);
	}
	@Override
	public X47BOrgDivision save(final X47BOrgDivision obj) {
		return _api.orgDivisionsAPI()
				   .getForCRUD()
				   .save(obj);
	}
	@Override
	public X47BOrgDivision delete(final X47BOrgDivisionOID oid) {
		return _api.orgDivisionsAPI()
				   .getForCRUD()
				   .delete(oid);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public void loadOrgDivisions(final X47BOrganizationOID orgOid,
								 final Language lang,
								 final UICOREMediatorSubscriber<Collection<X47BSummarizedOrgDivision>> subscriber) {
		Collection<X47BSummarizedOrgDivision> divs = _api.orgDivisionsAPI()
														 .getForFind()
														 .findSummariesByOrganization(orgOid,
																 					  lang);
		log.debug("...{} divisions of org with oid={} loaded",
				  divs != null ? divs.size() : 0,
				  orgOid);
		subscriber.onSuccess(divs);
	}
}
