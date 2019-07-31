package x47b.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
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
import x47b.model.metadata.X47BMetaDataForModelObjectBase;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;

@ConvertToDirtyStateTrackable
@ModelObjectData(X47BMetaDataForModelObjectBase.class)
@Accessors(prefix="_")
public abstract class X47BPersistableObjectBase<O extends X47BPersistableObjectOID,ID extends X47BPersistableObjectID<O>,
										   	    SELF_TYPE extends X47BPersistableObjectBase<O,ID,SELF_TYPE>>
	          extends PersistableModelObjectBase<O,SELF_TYPE>
	  	   implements X47BPersistableObject<O,ID> {

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
										    X47BPersistableObjectBase.this.getClass(),_id.asString());
		return SummarizableBuilder.summarizableFrom(sum);
	}
	@Override
	public FullTextSummarizable asFullTextSummarizable() {
		return SummarizableBuilder.fullTextSummarizableFrom(this);
	}
}
