package x47b.model;
import lombok.Getter;
import lombok.experimental.Accessors;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOIDBase;
import x47b.model.org.X47BOrgDivisionServiceLocation;
import x47b.model.org.X47BWorkPlace;
import x47b.model.search.X47BSearchResultItemForPanicButtonOrganizationalEntity;

@Accessors(prefix="_")
/////////////////////////////////////////////////////////////////////////////////////////)
public class X47BEntitySearchResult {
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private X47BSearchResultItemForPanicButtonOrganizationalEntity _searchResult;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BEntitySearchResult(X47BSearchResultItemForPanicButtonOrganizationalEntity result) {
		_searchResult = result;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Devuelve de OID del puesto de trabajo o de la localizaci�n
	 * @return
	 */
	public X47BPersistableObjectOIDBase getEntityOid() {
		if (_searchResult != null) {
			if (_searchResult.getModelObjectType().equals(X47BWorkPlace.class)) {
				return _searchResult.getWorkPlace().getOid();
			} else if (_searchResult.getModelObjectType().equals(X47BOrgDivisionServiceLocation.class)) {
				return _searchResult.getLocation().getOid();
			}
		}
		return null;
	}


}
