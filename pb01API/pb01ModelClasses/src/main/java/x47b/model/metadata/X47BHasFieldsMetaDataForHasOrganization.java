package x47b.model.metadata;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import r01f.locale.Language;
import r01f.model.metadata.FieldIDToken;
import r01f.model.metadata.HasFieldsMetaData;
import r01f.model.metadata.annotations.DescInLang;
import r01f.model.metadata.annotations.MetaDataForField;
import r01f.model.metadata.annotations.Storage;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;

public interface X47BHasFieldsMetaDataForHasOrganization
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
	public X47BOrganizationOID getOrganizationOid();

	@MetaDataForField(description = {
						@DescInLang(language=Language.SPANISH, value="Identificador único de negocio de la organización"),
						@DescInLang(language=Language.BASQUE, value="[eu] Organization's unique business identifier"),
						@DescInLang(language=Language.ENGLISH, value="Organization's unique business identifier")
				     },
				   	 storage = @Storage(indexed=true,
				   			 			stored=true))
	public X47BOrganizationID getOrganizationId();
}
