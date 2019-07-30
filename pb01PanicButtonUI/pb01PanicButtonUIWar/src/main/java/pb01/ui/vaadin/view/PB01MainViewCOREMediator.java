package pb01.ui.vaadin.view;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;

import r01f.locale.Language;
import r01f.ui.coremediator.UICOREMediatorBase;
import r01f.ui.coremediator.UICOREMediatorSubscriber;
import x47b.client.api.X47BPanicButtonClientAPI;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.summaries.X47BSummarizedOrgDivision;
import x47b.model.org.summaries.X47BSummarizedOrgDivisionService;
import x47b.model.org.summaries.X47BSummarizedOrgDivisionServiceLocation;
import x47b.model.org.summaries.X47BSummarizedOrganization;

@Singleton
public class PB01MainViewCOREMediator
     extends UICOREMediatorBase<X47BPanicButtonClientAPI> {

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01MainViewCOREMediator(final X47BPanicButtonClientAPI api) {
		super(api);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	ORGANIZATIONS
/////////////////////////////////////////////////////////////////////////////////////////
	public void loadAllOrgs(final Language lang,
							final UICOREMediatorSubscriber<Collection<X47BSummarizedOrganization>> subscriber) {
		Collection<X47BSummarizedOrganization> orgs = _api.organizationsAPI()
															.getForFind()
															.findSummaries(lang);
		subscriber.onSuccess(orgs);
	}
	public void loadOrgDivisions(final X47BOrganizationOID orgOid,
								 final Language lang,
								 final UICOREMediatorSubscriber<Collection<X47BSummarizedOrgDivision>> subscriber) {
		Collection<X47BSummarizedOrgDivision> divs = _api.orgDivisionsAPI()
														 .getForFind()
														 .findSummariesByOrganization(orgOid,
																 					  lang);
		subscriber.onSuccess(divs);
	}
	public void loadOrgDivisonServices(final X47BOrgDivisionOID divOid,
									   final Language lang,
									   final UICOREMediatorSubscriber<Collection<X47BSummarizedOrgDivisionService>> subscriber) {
		Collection<X47BSummarizedOrgDivisionService> srvcs = _api.orgDivisionServicesAPI()
																 .getForFind()
																 .findSummariesByOrgDivision(divOid,
																		 					 lang);
		subscriber.onSuccess(srvcs);
	}
	public void loadOrgDivisionServiceLocations(final X47BOrgDivisionServiceOID srvcOid,
												final Language lang,
												final UICOREMediatorSubscriber<Collection<X47BSummarizedOrgDivisionServiceLocation>> subscriber) {
		Collection<X47BSummarizedOrgDivisionServiceLocation> locs = _api.orgDivisionServiceLocationsAPI()
																		.getForFind()
																		.findSummariesByOrgDivisionService(srvcOid,
																										   lang);
		subscriber.onSuccess(locs);
	}
}
