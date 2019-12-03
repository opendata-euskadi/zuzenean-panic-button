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
@Table(name="X47BORGANIZATIONT00")
	@DiscriminatorValue("ORG")	// see X47BDBEntityForOrganizationalEntityBase

@NamedQueries({
	@NamedQuery(name = "X47BDBEntitiesForOrganizationsByNameSPANISH",
				query = "SELECT org " +
						  "FROM X47BDBEntityForOrganization org " +
						 "WHERE org._nameSpanish LIKE :name "),
	@NamedQuery(name = "X47BDBEntitiesForOrganizationsByNameBASQUE",
				query = "SELECT org " +
						  "FROM X47BDBEntityForOrganization org " +
						 "WHERE org._nameBasque LIKE :name ")
})
@Accessors(prefix="_")
@NoArgsConstructor
public class X47BDBEntityForOrganization
     extends X47BDBEntityForOrganizationalEntityBase {

	private static final long serialVersionUID = -5447136725316833669L;
/////////////////////////////////////////////////////////////////////////////////////////
//  BI-DIRECTIONAL RELATIONSHIP Organization -> Division (oneToMany)
//	Beware to update BOTH SIDES of the relationship: http://en.wikibooks.org/wiki/Java_Persistence/Relationships#Object_corruption.2C_one_side_of_the_relationship_is_not_updated_after_updating_the_other_side
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Child divisions
	 */
	@OneToMany(targetEntity=X47BDBEntityForOrgDivision.class,		// not required but informative
			   mappedBy="_organization",							// relationship owner
			   cascade={CascadeType.REMOVE},
			   orphanRemoval=true,
			   fetch=FetchType.LAZY)
	@Getter private Collection<X47BDBEntityForOrgDivision> _orgDivisions;
	
	public void addOrgDivision(final X47BDBEntityForOrgDivision orgDivision) {	
		if (_orgDivisions == null) _orgDivisions = Lists.newArrayList();
		_orgDivisions.add(orgDivision);
		if (orgDivision.getOrganization() != this) orgDivision.setOrganization(this);		// update the other side of the relationship
	}
	public boolean containsOrgDivision(final X47BDBEntityForOrgDivision orgDivision) {
		return _orgDivisions != null ? _orgDivisions.contains(orgDivision) : false;
	}
}
