package x47b.model;

import r01f.facets.FullTextSummarizable.HasFullTextSummaryFacet;
import r01f.facets.Summarizable.HasSummaryFacet;
import r01f.facets.HasID;
import r01f.model.IndexableModelObject;
import r01f.model.PersistableModelObject;
import x47b.model.oids.X47BIDs.X47BModelObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;
import x47b.model.org.X47BOrganization;
import x47b.model.org.X47BWorkPlace;

/**
 * Interface for every X47B entity: {@link X47BOrganization}, {@link X47BLocation}, {@link X47BWorkPlace}
 * @param <O>
 * @param <ID>
 */
public interface X47BEntityObject<O extends X47BPersistableObjectOID,ID extends X47BModelObjectID<O>> 
		 extends PersistableModelObject<O>,		// is persistable
     	 		 IndexableModelObject,			// is indexable
     	 		 HasSummaryFacet,				// ... has summary
     	 		 HasFullTextSummaryFacet,		// ... has full text
     	 		 HasID<ID>,
		 		 X47BModelObject {
	// nothing
}
