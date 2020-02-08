package pb01t.test.entities.mock;

import lombok.RequiredArgsConstructor;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrgDivision;
import pb01a.model.org.PB01AOrgObjectRef;
import pb01a.model.org.PB01AOrganization;
import r01f.locale.Language;
import r01f.locale.LanguageTexts.LangTextNotFoundBehabior;
import r01f.locale.LanguageTextsMapBacked;
import r01f.patterns.Factory;
import r01f.util.types.Strings;

@RequiredArgsConstructor
public class PB01TMockOrgDivisionFactory 
  implements Factory<PB01AOrgDivision> {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final PB01AOrganization _organization;
	
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
		@Override
		public PB01AOrgDivision create() {
			return _buildMockOrgDivision();
		} 
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	private PB01AOrgDivision _buildMockOrgDivision() {
		String randomDiv = _divIds.getOneUnused();
		
		PB01AOrgDivisionOID oid = PB01AOrgDivisionOID.supply();
		PB01AOrgDivision outDiv = new PB01AOrgDivision();
		outDiv.setOid(oid);
		outDiv.setId(PB01AOrgDivisionID.forId(Strings.customized("{}/{}",
															    _organization.getId(),
															    randomDiv)));
		outDiv.setOrgRef(new PB01AOrgObjectRef<PB01AOrganizationOID,PB01AOrganizationID>(_organization.getOid(),_organization.getId()));
		outDiv.setNameByLanguage(new LanguageTextsMapBacked(LangTextNotFoundBehabior.RETURN_NULL)
										   .add(Language.SPANISH,"TEST division " + randomDiv)
										   .add(Language.BASQUE,"[eu] TEST division " + randomDiv));
		return outDiv;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	private final PB01TMockEntityIDProvider _divIds = PB01TMockEntityIDProvider.create()	
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
