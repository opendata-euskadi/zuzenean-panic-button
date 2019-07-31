package x47b.model.org.summaries;

import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;
import x47b.model.org.X47BOrganizationalPersistableObject;

/**
 * Interface for an entity model object summary
 * @param <O>
 * @param <I>
 * @param <M>
 */
public interface X47BSummarizedOrganizationalObject<O extends X47BPersistableObjectOID,I extends X47BPersistableObjectID<O>,M extends X47BOrganizationalPersistableObject<O,I>>
	     extends X47BSummarizedObject<O,I,M> {
	
	public String getName();
	public void setName(final String name);
}
