package x47b.test.entities.mock;

import lombok.RequiredArgsConstructor;
import r01f.locale.Language;
import r01f.locale.LanguageTexts.LangTextNotFoundBehabior;
import r01f.locale.LanguageTextsMapBacked;
import r01f.patterns.Factory;
import r01f.util.types.Strings;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrgDivision;
import x47b.model.org.X47BOrganization;
import x47b.model.org.X47BOrganizationalModelObjectRef;

@RequiredArgsConstructor
public class X47BMockOrgDivisionFactory 
  implements Factory<X47BOrgDivision> {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final X47BOrganization _organization;
	
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
		@Override
		public X47BOrgDivision create() {
			return _buildMockOrgDivision();
		} 
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	private X47BOrgDivision _buildMockOrgDivision() {
		String randomDiv = _divIds.getOneUnused();
		
		X47BOrgDivisionOID oid = X47BOrgDivisionOID.supply();
		X47BOrgDivision outDiv = new X47BOrgDivision();
		outDiv.setOid(oid);
		outDiv.setId(X47BOrgDivisionID.forId(Strings.customized("{}/{}",
															    _organization.getId(),
															    randomDiv)));
		outDiv.setOrgRef(new X47BOrganizationalModelObjectRef<X47BOrganizationOID,X47BOrganizationID>(_organization.getOid(),_organization.getId()));
		outDiv.setNameByLanguage(new LanguageTextsMapBacked(LangTextNotFoundBehabior.RETURN_NULL)
										   .add(Language.SPANISH,"TEST division " + randomDiv)
										   .add(Language.BASQUE,"[eu] TEST division " + randomDiv));
		return outDiv;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	private final X47BMockEntityIDProvider _divIds = X47BMockEntityIDProvider.create()	
																				.add("DIVISION_1")
																				.add("DIVISION_2")
																				.add("DIVISION_3")
																				.add("DIVISION_4")
																				.add("DIVISION_5")
																				.add("DIVISION_6")
																				.add("DIVISION_7")
																				.add("DIVISION_8")
																				.add("DIVISION_9")
																				.add("DIVISION_10")
																				.add("DIVISION_11")
																				.add("DIVISION_12")
																				.add("DIVISION_13")
																				.add("DIVISION_14")
																				.add("DIVISION_15")
																				.add("DIVISION_16")
																				.add("DIVISION_17")
																				.add("DIVISION_18")
																				.build();
}
