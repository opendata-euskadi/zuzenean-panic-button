package x47b.model.org.summaries;

import r01f.model.SummarizedModelObject;
import x47b.model.X47BEntityObject;
import x47b.model.oids.X47BIDs.X47BModelObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;

/**
 * Interface for an entity model object summary
 * @param <O>
 * @param <I>
 * @param <M>
 */
public interface X47BSummarizedModelObject<O extends X47BPersistableObjectOID,I extends X47BModelObjectID<O>,M extends X47BEntityObject<O,I>>
	     extends SummarizedModelObject<M> {
	
	public O getOid();
	public void setOid(O oid);
	
	public I getId();
	public void setId(I id);
}
