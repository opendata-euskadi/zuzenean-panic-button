package pb01.ui.vaadin.orgentity.orgdivisionservice;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;
import pb01.ui.vaadin.orgentity.PB01COREMediatorForOrganizationalEntityBase;
import r01f.locale.Language;
import r01f.ui.coremediator.UICOREMediatorSubscriber;
import x47b.client.api.X47BPanicButtonClientAPI;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.org.X47BOrgDivisionService;
import x47b.model.org.summaries.X47BSummarizedOrgDivisionService;

@Slf4j
@Singleton
public class PB01COREMediatorForOrgDivisionService
	 extends PB01COREMediatorForOrganizationalEntityBase<X47BOrgDivisionServiceOID,X47BOrgDivisionService> {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01COREMediatorForOrgDivisionService(final X47BPanicButtonClientAPI api) {
		super(api);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public X47BOrgDivisionService load(final X47BOrgDivisionServiceOID oid) {
		return _api.orgDivisionServicesAPI()
				   .getForCRUD()
				   .load(oid);
	}
	@Override
	public X47BOrgDivisionService save(final X47BOrgDivisionService obj) {
		return _api.orgDivisionServicesAPI()
				   .getForCRUD()
				   .save(obj);
	}
	@Override
	public X47BOrgDivisionService delete(final X47BOrgDivisionServiceOID oid) {
		return _api.orgDivisionServicesAPI()
				   .getForCRUD()
				   .delete(oid);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public void loadOrgDivisonServices(final X47BOrgDivisionOID divOid,
									   final Language lang,
									   final UICOREMediatorSubscriber<Collection<X47BSummarizedOrgDivisionService>> subscriber) {
		Collection<X47BSummarizedOrgDivisionService> srvcs = _api.orgDivisionServicesAPI()
																 .getForFind()
																 .findSummariesByOrgDivision(divOid,
																		 					 lang);
		log.debug("...{} services of division with oid={} loaded",
				  srvcs != null ? srvcs.size() : 0,
				  divOid);
		subscriber.onSuccess(srvcs);
	}
}
