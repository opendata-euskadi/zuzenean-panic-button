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
@Table(name="PB01DIVISIONT00")
	@DiscriminatorValue("DIV")	// see PB01CDBEntityForOrganizationalEntityBase

@NamedQueries({
	@NamedQuery(name = "PB01DBEntitiesForOrgDivisionsByNameSPANISH",
				query = "SELECT division " +
						  "FROM PB01CDBEntityForOrgDivision division " +
						 "WHERE division._nameSpanish LIKE :name "),
	@NamedQuery(name = "PB01DBEntitiesForOrgDivisionsByNameBASQUE",
				query = "SELECT division " +
						  "FROM PB01CDBEntityForOrgDivision division " +
						 "WHERE division._nameBasque LIKE :name ")
})
@Accessors(prefix="_")
@NoArgsConstructor
public class PB01CDBEntityForOrgDivision
     extends PB01CDBEntityForOrganizationalEntityBase {

	private static final long serialVersionUID = -8544023719877260333L;

/////////////////////////////////////////////////////////////////////////////////////////
//  BI-DIRECTIONAL RELATIONSHIP division -> service (oneToMany)
//	Beware to update BOTH SIDES of the relationship: http://en.wikibooks.org/wiki/Java_Persistence/Relationships#Object_corruption.2C_one_side_of_the_relationship_is_not_updated_after_updating_the_other_side
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Child services
	 */
	@OneToMany(targetEntity=PB01CDBEntityForOrgDivisionService.class,			// not required but informative
			   mappedBy="_orgDivision",										   	// relationship owner
			   cascade={CascadeType.REMOVE},
			   orphanRemoval=true,
			   fetch=FetchType.LAZY)
	@Getter private Collection<PB01CDBEntityForOrgDivisionService> _orgDivisionServices;

	public void addService(final PB01CDBEntityForOrgDivisionService dbService) {
		if (_orgDivisionServices == null) _orgDivisionServices = Lists.newArrayList();
		_orgDivisionServices.add(dbService);
		if (dbService.getOrgDivision() != this) dbService.setOrgDivision(this);		// update the other side of the relationship
	}
	public boolean containsService(final PB01CDBEntityForOrgDivisionService dbService) {
		return _orgDivisionServices != null ? _orgDivisionServices.contains(dbService) : false;
	}
}
