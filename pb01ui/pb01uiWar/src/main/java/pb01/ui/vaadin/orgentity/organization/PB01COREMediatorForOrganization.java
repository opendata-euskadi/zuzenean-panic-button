package pb01.ui.vaadin.orgentity.organization;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;
import pb01.ui.vaadin.orgentity.PB01COREMediatorForOrganizationalEntityBase;
import pb01a.client.api.PB01APanicButtonClientAPI;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrganization;
import pb01a.model.org.summaries.PB01ASummarizedOrganization;
import r01f.locale.Language;
import r01f.ui.coremediator.UICOREMediatorSubscriber;

@Slf4j
@Singleton
public class PB01COREMediatorForOrganization
	 extends PB01COREMediatorForOrganizationalEntityBase<PB01AOrganizationOID,PB01AOrganization> {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01COREMediatorForOrganization(final PB01APanicButtonClientAPI api) {
		super(api);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public PB01AOrganization load(final PB01AOrganizationOID oid) {
		return _api.organizationsAPI()
				   .getForCRUD()
				   .load(oid);
	}
	@Override
	public PB01AOrganization save(final PB01AOrganization obj) {
		return _api.organizationsAPI()
				   .getForCRUD()
				   .save(obj);
	}
	@Override
	public PB01AOrganization delete(final PB01AOrganizationOID oid) {
		return _api.organizationsAPI()
				   .getForCRUD()
				   .delete(oid);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public void loadAllOrgs(final Language lang,
							final UICOREMediatorSubscriber<Collection<PB01ASummarizedOrganization>> subscriber) {
		Collection<PB01ASummarizedOrganization> orgs = _api.organizationsAPI()
															.getForFind()
															.findSummaries(lang);
		log.debug("...{} organizations loaded",
				  orgs != null ? orgs.size() : 0);
		subscriber.onSuccess(orgs);
	}
}
