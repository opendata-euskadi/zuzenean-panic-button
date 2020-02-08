package pb01c.db.entities;

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
import pb01a.model.org.PB01AOrgDivision;
import pb01a.model.org.PB01AOrgDivisionService;
import pb01a.model.org.PB01AOrganization;
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


@MappedSuperclass
@Entity @Cacheable(false)
@Table(name="PB01ENTITYT00")
	@IdClass(DBPrimaryKeyForModelObjectImpl.class)

@Inheritance(strategy=InheritanceType.JOINED)
	@DiscriminatorColumn(name="TYPE",discriminatorType=DiscriminatorType.STRING,length=3)

@NamedQueries({
	// Find by id
	@NamedQuery(name = "PB01DBOrganizationalEntityById",
				query = "SELECT entity " +
						  "FROM PB01CDBEntityForOrganizationalEntityBase entity " +
						 "WHERE TYPE(entity) = :dbType " +
						   "AND entity._id = :id "),
	// Find all organizations
	@NamedQuery(name = "PB01DBEntitiesForOrganization",
				query = "SELECT entity " +
						  "FROM PB01CDBEntityForOrganizationalEntityBase entity " +
						 "WHERE TYPE(entity) = PB01CDBEntityForOrganization"),
	// Find all organization's divisions
	@NamedQuery(name = "PB01DBEntitiesForDivisionsByOrganization",
				query = "SELECT entity " +
						  "FROM PB01CDBEntityForOrganizationalEntityBase entity " +
						 "WHERE TYPE(entity) = PB01CDBEntityForOrgDivision " +
						   "AND entity._organizationOid = :org"),
	// Find all division's services
	@NamedQuery(name = "PB01DBEntitiesForServicesByDivision",
				query = "SELECT entity " +
						  "FROM PB01CDBEntityForOrganizationalEntityBase entity " +
						 "WHERE TYPE(entity) = PB01CDBEntityForOrgDivisionService " +
						   "AND entity._orgDivisionOid = :division"),
	// Find all service's location
	@NamedQuery(name = "PB01DBEntitiesForLocationsByService",
				query = "SELECT entity " +
						  "FROM PB01CDBEntityForOrganizationalEntityBase entity " +
						 "WHERE TYPE(entity) = PB01CDBEntityForOrgDivisionServiceLocation " +
						   "AND entity._orgDivisionServiceOid = :service"),
	// Find all location's workplaces
	@NamedQuery(name = "PB01DBEntitiesForWorkPlacesByLocation",
				query = "SELECT entity " +
						  "FROM PB01CDBEntityForOrganizationalEntityBase entity " +
						 "WHERE TYPE(entity) = PB01CDBEntityForWorkPlace " +
						   "AND entity._orgDivisionServiceLocationOid = :loc"),
})

@Accessors(prefix="_")
@NoArgsConstructor
public abstract class PB01CDBEntityForOrganizationalEntityBase
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
	@Getter @Setter protected int _hierarchyLevel;		// used to return ordered results when searching (see PB01CDBSearcherForEntityModelObject)



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
	@ManyToOne(targetEntity=PB01CDBEntityForOrganization.class,		// not required but informative
			   fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "ORGANIZATION_OID",
					referencedColumnName = "OID",
					updatable = false,insertable = false,nullable = true)
	})
	@Getter private PB01CDBEntityForOrganization _organization;

	public void setOrganization(final PB01CDBEntityForOrganization dbOrg) {
		_organizationOid = dbOrg.getOid();

		if (this instanceof PB01CDBEntityForOrgDivision) {
			PB01CDBEntityForOrgDivision dbDivision = this.as(PB01CDBEntityForOrgDivision.class);
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
	@ManyToOne(targetEntity=PB01CDBEntityForOrgDivision.class,		// not required but informative
			   fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "DIVISION_OID",
					referencedColumnName = "OID",
					updatable = false,insertable = false,nullable = true)
	})
	@Getter private PB01CDBEntityForOrgDivision _orgDivision;

	public void setOrgDivision(final PB01CDBEntityForOrgDivision dbDivision) {
		_orgDivisionOid = dbDivision.getOid();

		if (this instanceof PB01CDBEntityForOrgDivisionService) {
			PB01CDBEntityForOrgDivisionService dbService = this.as(PB01CDBEntityForOrgDivisionService.class);
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
	@ManyToOne(targetEntity=PB01CDBEntityForOrgDivisionService.class,		// not required but informative
			   fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "SERVICE_OID",
					referencedColumnName = "OID",
					updatable = false,insertable = false,nullable = true)
	})
	@Getter private PB01CDBEntityForOrgDivisionService _orgDivisionService;

	public void setOrgDivisionService(final PB01CDBEntityForOrgDivisionService dbService) {
		_orgDivisionServiceOid = dbService.getOid();

		if (this instanceof PB01CDBEntityForOrgDivisionServiceLocation) {
			PB01CDBEntityForOrgDivisionServiceLocation dbLocation = this.as(PB01CDBEntityForOrgDivisionServiceLocation.class);
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
	@ManyToOne(targetEntity=PB01CDBEntityForOrgDivisionServiceLocation.class,		// not required but informative
			   fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "LOCATION_OID",
					referencedColumnName = "OID",
					updatable = false,insertable = false,nullable = true)
	})
	@Getter private PB01CDBEntityForOrgDivisionServiceLocation _orgDivisionServiceLocation;

	public void setOrgDivisionServiceLocation(final PB01CDBEntityForOrgDivisionServiceLocation dbLocation) {
		_orgDivisionServiceLocationOid = dbLocation.getOid();

		if (this instanceof PB01CDBEntityForWorkPlace) {
			PB01CDBEntityForWorkPlace dbWorkPlace = this.as(PB01CDBEntityForWorkPlace.class);
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
	 * This object typed as an entity model object: {@link PB01AOrganization}, {@link PB01AOrgDivision} or {@link PB01AOrgDivisionService}
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <E extends PB01CDBEntityForOrganizationalEntityBase> E as(final Class<E> type) {
		return (E)this;
	}
}
