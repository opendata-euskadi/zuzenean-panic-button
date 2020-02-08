package pb01a.model.metadata;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import r01f.locale.Language;
import r01f.model.metadata.FieldIDToken;
import r01f.model.metadata.HasFieldsMetaData;
import r01f.model.metadata.annotations.DescInLang;
import r01f.model.metadata.annotations.MetaDataForField;
import r01f.model.metadata.annotations.Storage;

public interface PB01AHasFieldsMetaDataForHasOrgDivision
		 extends HasFieldsMetaData {
/////////////////////////////////////////////////////////////////////////////////////////
// 	SEARCHABLE METADATA
/////////////////////////////////////////////////////////////////////////////////////////
	@Accessors(prefix="_")
	@RequiredArgsConstructor
	public enum SEARCHABLE_METADATA
	 implements FieldIDToken {
		OID ("orgDivisionOid"),
		ID ("orgDivisionId");

		@Getter private final String _token;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	DIVISION
/////////////////////////////////////////////////////////////////////////////////////////
	@MetaDataForField(description = {
						@DescInLang(language=Language.SPANISH, value="Identificador único de la división de la organización"),
						@DescInLang(language=Language.BASQUE, value="[eu] Organization's division's unique identifier"),
						@DescInLang(language=Language.ENGLISH, value="Organization's division's unique identifier")
				     },
				   	 storage = @Storage(indexed=true,
				   			 			stored=true))
	public PB01AOrgDivisionOID getOrgDivisionOid();

	@MetaDataForField(description = {
						@DescInLang(language=Language.SPANISH, value="Identificador �nico de negocio de la división de la organización"),
						@DescInLang(language=Language.BASQUE, value="[eu] Organization's division's unique business identifier"),
						@DescInLang(language=Language.ENGLISH, value="Organization's division's unique business identifier")
				     },
				   	 storage = @Storage(indexed=true,
				   			 			stored=true))
	public PB01AOrgDivisionID getOrgDivisionId();
}
