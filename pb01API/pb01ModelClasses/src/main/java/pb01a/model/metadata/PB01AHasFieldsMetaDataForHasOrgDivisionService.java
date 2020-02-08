package pb01a.model.metadata;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import r01f.locale.Language;
import r01f.model.metadata.FieldIDToken;
import r01f.model.metadata.HasFieldsMetaData;
import r01f.model.metadata.annotations.DescInLang;
import r01f.model.metadata.annotations.MetaDataForField;
import r01f.model.metadata.annotations.Storage;

public interface PB01AHasFieldsMetaDataForHasOrgDivisionService
		 extends HasFieldsMetaData {
/////////////////////////////////////////////////////////////////////////////////////////
// 	SEARCHABLE METADATA
/////////////////////////////////////////////////////////////////////////////////////////
	@Accessors(prefix="_")
	@RequiredArgsConstructor
	public enum SEARCHABLE_METADATA
	 implements FieldIDToken {
		OID ("orgDivisionServiceOid"),
		ID ("orgDivisionServiceId");

		@Getter private final String _token;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	SERVICE
/////////////////////////////////////////////////////////////////////////////////////////
	@MetaDataForField(description = {
						@DescInLang(language=Language.SPANISH, value="Identificador único del servicio de la división de la organización"),
						@DescInLang(language=Language.BASQUE, value="[eu] Organization's division's service's unique identifier"),
						@DescInLang(language=Language.ENGLISH, value="Organization's division's service's unique identifier")
				     },
				   	 storage = @Storage(indexed=true,
				   			 			stored=true))
	public PB01AOrgDivisionServiceOID getOrgDivisionServiceOid();

	@MetaDataForField(description = {
						@DescInLang(language=Language.SPANISH, value="Identificador �nico de negocio del servicio de la divisi�n de la organización"),
						@DescInLang(language=Language.BASQUE, value="[eu] Organization's division's service's unique business identifier"),
						@DescInLang(language=Language.ENGLISH, value="Organization's division's service's unique business identifier")
				     },
				   	 storage = @Storage(indexed=true,
				   			 			stored=true))
	public PB01AOrgDivisionServiceID getOrgDivisionServiceId();
}
