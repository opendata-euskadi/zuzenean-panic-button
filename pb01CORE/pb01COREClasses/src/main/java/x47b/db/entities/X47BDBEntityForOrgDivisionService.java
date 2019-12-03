package x47b.db.entities;

import java.util.Collection;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.collect.Lists;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Entity @Cacheable(false)
@Table(name="X47BSERVICET00") 
	@DiscriminatorValue("SRV")	// see X47BDBEntityForOrganizationalEntityBase

@NamedQueries({
	@NamedQuery(name = "X47BDBEntitiesForOrgDivisionServicesByNameSPANISH",
				query = "SELECT service " +
						  "FROM X47BDBEntityForOrgDivisionService service " +
						 "WHERE service._nameSpanish LIKE :name "),
	@NamedQuery(name = "X47BDBEntitiesForOrgDivisionServicesByNameBASQUE",
				query = "SELECT service " +
						  "FROM X47BDBEntityForOrgDivisionService service " +
						 "WHERE service._nameBasque LIKE :name ")
})
@Accessors(prefix="_")
@NoArgsConstructor
public class X47BDBEntityForOrgDivisionService
     extends X47BDBEntityForOrganizationalEntityBase {

	private static final long serialVersionUID = 698294185545489256L;
	
/////////////////////////////////////////////////////////////////////////////////////////
//  BI-DIRECTIONAL RELATIONSHIP service -> location (oneToMany)
//	Beware to update BOTH SIDES of the relationship: http://en.wikibooks.org/wiki/Java_Persistence/Relationships#Object_corruption.2C_one_side_of_the_relationship_is_not_updated_after_updating_the_other_side
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Child services
	 */
	@OneToMany(targetEntity=X47BDBEntityForOrgDivisionServiceLocation.class,	// not required but informative
			   mappedBy="_orgDivisionService",									// relationship owner
			   cascade={CascadeType.REMOVE},
			   orphanRemoval=true,
			   fetch=FetchType.LAZY)
	@Getter private Collection<X47BDBEntityForOrgDivisionServiceLocation> _orgDivisionServiceLocations;
	
	public void addLocation(final X47BDBEntityForOrgDivisionServiceLocation dbLocation) {	
		if (_orgDivisionServiceLocations == null) _orgDivisionServiceLocations = Lists.newArrayList();
		_orgDivisionServiceLocations.add(dbLocation);
		if (dbLocation.getOrgDivisionService() != this) dbLocation.setOrgDivisionService(this);		// update the other side of the relationship
	}
	public boolean containsLocation(final X47BDBEntityForOrgDivisionServiceLocation dbLocation) {
		return _orgDivisionServiceLocations != null ? _orgDivisionServiceLocations.contains(dbLocation) : false;
	}
}
