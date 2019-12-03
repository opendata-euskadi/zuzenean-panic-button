package x47b.db.entities;

import javax.persistence.Cacheable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Entity @Cacheable(false)
@Table(name="X47BWORKPLACET00") 
	@DiscriminatorValue("AG")	// see X47BDBEntityForOrganizationalEntityBase

@NamedQueries({
	@NamedQuery(name = "X47BDBEntitiesForWorkPlacesByNameSPANISH",
				query = "SELECT wp " +
						  "FROM X47BDBEntityForWorkPlace wp " +
						 "WHERE wp._nameSpanish LIKE :name "),
	@NamedQuery(name = "X47BDBEntitiesForWorkPlacesByNameBASQUE",
				query = "SELECT wp " +
						  "FROM X47BDBEntityForWorkPlace wp " +
						 "WHERE wp._nameBasque LIKE :name ")
})
@Accessors(prefix="_")
@NoArgsConstructor
public class X47BDBEntityForWorkPlace
     extends X47BDBEntityForOrganizationalEntityBase {

	private static final long serialVersionUID = 698294185545489256L;
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
}
