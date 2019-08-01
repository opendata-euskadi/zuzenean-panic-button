package pb01.ui.vaadin.orgentity.workplace;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;
import pb01.ui.vaadin.orgentity.PB01COREMediatorForOrganizationalEntityBase;
import r01f.locale.Language;
import r01f.ui.coremediator.UICOREMediatorSubscriber;
import x47b.client.api.X47BPanicButtonClientAPI;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BWorkPlace;
import x47b.model.org.summaries.X47BSummarizedWorkPlace;

@Slf4j
@Singleton
public class PB01COREMediatorForWorkPlace
	 extends PB01COREMediatorForOrganizationalEntityBase<X47BWorkPlaceOID,X47BWorkPlace> {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01COREMediatorForWorkPlace(final X47BPanicButtonClientAPI api) {
		super(api);	// factory
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public X47BWorkPlace load(final X47BWorkPlaceOID oid) {
		return _api.workPlacesAPI()
				   .getForCRUD()
				   .load(oid);
	}
	@Override
	public X47BWorkPlace save(final X47BWorkPlace obj) {
		return _api.workPlacesAPI()
				   .getForCRUD()
				   .save(obj);
	}
	@Override
	public X47BWorkPlace delete(final X47BWorkPlaceOID oid) {
		return _api.workPlacesAPI()
				   .getForCRUD()
				   .delete(oid);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public void loadWorkPlaces(final X47BOrgDivisionServiceLocationOID locOid,
							   final Language lang,
							   final UICOREMediatorSubscriber<Collection<X47BSummarizedWorkPlace>> subscriber) {
		final Collection<X47BSummarizedWorkPlace> locs = _api.workPlacesAPI()
																	.getForFind()
																	.findSummariesByOrgDivisionServiceLocation(locOid,
																											   lang);
		log.debug("...{} workplaces at location with oid={} loaded",
				  locs != null ? locs.size() : 0,
				  locOid);
		subscriber.onSuccess(locs);
	}
}
