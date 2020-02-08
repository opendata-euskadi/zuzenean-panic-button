package pb01a.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import pb01a.model.metadata.PB01AMetaDataForModelObjectBase;
import pb01a.model.oids.PB01AIDs.PB01APersistableObjectID;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import r01f.aspects.interfaces.dirtytrack.ConvertToDirtyStateTrackable;
import r01f.facets.FullTextSummarizable;
import r01f.facets.Summarizable;
import r01f.facets.builders.SummarizableBuilder;
import r01f.guids.OID;
import r01f.model.PersistableModelObjectBase;
import r01f.model.metadata.annotations.ModelObjectData;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallField.MarshallFieldAsXml;
import r01f.types.summary.Summary;
import r01f.types.summary.SummaryBuilder;

@ConvertToDirtyStateTrackable
@ModelObjectData(PB01AMetaDataForModelObjectBase.class)
@Accessors(prefix="_")
public abstract class PB01APersistableObjectBase<O extends PB01APersistableObjectOID,ID extends PB01APersistableObjectID<O>,
										   	    SELF_TYPE extends PB01APersistableObjectBase<O,ID,SELF_TYPE>>
	          extends PersistableModelObjectBase<O,SELF_TYPE>
	  	   implements PB01APersistableObject<O,ID> {

	private static final long serialVersionUID = 7579054159448752329L;

/////////////////////////////////////////////////////////////////////////////////////////
//  COMMON FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallField(as="id",
			   	   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter protected ID _id;
/////////////////////////////////////////////////////////////////////////////////////////
//  HasID
/////////////////////////////////////////////////////////////////////////////////////////
	@Override @SuppressWarnings("unchecked")
	public void unsafeSetId(final OID id) {
		_id = (ID)id;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	SUMMARIES
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Summarizable asSummarizable() {
		Summary sum = SummaryBuilder.languageInDependent()
									.create("{} object with id={}",
										    PB01APersistableObjectBase.this.getClass(),_id.asString());
		return SummarizableBuilder.summarizableFrom(sum);
	}
	@Override
	public FullTextSummarizable asFullTextSummarizable() {
		return SummarizableBuilder.fullTextSummarizableFrom(this);
	}
}
