package pb01c.db.entities;

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
@Table(name="PB01LOCATIONT00")
	@DiscriminatorValue("LOC")	// see PB01CDBEntityForOrganizationalEntityBase

@NamedQueries({
	@NamedQuery(name = "PB01DBEntitiesForOrgDivisionServiceLocationsByNameSPANISH",
				query = "SELECT location " +
						  "FROM PB01CDBEntityForOrgDivisionServiceLocation location " +
						 "WHERE location._nameSpanish LIKE :name "),
	@NamedQuery(name = "PB01DBEntityForOrgDivisionServiceByNameBASQUE",
				query = "SELECT location " +
						  "FROM PB01CDBEntityForOrgDivisionServiceLocation location " +
						 "WHERE location._nameBasque LIKE :name ")
})
@Accessors(prefix="_")
@NoArgsConstructor
public class PB01CDBEntityForOrgDivisionServiceLocation
     extends PB01CDBEntityForOrganizationalEntityBase {

	private static final long serialVersionUID = 2768673101219364565L;

/////////////////////////////////////////////////////////////////////////////////////////
//  BI-DIRECTIONAL RELATIONSHIP service -> location (oneToMany)
//	Beware to update BOTH SIDES of the relationship: http://en.wikibooks.org/wiki/Java_Persistence/Relationships#Object_corruption.2C_one_side_of_the_relationship_is_not_updated_after_updating_the_other_side
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Child services
	 */
	@OneToMany(targetEntity=PB01CDBEntityForWorkPlace.class,		// not required but informative
			   mappedBy="_orgDivisionServiceLocation",		// relationship owner
			   cascade={CascadeType.REMOVE},
			   orphanRemoval=true,
			   fetch=FetchType.LAZY)
	@Getter private Collection<PB01CDBEntityForWorkPlace> _workPlaces;

	public void addWorkPlace(final PB01CDBEntityForWorkPlace dbWorkPlace) {
		if (_workPlaces == null) _workPlaces = Lists.newArrayList();
		_workPlaces.add(dbWorkPlace);
		if (dbWorkPlace.getOrgDivisionServiceLocation() != this) dbWorkPlace.setOrgDivisionServiceLocation(this);		// update the other side of the relationship
	}
	public boolean containsWorkPlace(final PB01CDBEntityForWorkPlace dbWorkPlace) {
		return _workPlaces != null ? _workPlaces.contains(dbWorkPlace) : false;
	}
}
