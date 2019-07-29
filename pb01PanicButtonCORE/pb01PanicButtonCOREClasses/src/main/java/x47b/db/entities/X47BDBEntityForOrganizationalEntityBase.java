package x47b.db.entities;

import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.guids.OID;
import r01f.locale.Language;
import r01f.locale.LanguageTexts;
import r01f.locale.LanguageTexts.LangTextNotFoundBehabior;
import r01f.locale.LanguageTextsMapBacked;
import r01f.persistence.db.DBEntityHasModelObjectDescriptor;
import r01f.persistence.db.entities.DBEntityBase;
import r01f.persistence.db.entities.DBEntityForModelObject;
import r01f.persistence.db.entities.primarykeys.DBPrimaryKeyForModelObject;
import r01f.persistence.db.entities.primarykeys.DBPrimaryKeyForModelObjectImpl;
import x47b.model.org.X47BOrgDivision;
import x47b.model.org.X47BOrgDivisionService;
import x47b.model.org.X47BOrganization;


@MappedSuperclass
@Entity @Cacheable(false)
@Table(name="X47BENTITYT00")
	@IdClass(DBPrimaryKeyForModelObjectImpl.class)

@Inheritance(strategy=InheritanceType.JOINED)
	@DiscriminatorColumn(name="TYPE",discriminatorType=DiscriminatorType.STRING,length=3)

@NamedQueries({
	// Find by id
	@NamedQuery(name = "X47BDBOrganizationalEntityById",
				query = "SELECT entity " +
						  "FROM X47BDBEntityForOrganizationalEntityBase entity " +
						 "WHERE TYPE(entity) = :dbType " + 
						   "AND entity._id = :id "),
	// Find all organizations
	@NamedQuery(name = "X47BDBEntitiesForOrganization",
				query = "SELECT entity " +
						  "FROM X47BDBEntityForOrganizationalEntityBase entity " +
						 "WHERE TYPE(entity) = X47BDBEntityForOrganization"),
	// Find all organization's divisions
	@NamedQuery(name = "X47BDBEntitiesForDivisionsByOrganization",
				query = "SELECT entity " +
						  "FROM X47BDBEntityForOrganizationalEntityBase entity " +
						 "WHERE TYPE(entity) = X47BDBEntityForOrgDivision " +
						   "AND entity._organizationOid = :org"),
	// Find all division's services
	@NamedQuery(name = "X47BDBEntitiesForServicesByDivision",
				query = "SELECT entity " +
						  "FROM X47BDBEntityForOrganizationalEntityBase entity " +
						 "WHERE TYPE(entity) = X47BDBEntityForOrgDivisionService " + 
						   "AND entity._orgDivisionOid = :division"),
	// Find all service's location
	@NamedQuery(name = "X47BDBEntitiesForLocationsByService",
				query = "SELECT entity " +
						  "FROM X47BDBEntityForOrganizationalEntityBase entity " +
						 "WHERE TYPE(entity) = X47BDBEntityForOrgDivisionServiceLocation " + 
						   "AND entity._orgDivisionServiceOid = :service"),
	// Find all location's workplaces
	@NamedQuery(name = "X47BDBEntitiesForWorkPlacesByLocation",
				query = "SELECT entity " +
						  "FROM X47BDBEntityForOrganizationalEntityBase entity " +
						 "WHERE TYPE(entity) = X47BDBEntityForWorkPlace " + 
						   "AND entity._orgDivisionServiceLocationOid = :loc"),
})

@Accessors(prefix="_")
@NoArgsConstructor
public abstract class X47BDBEntityForOrganizationalEntityBase
              extends DBEntityBase
           implements DBEntityForModelObject<DBPrimaryKeyForModelObject>,
           			  DBEntityHasModelObjectDescriptor {

	private static final long serialVersionUID = 7697152902080922348L;

/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@Id @Column(name="OID",length=OID.OID_LENGTH,nullable=false) @Basic
    @Getter @Setter protected String _oid;	
	
	@Column(name="ID",length=OID.OID_LENGTH * 3,nullable=false) @Basic
    @Getter @Setter protected String _id;				
	

	
	@Column(name="ORGANIZATION_OID",length=OID.OID_LENGTH) @Basic
    @Getter @Setter protected String _organizationOid;

	@Column(name="DIVISION_OID",length=OID.OID_LENGTH) @Basic
    @Getter @Setter protected String _orgDivisionOid;
	
	@Column(name="SERVICE_OID",length=OID.OID_LENGTH) @Basic
    @Getter @Setter protected String _orgDivisionServiceOid;
	
	@Column(name="LOCATION_OID",length=OID.OID_LENGTH) @Basic
    @Getter @Setter protected String _orgDivisionServiceLocationOid;
	
	
	
	@Column(name="ORGANIZATION_ID",length=OID.OID_LENGTH) @Basic
    @Getter @Setter protected String _organizationId;				// full hierarchy id of the organization

	@Column(name="DIVISION_ID",length=OID.OID_LENGTH) @Basic
    @Getter @Setter protected String _orgDivisionId;				// full hierarchy id of the division
	
	@Column(name="SERVICE_ID",length=OID.OID_LENGTH) @Basic
    @Getter @Setter protected String _orgDivisionServiceId;			// full hierarchy id of the division
	
	@Column(name="LOCATION_ID",length=OID.OID_LENGTH) @Basic
    @Getter @Setter protected String _orgDivisionServiceLocationId;	// full hierarchy id of the division

	

	@Column(name="DEEPTH") @Basic
	@Getter @Setter protected int _hierarchyLevel;		// used to return ordered results when searching (see X47BDBSearcherForEntityModelObject)

	
	
	@Column(name="NAME_ES",length=200,nullable=false) @Basic
	@Getter @Setter protected String _nameSpanish;

	@Column(name="NAME_EU",length=200,nullable=false) @Basic
	@Getter @Setter protected String _nameBasque;
	
	
	
	@Column(name="LAST_RAISE_DATE",
			insertable=false,updatable=true) @Temporal(TemporalType.TIMESTAMP) 
	@Getter @Setter protected Calendar _lastAlarmRaiseDate;		// http://www.developerscrappad.com/228/java/java-ee/ejb3-jpa-dealing-with-date-time-and-timestamp/
	
	@Column(name="ALARM_RAISE_COUNT") @Basic 
	@Getter @Setter protected long _alarmRaiseCount;		
	
	
	
	@Column(name="DESCRIPTOR") @Lob @Basic(fetch=FetchType.EAGER) 
	@Getter @Setter protected String _descriptor;
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	protected void _preCreate() {
		// nothing to do
	}

	@Override
	protected void _preUpdate() {
		// nothing to do
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  BI-DIRECTIONAL RELATIONSHIP WITH organization
//	Beware to update BOTH SIDES of the relationship: http://en.wikibooks.org/wiki/Java_Persistence/Relationships#Object_corruption.2C_one_side_of_the_relationship_is_not_updated_after_updating_the_other_side
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Parent organization
	 */
	@ManyToOne(targetEntity=X47BDBEntityForOrganization.class,		// not required but informative
			   fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "ORGANIZATION_OID",
					referencedColumnName = "OID",
					updatable = false,insertable = false,nullable = true)
	})
	@Getter private X47BDBEntityForOrganization _organization;

	public void setOrganization(final X47BDBEntityForOrganization dbOrg) {
		_organizationOid = dbOrg.getOid();

		if (this instanceof X47BDBEntityForOrgDivision) {
			X47BDBEntityForOrgDivision dbDivision = this.as(X47BDBEntityForOrgDivision.class);
			if (!dbOrg.containsOrgDivision(dbDivision)) {
				dbOrg.addOrgDivision(dbDivision);		// update the other side of the relationship
			}
		} 
		_organization = dbOrg;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  BI-DIRECTIONAL RELATIONSHIP WITH organizational divisions
//	Beware to update BOTH SIDES of the relationship: http://en.wikibooks.org/wiki/Java_Persistence/Relationships#Object_corruption.2C_one_side_of_the_relationship_is_not_updated_after_updating_the_other_side
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Parent division
	 */
	@ManyToOne(targetEntity=X47BDBEntityForOrgDivision.class,		// not required but informative
			   fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "DIVISION_OID",
					referencedColumnName = "OID",
					updatable = false,insertable = false,nullable = true)
	})
	@Getter private X47BDBEntityForOrgDivision _orgDivision;

	public void setOrgDivision(final X47BDBEntityForOrgDivision dbDivision) {
		_orgDivisionOid = dbDivision.getOid();

		if (this instanceof X47BDBEntityForOrgDivisionService) {
			X47BDBEntityForOrgDivisionService dbService = this.as(X47BDBEntityForOrgDivisionService.class);
			if (!dbDivision.containsService(dbService)) dbDivision.addService(dbService);	// update the other side of the relationship
		}
		_orgDivision = dbDivision;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  BI-DIRECTIONAL RELATIONSHIP WITH organizational division services
//	Beware to update BOTH SIDES of the relationship: http://en.wikibooks.org/wiki/Java_Persistence/Relationships#Object_corruption.2C_one_side_of_the_relationship_is_not_updated_after_updating_the_other_side
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Parent service
	 */
	@ManyToOne(targetEntity=X47BDBEntityForOrgDivisionService.class,		// not required but informative
			   fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "SERVICE_OID",
					referencedColumnName = "OID",
					updatable = false,insertable = false,nullable = true)
	})
	@Getter private X47BDBEntityForOrgDivisionService _orgDivisionService;

	public void setOrgDivisionService(final X47BDBEntityForOrgDivisionService dbService) {
		_orgDivisionServiceOid = dbService.getOid();

		if (this instanceof X47BDBEntityForOrgDivisionServiceLocation) {
			X47BDBEntityForOrgDivisionServiceLocation dbLocation = this.as(X47BDBEntityForOrgDivisionServiceLocation.class);
			if (!dbService.containsLocation(dbLocation)) dbService.addLocation(dbLocation);	// update the other side of the relationship
		}
		_orgDivisionService = dbService;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  BI-DIRECTIONAL RELATIONSHIP WITH organizational division service location
//	Beware to update BOTH SIDES of the relationship: http://en.wikibooks.org/wiki/Java_Persistence/Relationships#Object_corruption.2C_one_side_of_the_relationship_is_not_updated_after_updating_the_other_side
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Parent location
	 */
	@ManyToOne(targetEntity=X47BDBEntityForOrgDivisionServiceLocation.class,		// not required but informative
			   fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "LOCATION_OID",
					referencedColumnName = "OID",
					updatable = false,insertable = false,nullable = true)
	})
	@Getter private X47BDBEntityForOrgDivisionServiceLocation _orgDivisionServiceLocation;

	public void setOrgDivisionServiceLocation(final X47BDBEntityForOrgDivisionServiceLocation dbLocation) {
		_orgDivisionServiceLocationOid = dbLocation.getOid();

		if (this instanceof X47BDBEntityForWorkPlace) {
			X47BDBEntityForWorkPlace dbWorkPlace = this.as(X47BDBEntityForWorkPlace.class);
			if (!dbLocation.containsWorkPlace(dbWorkPlace)) dbLocation.addWorkPlace(dbWorkPlace);	// update the other side of the relationship
		}
		_orgDivisionServiceLocation = dbLocation;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public DBPrimaryKeyForModelObject getDBEntityPrimaryKey() {
		return DBPrimaryKeyForModelObjectImpl.from(_oid);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * @return the name in spanish and basque as a {@link LanguageTexts} object
	 */
	public LanguageTexts getName() {
		return new LanguageTextsMapBacked(LangTextNotFoundBehabior.RETURN_NULL)
							.add(Language.SPANISH,_nameSpanish)
							.add(Language.BASQUE,_nameBasque);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * This object typed as an entity model object: {@link X47BOrganization}, {@link X47BOrgDivision} or {@link X47BOrgDivisionService}
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <E extends X47BDBEntityForOrganizationalEntityBase> E as(final Class<E> type) {
		return (E)this;
	}
}
