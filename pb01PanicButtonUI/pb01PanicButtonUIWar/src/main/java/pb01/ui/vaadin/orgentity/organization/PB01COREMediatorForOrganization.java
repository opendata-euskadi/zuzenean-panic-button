package pb01.ui.vaadin.orgentity.organization;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;
import pb01.ui.vaadin.orgentity.PB01COREMediatorForOrganizationalEntityBase;
import r01f.locale.Language;
import r01f.ui.coremediator.UICOREMediatorSubscriber;
import x47b.client.api.X47BPanicButtonClientAPI;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrganization;
import x47b.model.org.summaries.X47BSummarizedOrganization;

@Slf4j
@Singleton
public class PB01COREMediatorForOrganization
	 extends PB01COREMediatorForOrganizationalEntityBase<X47BOrganizationOID,X47BOrganization> {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01COREMediatorForOrganization(final X47BPanicButtonClientAPI api) {
		super(api,
			  () -> new X47BOrganization());	// factory
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public X47BOrganization load(final X47BOrganizationOID oid) {
		return _api.organizationsAPI()
				   .getForCRUD()
				   .load(oid);
	}
	@Override
	public X47BOrganization save(final X47BOrganization obj) {
		return _api.organizationsAPI()
				   .getForCRUD()
				   .save(obj);
	}
	@Override
	public X47BOrganization delete(final X47BOrganizationOID oid) {
		return _api.organizationsAPI()
				   .getForCRUD()
				   .delete(oid);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public void loadAllOrgs(final Language lang,
							final UICOREMediatorSubscriber<Collection<X47BSummarizedOrganization>> subscriber) {
		Collection<X47BSummarizedOrganization> orgs = _api.organizationsAPI()
															.getForFind()
															.findSummaries(lang);
		log.debug("...{} organizations loaded",
				  orgs != null ? orgs.size() : 0);
		subscriber.onSuccess(orgs);
	}
}
