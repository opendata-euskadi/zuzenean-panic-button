package x47b.model.org.summaries;

import x47b.model.oids.X47BIDs.X47BModelObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;
import x47b.model.org.X47BOrganizationalModelObject;

/**
 * Interface for an entity model object summary
 * @param <O>
 * @param <I>
 * @param <M>
 */
public interface X47BSummarizedOrganizationalModelObject<O extends X47BPersistableObjectOID,I extends X47BModelObjectID<O>,M extends X47BOrganizationalModelObject<O,I>>
	     extends X47BSummarizedModelObject<O,I,M> {
	
	public String getName();
	public void setName(final String name);
}
