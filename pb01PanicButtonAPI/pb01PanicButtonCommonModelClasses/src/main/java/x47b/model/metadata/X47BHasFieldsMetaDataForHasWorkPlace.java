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
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;

public interface X47BHasFieldsMetaDataForHasWorkPlace 
		 extends HasFieldsMetaData {
/////////////////////////////////////////////////////////////////////////////////////////
// 	SEARCHABLE METADATA
/////////////////////////////////////////////////////////////////////////////////////////
	@Accessors(prefix="_")
	@RequiredArgsConstructor
	public enum SEARCHABLE_METADATA 
	 implements FieldIDToken {
		OID ("workPlaceOid"),
		ID ("workPlaceId");
		
		@Getter private final String _token;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	SERVICE LOCATION	  
/////////////////////////////////////////////////////////////////////////////////////////
	@MetaDataForField(description = {
						@DescInLang(language=Language.SPANISH, value="Identificador único del puesto de trabajo"),
						@DescInLang(language=Language.BASQUE, value="[eu] Work place's unique identifier"),
						@DescInLang(language=Language.ENGLISH, value="Work place's unique identifier")
				     },
				   	 storage = @Storage(indexed=true,
				   			 			stored=true))
	public X47BWorkPlaceOID getWorkPlaceOid();
	
	@MetaDataForField(description = {
						@DescInLang(language=Language.SPANISH, value="Identificador único de negocio del puesto de trabajo"),
						@DescInLang(language=Language.BASQUE, value="[eu] Work place's unique business identifier"),
						@DescInLang(language=Language.ENGLISH, value="Work place's unique business identifier")
				     },
				   	 storage = @Storage(indexed=true,
				   			 			stored=true))
	public X47BWorkPlaceID getWorkPlaceId();
}
