package pb01t.test.entities.mock;

import lombok.RequiredArgsConstructor;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrgDivision;
import pb01a.model.org.PB01AOrgDivisionService;
import pb01a.model.org.PB01AOrgObjectRef;
import pb01a.model.org.PB01AOrganization;
import r01f.locale.Language;
import r01f.locale.LanguageTexts.LangTextNotFoundBehabior;
import r01f.locale.LanguageTextsMapBacked;
import r01f.patterns.Factory;
import r01f.util.types.Strings;

@RequiredArgsConstructor
public class PB01TMockOrgDivisionServiceFactory 
  implements Factory<PB01AOrgDivisionService> {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final PB01AOrganization _organization;
	private final PB01AOrgDivision _division;
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public PB01AOrgDivisionService create() {
		return _buildMockService();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	private PB01AOrgDivisionService _buildMockService() {
		String randomServiceId = _srvIds.getOneUnused();
		
		PB01AOrgDivisionServiceOID oid = PB01AOrgDivisionServiceOID.supply();
		PB01AOrgDivisionService outService = new PB01AOrgDivisionService();
		outService.setOid(oid);
		outService.setId(PB01AOrgDivisionServiceID.forId(Strings.customized("{}/{}",
																		   _division.getId(),	// organization/division
																		   randomServiceId)));
		outService.setOrgRef(new PB01AOrgObjectRef<PB01AOrganizationOID,PB01AOrganizationID>(_organization.getOid(),_organization.getId()));
		outService.setOrgDivisionRef(new PB01AOrgObjectRef<PB01AOrgDivisionOID,PB01AOrgDivisionID>(_division.getOid(),_division.getId()));
		outService.setNameByLanguage(new LanguageTextsMapBacked(LangTextNotFoundBehabior.RETURN_NULL)
											     .add(Language.SPANISH,"TEST service " + randomServiceId)
											     .add(Language.BASQUE,"[eu] TEST service " + randomServiceId));
		return outService;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	private final PB01TMockEntityIDProvider _srvIds = PB01TMockEntityIDProvider.create()	
																				.add("SERVICIO_1")
																				.add("SERVICIO_2")
																				.add("SERVICIO_3")
																				.add("SERVICIO_4")
																				.add("SERVICIO_5")
																				.add("SERVICIO_6")
																				.add("SERVICIO_7")
																				.add("SERVICIO_8")
																				.add("SERVICIO_9")
																				.add("SERVICIO_10")
																				.add("SERVICIO_11")
																				.add("SERVICIO_12")
																				.add("SERVICIO_13")
																				.add("SERVICIO_14")
																				.add("SERVICIO_15")
																				.add("SERVICIO_16")
																				.add("SERVICIO_17")
																				.add("SERVICIO_18")
																						.build();
}
