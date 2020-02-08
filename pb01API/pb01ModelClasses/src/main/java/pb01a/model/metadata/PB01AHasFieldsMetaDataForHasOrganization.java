package pb01a.model.metadata;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import r01f.locale.Language;
import r01f.model.metadata.FieldIDToken;
import r01f.model.metadata.HasFieldsMetaData;
import r01f.model.metadata.annotations.DescInLang;
import r01f.model.metadata.annotations.MetaDataForField;
import r01f.model.metadata.annotations.Storage;

public interface PB01AHasFieldsMetaDataForHasOrganization
		 extends HasFieldsMetaData {
/////////////////////////////////////////////////////////////////////////////////////////
// 	SEARCHABLE METADATA
/////////////////////////////////////////////////////////////////////////////////////////
	@Accessors(prefix="_")
	@RequiredArgsConstructor
	public enum SEARCHABLE_METADATA
	 implements FieldIDToken {
		OID ("organizationOid"),
		ID ("organizationId");

		@Getter private final String _token;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	ORGANIZATION
/////////////////////////////////////////////////////////////////////////////////////////
	@MetaDataForField(description = {
						@DescInLang(language=Language.SPANISH, value="Identificador único de la organización"),
						@DescInLang(language=Language.BASQUE, value="[eu] Organization's unique identifier"),
						@DescInLang(language=Language.ENGLISH, value="Organization's unique identifier")
				     },
				   	 storage = @Storage(indexed=true,
				   			 			stored=true))
	public PB01AOrganizationOID getOrganizationOid();

	@MetaDataForField(description = {
						@DescInLang(language=Language.SPANISH, value="Identificador único de negocio de la organización"),
						@DescInLang(language=Language.BASQUE, value="[eu] Organization's unique business identifier"),
						@DescInLang(language=Language.ENGLISH, value="Organization's unique business identifier")
				     },
				   	 storage = @Storage(indexed=true,
				   			 			stored=true))
	public PB01AOrganizationID getOrganizationId();
}
