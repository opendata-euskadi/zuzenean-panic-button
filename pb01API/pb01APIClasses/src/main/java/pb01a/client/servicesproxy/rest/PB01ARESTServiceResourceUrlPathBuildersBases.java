package pb01a.client.servicesproxy.rest;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pb01a.model.oids.PB01AIDs.PB01APersistableObjectID;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import r01f.locale.Language;
import r01f.services.client.servicesproxy.rest.RESTServiceResourceUrlPathBuilders.RESTServiceEndPointUrl;
import r01f.services.client.servicesproxy.rest.RESTServiceResourceUrlPathBuilders.RESTServiceResourceUrlPathBuilderBase;
import r01f.services.client.servicesproxy.rest.RESTServiceResourceUrlPathBuilders.RESTServiceResourceUrlPathBuilderForModelObjectPersistenceBase;
import r01f.types.url.UrlPath;

/**
 * Base types for REST resources path building
 */
@NoArgsConstructor(access=AccessLevel.PRIVATE)
abstract class PB01ARESTServiceResourceUrlPathBuildersBases {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	static class PB01ARESTServiceResourceUrlPathBuilderBase
	     extends RESTServiceResourceUrlPathBuilderBase {
		public PB01ARESTServiceResourceUrlPathBuilderBase(final RESTServiceEndPointUrl endPointUrl,
														 final UrlPath resourceUrlPath) {
			super(endPointUrl,
				  resourceUrlPath);
		}
	}
	static abstract class PB01ARESTServiceResourceUrlPathBuilderForPersistenceBase<O extends PB01APersistableObjectOID>
		 		  extends RESTServiceResourceUrlPathBuilderForModelObjectPersistenceBase<O> {
		public PB01ARESTServiceResourceUrlPathBuilderForPersistenceBase(final RESTServiceEndPointUrl endPointUrl,
																		final UrlPath resource) {
			super(endPointUrl,
				  resource);
		}
	}
	static abstract class PB01ARESTServiceResourceUrlPathBuilderForEntityPersistenceBase<O extends PB01APersistableObjectOID,ID extends PB01APersistableObjectID<O>>
		 		  extends PB01ARESTServiceResourceUrlPathBuilderForPersistenceBase<O> {
		public PB01ARESTServiceResourceUrlPathBuilderForEntityPersistenceBase(final RESTServiceEndPointUrl endPointUrl,
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
