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
@Table(name="X47BDIVISIONT00")
	@DiscriminatorValue("DIV")	// see X47BDBEntityForOrganizationalEntityBase
	
@NamedQueries({
	@NamedQuery(name = "X47BDBEntitiesForOrgDivisionsByNameSPANISH",
				query = "SELECT division " +
						  "FROM X47BDBEntityForOrgDivision division " +
						 "WHERE division._nameSpanish LIKE :name "),
	@NamedQuery(name = "X47BDBEntitiesForOrgDivisionsByNameBASQUE",
				query = "SELECT division " +
						  "FROM X47BDBEntityForOrgDivision division " +
						 "WHERE division._nameBasque LIKE :name ")
})
@Accessors(prefix="_")
@NoArgsConstructor
public class X47BDBEntityForOrgDivision
     extends X47BDBEntityForOrganizationalEntityBase {

	private static final long serialVersionUID = -8544023719877260333L;
	
/////////////////////////////////////////////////////////////////////////////////////////
//  BI-DIRECTIONAL RELATIONSHIP division -> service (oneToMany)
//	Beware to update BOTH SIDES of the relationship: http://en.wikibooks.org/wiki/Java_Persistence/Relationships#Object_corruption.2C_one_side_of_the_relationship_is_not_updated_after_updating_the_other_side
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Child services
	 */
	@OneToMany(targetEntity=X47BDBEntityForOrgDivisionService.class,			// not required but informative
			   mappedBy="_orgDivision",										   	// relationship owner
			   cascade={CascadeType.REMOVE},
			   orphanRemoval=true,
			   fetch=FetchType.LAZY)
	@Getter private Collection<X47BDBEntityForOrgDivisionService> _orgDivisionServices;
	
	public void addService(final X47BDBEntityForOrgDivisionService dbService) {	
		if (_orgDivisionServices == null) _orgDivisionServices = Lists.newArrayList();
		_orgDivisionServices.add(dbService);
		if (dbService.getOrgDivision() != this) dbService.setOrgDivision(this);		// update the other side of the relationship
	}
	public boolean containsService(final X47BDBEntityForOrgDivisionService dbService) {
		return _orgDivisionServices != null ? _orgDivisionServices.contains(dbService) : false;
	}
}
