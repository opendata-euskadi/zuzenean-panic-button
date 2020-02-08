package pb01a.model;

import pb01a.model.oids.PB01AIDs.PB01APersistableObjectID;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import pb01a.model.org.PB01AOrganization;
import pb01a.model.org.PB01AWorkPlace;
import r01f.facets.FullTextSummarizable.HasFullTextSummaryFacet;
import r01f.facets.HasID;
import r01f.facets.Summarizable.HasSummaryFacet;
import r01f.model.IndexableModelObject;
import r01f.model.PersistableModelObject;

/**
 * Interface for every PB01 entity: {@link PB01AOrganization}, {@link PB01AOrgDivisonServiceLocation}, {@link PB01AWorkPlace}
 * @param <O>
 * @param <ID>
 */
public interface PB01APersistableObject<O extends PB01APersistableObjectOID,ID extends PB01APersistableObjectID<O>>
		 extends PersistableModelObject<O>,		// is persistable
     	 		 IndexableModelObject,			// is indexable
     	 		 HasSummaryFacet,				// ... has summary
     	 		 HasFullTextSummaryFacet,		// ... has full text
     	 		 HasID<ID>,
		 		 PB01AModelObject {
	// nothing
}
