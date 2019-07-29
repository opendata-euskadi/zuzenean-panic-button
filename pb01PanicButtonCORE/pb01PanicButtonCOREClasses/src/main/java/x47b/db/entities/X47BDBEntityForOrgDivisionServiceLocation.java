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
@Table(name="X47BLOCATIONT00") 
	@DiscriminatorValue("LOC")	// see X47BDBEntityForOrganizationalEntityBase

@NamedQueries({
	@NamedQuery(name = "X47BDBEntitiesForOrgDivisionServiceLocationsByNameSPANISH",
				query = "SELECT location " +
						  "FROM X47BDBEntityForOrgDivisionServiceLocation location " +
						 "WHERE location._nameSpanish LIKE :name "),
	@NamedQuery(name = "X47BDBEntityForOrgDivisionServiceByNameBASQUE",
				query = "SELECT location " +
						  "FROM X47BDBEntityForOrgDivisionServiceLocation location " +
						 "WHERE location._nameBasque LIKE :name ")
})
@Accessors(prefix="_")
@NoArgsConstructor
public class X47BDBEntityForOrgDivisionServiceLocation
     extends X47BDBEntityForOrganizationalEntityBase {
	
	private static final long serialVersionUID = 2768673101219364565L;
	
/////////////////////////////////////////////////////////////////////////////////////////
//  BI-DIRECTIONAL RELATIONSHIP service -> location (oneToMany)
//	Beware to update BOTH SIDES of the relationship: http://en.wikibooks.org/wiki/Java_Persistence/Relationships#Object_corruption.2C_one_side_of_the_relationship_is_not_updated_after_updating_the_other_side
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Child services
	 */
	@OneToMany(targetEntity=X47BDBEntityForWorkPlace.class,		// not required but informative
			   mappedBy="_orgDivisionServiceLocation",		// relationship owner
			   cascade={CascadeType.REMOVE},
			   orphanRemoval=true,
			   fetch=FetchType.LAZY)
	@Getter private Collection<X47BDBEntityForWorkPlace> _workPlaces;
	
	public void addWorkPlace(final X47BDBEntityForWorkPlace dbWorkPlace) {	
		if (_workPlaces == null) _workPlaces = Lists.newArrayList();
		_workPlaces.add(dbWorkPlace);
		if (dbWorkPlace.getOrgDivisionServiceLocation() != this) dbWorkPlace.setOrgDivisionServiceLocation(this);		// update the other side of the relationship
	}
	public boolean containsWorkPlace(final X47BDBEntityForWorkPlace dbWorkPlace) {
		return _workPlaces != null ? _workPlaces.contains(dbWorkPlace) : false;
	}
}
