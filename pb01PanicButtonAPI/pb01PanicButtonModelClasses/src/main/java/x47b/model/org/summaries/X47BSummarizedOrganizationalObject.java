package x47b.model.org.summaries;

import x47b.model.oids.X47BOrganizationalIDs.X47BOrgObjectID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgObjectOID;
import x47b.model.org.X47BOrganizationalPersistableObject;

/**
 * Interface for an entity model object summary
 * @param <O>
 * @param <I>
 * @param <M>
 */
public interface X47BSummarizedOrganizationalObject<O extends X47BOrgObjectOID,I extends X47BOrgObjectID<O>,M extends X47BOrganizationalPersistableObject<O,I>>
	     extends X47BSummarizedObject<O,I,M> {

	public String getName();
	public void setName(final String name);
}
