package pb01.ui.vaadin.orgentity.orgdivisionservice;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;
import pb01.ui.vaadin.orgentity.PB01COREMediatorForOrganizationalEntityBase;
import pb01a.client.api.PB01APanicButtonClientAPI;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.org.PB01AOrgDivisionService;
import pb01a.model.org.summaries.PB01ASummarizedOrgDivisionService;
import r01f.locale.Language;
import r01f.ui.coremediator.UICOREMediatorSubscriber;

@Slf4j
@Singleton
public class PB01COREMediatorForOrgDivisionService
	 extends PB01COREMediatorForOrganizationalEntityBase<PB01AOrgDivisionServiceOID,PB01AOrgDivisionService> {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01COREMediatorForOrgDivisionService(final PB01APanicButtonClientAPI api) {
		super(api);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public PB01AOrgDivisionService load(final PB01AOrgDivisionServiceOID oid) {
		return _api.orgDivisionServicesAPI()
				   .getForCRUD()
				   .load(oid);
	}
	@Override
	public PB01AOrgDivisionService save(final PB01AOrgDivisionService obj) {
		return _api.orgDivisionServicesAPI()
				   .getForCRUD()
				   .save(obj);
	}
	@Override
	public PB01AOrgDivisionService delete(final PB01AOrgDivisionServiceOID oid) {
		return _api.orgDivisionServicesAPI()
				   .getForCRUD()
				   .delete(oid);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public void loadOrgDivisonServices(final PB01AOrgDivisionOID divOid,
									   final Language lang,
									   final UICOREMediatorSubscriber<Collection<PB01ASummarizedOrgDivisionService>> subscriber) {
		Collection<PB01ASummarizedOrgDivisionService> srvcs = _api.orgDivisionServicesAPI()
																 .getForFind()
																 .findSummariesByOrgDivision(divOid,
																		 					 lang);
		log.debug("...{} services of division with oid={} loaded",
				  srvcs != null ? srvcs.size() : 0,
				  divOid);
		subscriber.onSuccess(srvcs);
	}
}
