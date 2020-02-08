package pb01t.test.entities.mock;

import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrganization;
import r01f.locale.Language;
import r01f.locale.LanguageTexts.LangTextNotFoundBehabior;
import r01f.locale.LanguageTextsMapBacked;
import r01f.patterns.Factory;
import r01f.util.types.Strings;

public class PB01TMockOrganizationFactory 
  implements Factory<PB01AOrganization> {
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public PB01AOrganization create() {
		return _buildMockOrganization();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  ENTITY PROVIDER
/////////////////////////////////////////////////////////////////////////////////////////
	private PB01AOrganization _buildMockOrganization() {
		String randomOrg = _orgIds.getOneUnused();
		
		PB01AOrganizationOID oid = PB01AOrganizationOID.supply();
		PB01AOrganization outOrg = new PB01AOrganization();
		outOrg.setOid(oid);
		outOrg.setId(PB01AOrganizationID.forId(Strings.customized("{}",
																 randomOrg)));
		outOrg.setNameByLanguage(new LanguageTextsMapBacked(LangTextNotFoundBehabior.RETURN_NULL)
										   .add(Language.SPANISH,"TEST organization " + randomOrg)
										   .add(Language.BASQUE,"[eu] TEST organization " + randomOrg));
		
		return outOrg;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	private final PB01TMockEntityIDProvider _orgIds = PB01TMockEntityIDProvider.create()	
																				.add("IBERDROLA")
																				.add("LANBIDE")
																				.add("SPRI")
																				.add("EJIE")
																				.add("IZENPE")
																				.add("IVAP")
																				.add("AVPD")
																				.add("KZGUNEA")
																				.add("LANTIK")
																				.add("IFZE")
																				.add("CIMUBISA")
																				.add("CCASA")
																				.add("BBVA")
																				.add("BANCO_SANTANDER")
																				.add("BANCO_POPULAR")
																				.add("TELEFONICA")
																				.add("HP")
																				.add("IBM")
																				.add("GOOGLE")
																				.add("AMAZON")
																				.add("SAMSUNG")
																				.add("DELL")
																				.add("LENOVO")
																				.build();
}
