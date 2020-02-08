package pb01a.model.org.summaries;

import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgObjectID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgObjectOID;
import pb01a.model.org.PB01AOrganizationalPersistableObject;

/**
 * Interface for an entity model object summary
 * @param <O>
 * @param <I>
 * @param <M>
 */
public interface PB01ASummarizedOrganizationalObject<O extends PB01AOrgObjectOID,I extends PB01AOrgObjectID<O>,M extends PB01AOrganizationalPersistableObject<O,I>>
	     extends PB01ASummarizedObject<O,I,M> {

	public String getName();
	public void setName(final String name);
}
