package pb01c.db.entities;

import javax.persistence.Cacheable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Entity @Cacheable(false)
@Table(name="PB01WORKPLACET00")
	@DiscriminatorValue("AG")	// see PB01CDBEntityForOrganizationalEntityBase

@NamedQueries({
	@NamedQuery(name = "PB01DBEntitiesForWorkPlacesByNameSPANISH",
				query = "SELECT wp " +
						  "FROM PB01CDBEntityForWorkPlace wp " +
						 "WHERE wp._nameSpanish LIKE :name "),
	@NamedQuery(name = "PB01DBEntitiesForWorkPlacesByNameBASQUE",
				query = "SELECT wp " +
						  "FROM PB01CDBEntityForWorkPlace wp " +
						 "WHERE wp._nameBasque LIKE :name ")
})
@Accessors(prefix="_")
@NoArgsConstructor
public class PB01CDBEntityForWorkPlace
     extends PB01CDBEntityForOrganizationalEntityBase {

	private static final long serialVersionUID = 698294185545489256L;
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
}
