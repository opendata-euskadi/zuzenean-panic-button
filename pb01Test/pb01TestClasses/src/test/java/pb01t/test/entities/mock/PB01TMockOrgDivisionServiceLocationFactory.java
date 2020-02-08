package pb01t.test.entities.mock;

import lombok.RequiredArgsConstructor;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrgDivision;
import pb01a.model.org.PB01AOrgDivisionService;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import pb01a.model.org.PB01AOrgObjectRef;
import pb01a.model.org.PB01AOrganization;
import r01f.locale.Language;
import r01f.locale.LanguageTexts.LangTextNotFoundBehabior;
import r01f.locale.LanguageTextsMapBacked;
import r01f.patterns.Factory;
import r01f.util.types.Strings;

@RequiredArgsConstructor
public class PB01TMockOrgDivisionServiceLocationFactory 
  implements Factory<PB01AOrgDivisionServiceLocation> {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final PB01AOrganization _organization;
	private final PB01AOrgDivision _division;
	private final PB01AOrgDivisionService _service;
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public PB01AOrgDivisionServiceLocation create() {
		return _buildMockLocation();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	private PB01AOrgDivisionServiceLocation _buildMockLocation() {
		String randomLocId = _locIds.getOneUnused();
		
		PB01AOrgDivisionServiceLocationOID oid = PB01AOrgDivisionServiceLocationOID.supply();
		PB01AOrgDivisionServiceLocation outLoc = new PB01AOrgDivisionServiceLocation();
		outLoc.setOid(oid);
		outLoc.setId(PB01AOrgDivisionServiceLocationID.forId(Strings.customized("{}/{}",
																			   _service.getId(),	// organization/division/service
																			   randomLocId)));
		outLoc.setOrgRef(new PB01AOrgObjectRef<PB01AOrganizationOID,PB01AOrganizationID>(_organization.getOid(),_organization.getId()));
		outLoc.setOrgDivisionRef(new PB01AOrgObjectRef<PB01AOrgDivisionOID,PB01AOrgDivisionID>(_division.getOid(),_division.getId()));
		outLoc.setOrgDivisionServiceRef(new PB01AOrgObjectRef<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID>(_service.getOid(),_service.getId()));
		outLoc.setNameByLanguage(new LanguageTextsMapBacked(LangTextNotFoundBehabior.RETURN_NULL)
										     .add(Language.SPANISH,"TEST location " + randomLocId)
										     .add(Language.BASQUE,"[eu] TEST location " + randomLocId));
		return outLoc;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	private final PB01TMockEntityIDProvider _locIds = PB01TMockEntityIDProvider.create()	
																				.add("BILBAO")
																				.add("DONOSTIA")
																				.add("VITORIA")
																				.add("AJO")
																				.add("SANTANDER")
																				.add("SANTO�A")
																				.add("GORLIZ")
																				.add("ME�AKA")
																				.add("PAMPLONA")
																				.add("MADRID")
																				.add("VALENCIA")
																				.add("CULLERA")
																				.add("LOGRO�O")
																				.add("LEGAZPI")
																				.add("AULESTI")
																				.add("SESTAO")
																				.add("PALENCIA")
																				.add("BURGOS")
																						.build();
}
