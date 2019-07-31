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
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrgDivision;
import x47b.model.org.X47BOrgDivisionService;
import x47b.model.org.X47BOrgDivisionServiceLocation;
import x47b.model.org.X47BOrganization;
import x47b.model.org.X47BOrganizationalObjectRef;

@RequiredArgsConstructor
public class X47BMockOrgDivisionServiceLocationFactory 
  implements Factory<X47BOrgDivisionServiceLocation> {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final X47BOrganization _organization;
	private final X47BOrgDivision _division;
	private final X47BOrgDivisionService _service;
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public X47BOrgDivisionServiceLocation create() {
		return _buildMockLocation();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	private X47BOrgDivisionServiceLocation _buildMockLocation() {
		String randomLocId = _locIds.getOneUnused();
		
		X47BOrgDivisionServiceLocationOID oid = X47BOrgDivisionServiceLocationOID.supply();
		X47BOrgDivisionServiceLocation outLoc = new X47BOrgDivisionServiceLocation();
		outLoc.setOid(oid);
		outLoc.setId(X47BOrgDivisionServiceLocationID.forId(Strings.customized("{}/{}",
																			   _service.getId(),	// organization/division/service
																			   randomLocId)));
		outLoc.setOrgRef(new X47BOrganizationalObjectRef<X47BOrganizationOID,X47BOrganizationID>(_organization.getOid(),_organization.getId()));
		outLoc.setOrgDivisionRef(new X47BOrganizationalObjectRef<X47BOrgDivisionOID,X47BOrgDivisionID>(_division.getOid(),_division.getId()));
		outLoc.setOrgDivisionServiceRef(new X47BOrganizationalObjectRef<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID>(_service.getOid(),_service.getId()));
		outLoc.setNameByLanguage(new LanguageTextsMapBacked(LangTextNotFoundBehabior.RETURN_NULL)
										     .add(Language.SPANISH,"TEST location " + randomLocId)
										     .add(Language.BASQUE,"[eu] TEST location " + randomLocId));
		return outLoc;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	private final X47BMockEntityIDProvider _locIds = X47BMockEntityIDProvider.create()	
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
