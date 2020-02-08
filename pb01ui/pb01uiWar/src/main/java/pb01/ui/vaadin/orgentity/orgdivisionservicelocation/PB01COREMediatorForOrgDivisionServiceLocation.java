package pb01.ui.vaadin.orgentity.orgdivisionservicelocation;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;
import pb01.ui.vaadin.orgentity.PB01COREMediatorForOrganizationalEntityBase;
import pb01a.client.api.PB01APanicButtonClientAPI;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import pb01a.model.org.summaries.PB01ASummarizedOrgDivisionServiceLocation;
import r01f.locale.Language;
import r01f.ui.coremediator.UICOREMediatorSubscriber;

@Slf4j
@Singleton
public class PB01COREMediatorForOrgDivisionServiceLocation
	 extends PB01COREMediatorForOrganizationalEntityBase<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocation> {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01COREMediatorForOrgDivisionServiceLocation(final PB01APanicButtonClientAPI api) {
		super(api);	// factory
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public PB01AOrgDivisionServiceLocation load(final PB01AOrgDivisionServiceLocationOID oid) {
		return _api.orgDivisionServiceLocationsAPI()
				   .getForCRUD()
				   .load(oid);
	}
	@Override
	public PB01AOrgDivisionServiceLocation save(final PB01AOrgDivisionServiceLocation obj) {
		return _api.orgDivisionServiceLocationsAPI()
				   .getForCRUD()
				   .save(obj);
	}
	@Override
	public PB01AOrgDivisionServiceLocation delete(final PB01AOrgDivisionServiceLocationOID oid) {
		return _api.orgDivisionServiceLocationsAPI()
				   .getForCRUD()
				   .delete(oid);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public void loadOrgDivisionServiceLocations(final PB01AOrgDivisionServiceOID srvcOid,
												final Language lang,
												final UICOREMediatorSubscriber<Collection<PB01ASummarizedOrgDivisionServiceLocation>> subscriber) {
		Collection<PB01ASummarizedOrgDivisionServiceLocation> locs = _api.orgDivisionServiceLocationsAPI()
																		.getForFind()
																		.findSummariesByOrgDivisionService(srvcOid,
																										   lang);
		log.debug("...{} locations of service with oid={} loaded",
				  locs != null ? locs.size() : 0,
				  srvcOid);
		subscriber.onSuccess(locs);
	}
}
