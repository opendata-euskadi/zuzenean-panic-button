package x47b.test.entities.mock;

import lombok.RequiredArgsConstructor;
import r01f.locale.Language;
import r01f.locale.LanguageTexts.LangTextNotFoundBehabior;
import r01f.locale.LanguageTextsMapBacked;
import r01f.patterns.Factory;
import r01f.util.types.Strings;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrgDivision;
import x47b.model.org.X47BOrgDivisionService;
import x47b.model.org.X47BOrganization;
import x47b.model.org.X47BOrgObjectRef;

@RequiredArgsConstructor
public class X47BMockOrgDivisionServiceFactory 
  implements Factory<X47BOrgDivisionService> {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final X47BOrganization _organization;
	private final X47BOrgDivision _division;
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public X47BOrgDivisionService create() {
		return _buildMockService();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	private X47BOrgDivisionService _buildMockService() {
		String randomServiceId = _srvIds.getOneUnused();
		
		X47BOrgDivisionServiceOID oid = X47BOrgDivisionServiceOID.supply();
		X47BOrgDivisionService outService = new X47BOrgDivisionService();
		outService.setOid(oid);
		outService.setId(X47BOrgDivisionServiceID.forId(Strings.customized("{}/{}",
																		   _division.getId(),	// organization/division
																		   randomServiceId)));
		outService.setOrgRef(new X47BOrgObjectRef<X47BOrganizationOID,X47BOrganizationID>(_organization.getOid(),_organization.getId()));
		outService.setOrgDivisionRef(new X47BOrgObjectRef<X47BOrgDivisionOID,X47BOrgDivisionID>(_division.getOid(),_division.getId()));
		outService.setNameByLanguage(new LanguageTextsMapBacked(LangTextNotFoundBehabior.RETURN_NULL)
											     .add(Language.SPANISH,"TEST service " + randomServiceId)
											     .add(Language.BASQUE,"[eu] TEST service " + randomServiceId));
		return outService;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	private final X47BMockEntityIDProvider _srvIds = X47BMockEntityIDProvider.create()	
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
