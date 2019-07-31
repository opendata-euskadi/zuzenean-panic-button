package pb01.ui.vaadin.orgentity.orgdivisionservicelocation;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;
import pb01.ui.vaadin.orgentity.PB01COREMediatorForOrganizationalEntityBase;
import r01f.locale.Language;
import r01f.ui.coremediator.UICOREMediatorSubscriber;
import x47b.client.api.X47BPanicButtonClientAPI;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.org.X47BOrgDivisionServiceLocation;
import x47b.model.org.summaries.X47BSummarizedOrgDivisionServiceLocation;

@Slf4j
@Singleton
public class PB01COREMediatorForOrgDivisionServiceLocation
	 extends PB01COREMediatorForOrganizationalEntityBase<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocation> {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01COREMediatorForOrgDivisionServiceLocation(final X47BPanicButtonClientAPI api) {
		super(api,
			  () -> new X47BOrgDivisionServiceLocation());	// factory
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public X47BOrgDivisionServiceLocation load(final X47BOrgDivisionServiceLocationOID oid) {
		return _api.orgDivisionServiceLocationsAPI()
				   .getForCRUD()
				   .load(oid);
	}
	@Override
	public X47BOrgDivisionServiceLocation save(final X47BOrgDivisionServiceLocation obj) {
		return _api.orgDivisionServiceLocationsAPI()
				   .getForCRUD()
				   .save(obj);
	}
	@Override
	public X47BOrgDivisionServiceLocation delete(final X47BOrgDivisionServiceLocationOID oid) {
		return _api.orgDivisionServiceLocationsAPI()
				   .getForCRUD()
				   .delete(oid);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public void loadOrgDivisionServiceLocations(final X47BOrgDivisionServiceOID srvcOid,
												final Language lang,
												final UICOREMediatorSubscriber<Collection<X47BSummarizedOrgDivisionServiceLocation>> subscriber) {
		Collection<X47BSummarizedOrgDivisionServiceLocation> locs = _api.orgDivisionServiceLocationsAPI()
																		.getForFind()
																		.findSummariesByOrgDivisionService(srvcOid,
																										   lang);
		log.debug("...{} locations of service with oid={} loaded",
				  locs != null ? locs.size() : 0,
				  srvcOid);
		subscriber.onSuccess(locs);
	}
}
