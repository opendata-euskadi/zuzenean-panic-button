package pb01t.test.entities.mock;

import lombok.RequiredArgsConstructor;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.org.PB01AOrgDivision;
import pb01a.model.org.PB01AOrgDivisionService;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import pb01a.model.org.PB01AOrgObjectRef;
import pb01a.model.org.PB01AOrganization;
import pb01a.model.org.PB01AWorkPlace;
import pb01t.test.PB01TMockEntityIDProvider;
import r01f.locale.Language;
import r01f.locale.LanguageTexts.LangTextNotFoundBehabior;
import r01f.locale.LanguageTextsMapBacked;
import r01f.patterns.Factory;
import r01f.util.types.Strings;

@RequiredArgsConstructor
public class PB01TMockWorkPlaceFactory 
  implements Factory<PB01AWorkPlace> {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final PB01AOrganization _organization;
	private final PB01AOrgDivision _division;
	private final PB01AOrgDivisionService _service;
	private final PB01AOrgDivisionServiceLocation _location;
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public PB01AWorkPlace create() {
		return _buildMockWorkPlace();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	private PB01AWorkPlace _buildMockWorkPlace() {
		String randomWorkPlace = _workPlacesIds.getOneUnused();
		
		PB01AWorkPlaceOID oid = PB01AWorkPlaceOID.supply();
		PB01AWorkPlace outWorkPlace = new PB01AWorkPlace();
		outWorkPlace.setOid(oid);
		outWorkPlace.setId(PB01AWorkPlaceID.forId(Strings.customized("{}/{}",
															_location.getId(),
															randomWorkPlace)));
		outWorkPlace.setOrgRef(new PB01AOrgObjectRef<PB01AOrganizationOID,PB01AOrganizationID>(_organization.getOid(),_organization.getId()));
		outWorkPlace.setOrgDivisionRef(new PB01AOrgObjectRef<PB01AOrgDivisionOID,PB01AOrgDivisionID>(_division.getOid(),_division.getId()));
		outWorkPlace.setOrgDivisionServiceRef(new PB01AOrgObjectRef<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID>(_service.getOid(),_service.getId()));
		outWorkPlace.setOrgDivisionServiceLocationRef(new PB01AOrgObjectRef<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocationID>(_location.getOid(),_location.getId()));
		outWorkPlace.setNameByLanguage(new LanguageTextsMapBacked(LangTextNotFoundBehabior.RETURN_NULL)
										     .add(Language.SPANISH,"TEST WorkPlace " + randomWorkPlace)
										     .add(Language.BASQUE,"[eu] TEST WorkPlace " + randomWorkPlace));
		outWorkPlace.setPhones(PB01TMockAuxObjsFactories.createPhoneList());
		outWorkPlace.setEmails(PB01TMockAuxObjsFactories.createEMailList());
		return outWorkPlace;
	}
	private final PB01TMockEntityIDProvider _workPlacesIds = PB01TMockEntityIDProvider.create()	
																					.add("1")
																					.add("2")
																					.add("3")
																					.add("4")
																					.add("5")
																					.add("6")
																					.add("7")
																					.add("8")
																					.add("9")
																					.add("10")
																					.add("11")
																					.add("12")
																					.add("13")
																					.add("14")
																					.add("15")
																					.add("16")
																					.add("17")
																					.add("18")
																					.add("19")
																					.add("20")
																					.add("21")
																					.add("22")
																					.add("23")
																					.add("24")
																					.add("25")
																					.add("26")
																					.add("27")
																					.add("28")
																					.add("29")
																					.add("30")
																					.build();
}
