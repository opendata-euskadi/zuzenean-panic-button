package x47b.client.servicesproxy.rest;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import r01f.locale.Language;
import r01f.services.client.servicesproxy.rest.RESTServiceResourceUrlPathBuilders.RESTServiceEndPointUrl;
import r01f.services.client.servicesproxy.rest.RESTServiceResourceUrlPathBuilders.RESTServiceResourceUrlPathBuilderBase;
import r01f.services.client.servicesproxy.rest.RESTServiceResourceUrlPathBuilders.RESTServiceResourceUrlPathBuilderForModelObjectPersistenceBase;
import r01f.types.url.UrlPath;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;

/**
 * Base types for REST resources path building
 */
@NoArgsConstructor(access=AccessLevel.PRIVATE)
abstract class X47BRESTServiceResourceUrlPathBuildersBases {
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	static class X47BRESTServiceResourceUrlPathBuilderBase 
	     extends RESTServiceResourceUrlPathBuilderBase {
		public X47BRESTServiceResourceUrlPathBuilderBase(final RESTServiceEndPointUrl endPointUrl,
														 final UrlPath resourceUrlPath) {
			super(endPointUrl,
				  resourceUrlPath);
		}
	}
	static abstract class X47BRESTServiceResourceUrlPathBuilderForPersistenceBase<O extends X47BPersistableObjectOID>
		 		  extends RESTServiceResourceUrlPathBuilderForModelObjectPersistenceBase<O> {
		public X47BRESTServiceResourceUrlPathBuilderForPersistenceBase(final RESTServiceEndPointUrl endPointUrl,
															   final UrlPath resource) {
			super(endPointUrl,	
				  resource);
		}
	}
	static abstract class X47BRESTServiceResourceUrlPathBuilderForEntityPersistenceBase<O extends X47BPersistableObjectOID,ID extends X47BPersistableObjectID<O>>
		 		  extends X47BRESTServiceResourceUrlPathBuilderForPersistenceBase<O> {
		public X47BRESTServiceResourceUrlPathBuilderForEntityPersistenceBase(final RESTServiceEndPointUrl endPointUrl,
																	 final UrlPath resource) {
			super(endPointUrl,
				  resource);
		}
		public UrlPath pathOfEntityById(final ID id) {
			return this.pathOfAllEntities().joinedWith("byId",id);
		}
		public UrlPath pathOfEntityListByNameInLanguage(final Language lang,
													 	final String name) {
			return this.pathOfEntityList().joinedWith("byNameIn",lang,
					   								  name);
		}
	}
}
