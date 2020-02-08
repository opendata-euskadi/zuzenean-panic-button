package pb01.ui.vaadin.orgentity.workplace;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;
import pb01.ui.vaadin.orgentity.PB01COREMediatorForOrganizationalEntityBase;
import pb01a.client.api.PB01APanicButtonClientAPI;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.org.PB01AWorkPlace;
import pb01a.model.org.summaries.PB01ASummarizedWorkPlace;
import r01f.locale.Language;
import r01f.ui.coremediator.UICOREMediatorSubscriber;

@Slf4j
@Singleton
public class PB01COREMediatorForWorkPlace
	 extends PB01COREMediatorForOrganizationalEntityBase<PB01AWorkPlaceOID,PB01AWorkPlace> {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01COREMediatorForWorkPlace(final PB01APanicButtonClientAPI api) {
		super(api);	// factory
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public PB01AWorkPlace load(final PB01AWorkPlaceOID oid) {
		return _api.workPlacesAPI()
				   .getForCRUD()
				   .load(oid);
	}
	@Override
	public PB01AWorkPlace save(final PB01AWorkPlace obj) {
		return _api.workPlacesAPI()
				   .getForCRUD()
				   .save(obj);
	}
	@Override
	public PB01AWorkPlace delete(final PB01AWorkPlaceOID oid) {
		return _api.workPlacesAPI()
				   .getForCRUD()
				   .delete(oid);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public void loadWorkPlaces(final PB01AOrgDivisionServiceLocationOID locOid,
							   final Language lang,
							   final UICOREMediatorSubscriber<Collection<PB01ASummarizedWorkPlace>> subscriber) {
		final Collection<PB01ASummarizedWorkPlace> locs = _api.workPlacesAPI()
																	.getForFind()
																	.findSummariesByOrgDivisionServiceLocation(locOid,
																											   lang);
		log.debug("...{} workplaces at location with oid={} loaded",
				  locs != null ? locs.size() : 0,
				  locOid);
		subscriber.onSuccess(locs);
	}
}
