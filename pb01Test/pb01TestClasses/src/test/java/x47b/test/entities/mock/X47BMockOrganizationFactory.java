package x47b.test.entities.mock;

import r01f.locale.Language;
import r01f.locale.LanguageTexts.LangTextNotFoundBehabior;
import r01f.locale.LanguageTextsMapBacked;
import r01f.patterns.Factory;
import r01f.util.types.Strings;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrganization;

public class X47BMockOrganizationFactory 
  implements Factory<X47BOrganization> {
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public X47BOrganization create() {
		return _buildMockOrganization();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  ENTITY PROVIDER
/////////////////////////////////////////////////////////////////////////////////////////
	private X47BOrganization _buildMockOrganization() {
		String randomOrg = _orgIds.getOneUnused();
		
		X47BOrganizationOID oid = X47BOrganizationOID.supply();
		X47BOrganization outOrg = new X47BOrganization();
		outOrg.setOid(oid);
		outOrg.setId(X47BOrganizationID.forId(Strings.customized("{}",
																 randomOrg)));
		outOrg.setNameByLanguage(new LanguageTextsMapBacked(LangTextNotFoundBehabior.RETURN_NULL)
										   .add(Language.SPANISH,"TEST organization " + randomOrg)
										   .add(Language.BASQUE,"[eu] TEST organization " + randomOrg));
		
		return outOrg;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	private final X47BMockEntityIDProvider _orgIds = X47BMockEntityIDProvider.create()	
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
