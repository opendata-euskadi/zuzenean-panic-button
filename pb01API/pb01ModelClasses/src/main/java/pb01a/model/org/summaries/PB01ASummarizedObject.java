package pb01a.model.org.summaries;

import pb01a.model.PB01APersistableObject;
import pb01a.model.oids.PB01AIDs.PB01APersistableObjectID;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import r01f.model.SummarizedModelObject;

/**
 * Interface for an entity model object summary
 * @param <O>
 * @param <I>
 * @param <M>
 */
public interface PB01ASummarizedObject<O extends PB01APersistableObjectOID,I extends PB01APersistableObjectID<O>,M extends PB01APersistableObject<O,I>>
	     extends SummarizedModelObject<M> {
	
	public O getOid();
	public void setOid(O oid);
	
	public I getId();
	public void setId(I id);
}
