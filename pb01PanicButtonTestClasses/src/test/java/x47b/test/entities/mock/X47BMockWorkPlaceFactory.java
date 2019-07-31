package x47b.test.entities.mock;

import lombok.RequiredArgsConstructor;
import r01f.locale.Language;
import r01f.locale.LanguageTexts.LangTextNotFoundBehabior;
import r01f.locale.LanguageTextsMapBacked;
import r01f.patterns.Factory;
import r01f.util.types.Strings;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BOrgDivision;
import x47b.model.org.X47BOrgDivisionService;
import x47b.model.org.X47BOrgDivisionServiceLocation;
import x47b.model.org.X47BOrganization;
import x47b.model.org.X47BOrganizationalObjectRef;
import x47b.model.org.X47BWorkPlace;
import x47b.test.X47BMockEntityIDProvider;

@RequiredArgsConstructor
public class X47BMockWorkPlaceFactory 
  implements Factory<X47BWorkPlace> {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final X47BOrganization _organization;
	private final X47BOrgDivision _division;
	private final X47BOrgDivisionService _service;
	private final X47BOrgDivisionServiceLocation _location;
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public X47BWorkPlace create() {
		return _buildMockWorkPlace();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	private X47BWorkPlace _buildMockWorkPlace() {
		String randomWorkPlace = _workPlacesIds.getOneUnused();
		
		X47BWorkPlaceOID oid = X47BWorkPlaceOID.supply();
		X47BWorkPlace outWorkPlace = new X47BWorkPlace();
		outWorkPlace.setOid(oid);
		outWorkPlace.setId(X47BWorkPlaceID.forId(Strings.customized("{}/{}",
															_location.getId(),
															randomWorkPlace)));
		outWorkPlace.setOrgRef(new X47BOrganizationalObjectRef<X47BOrganizationOID,X47BOrganizationID>(_organization.getOid(),_organization.getId()));
		outWorkPlace.setOrgDivisionRef(new X47BOrganizationalObjectRef<X47BOrgDivisionOID,X47BOrgDivisionID>(_division.getOid(),_division.getId()));
		outWorkPlace.setOrgDivisionServiceRef(new X47BOrganizationalObjectRef<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID>(_service.getOid(),_service.getId()));
		outWorkPlace.setOrgDivisionServiceLocationRef(new X47BOrganizationalObjectRef<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID>(_location.getOid(),_location.getId()));
		outWorkPlace.setNameByLanguage(new LanguageTextsMapBacked(LangTextNotFoundBehabior.RETURN_NULL)
										     .add(Language.SPANISH,"TEST WorkPlace " + randomWorkPlace)
										     .add(Language.BASQUE,"[eu] TEST WorkPlace " + randomWorkPlace));
		outWorkPlace.setPhones(X47BMockAuxObjsFactories.createPhoneList());
		outWorkPlace.setEmails(X47BMockAuxObjsFactories.createEMailList());
		return outWorkPlace;
	}
	private final X47BMockEntityIDProvider _workPlacesIds = X47BMockEntityIDProvider.create()	
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
