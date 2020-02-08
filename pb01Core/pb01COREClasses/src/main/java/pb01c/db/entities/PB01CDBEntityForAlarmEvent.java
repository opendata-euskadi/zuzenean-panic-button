package pb01c.db.entities;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.guids.OID;
import r01f.persistence.db.entities.DBEntityBase;
import r01f.persistence.db.entities.DBEntityForModelObject;
import r01f.persistence.db.entities.primarykeys.DBPrimaryKeyForModelObject;
import r01f.persistence.db.entities.primarykeys.DBPrimaryKeyForModelObjectImpl;


@Entity @Cacheable(false)
@Table(name="PB01ALARMT00")
	@IdClass(DBPrimaryKeyForModelObjectImpl.class)
@NamedQueries({
	@NamedQuery(name = "PB01DBEntitiesForAlarmEventsByOrganizationID",
				query = "SELECT alarm " +
						  "FROM PB01CDBEntityForAlarmEvent alarm " +
						 "WHERE alarm._createDate BETWEEN :startDate AND :endDate " +
						   "AND alarm._organizationId = :id "),
	@NamedQuery(name = "PB01DBEntitiesForAlarmEventsByDivisionID",
				query = "SELECT alarm " +
						  "FROM PB01CDBEntityForAlarmEvent alarm " +
						 "WHERE alarm._createDate BETWEEN :startDate AND :endDate " +
						   "AND alarm._divisionId = :id "),
	@NamedQuery(name = "PB01DBEntitiesForAlarmEventsByServiceID",
				query = "SELECT alarm " +
						  "FROM PB01CDBEntityForAlarmEvent alarm " +
						 "WHERE alarm._createDate BETWEEN :startDate AND :endDate " +
						   "AND alarm._serviceId = :id "),
	@NamedQuery(name = "PB01DBEntitiesForAlarmEventsByLocationID",
				query = "SELECT alarm " +
						  "FROM PB01CDBEntityForAlarmEvent alarm " +
						 "WHERE alarm._createDate BETWEEN :startDate AND :endDate " +
						   "AND alarm._locationId = :id "),
	@NamedQuery(name = "PB01DBEntitiesForAlarmEventsByWorkPlaceID",
				query = "SELECT alarm " +
						  "FROM PB01CDBEntityForAlarmEvent alarm " +
						 "WHERE alarm._createDate BETWEEN :startDate AND :endDate " +
						   "AND alarm._workPlaceId = :id ")
})


@Accessors(prefix="_")
@NoArgsConstructor
public class PB01CDBEntityForAlarmEvent
     extends DBEntityBase
  implements DBEntityForModelObject<DBPrimaryKeyForModelObject> {

	private static final long serialVersionUID = -8080370931290297514L;

/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@Id @Column(name="OID",length=OID.OID_LENGTH) @Basic
    @Getter @Setter protected String _oid;


	@Column(name="ORGANIZATION_OID",length=OID.OID_LENGTH,nullable=false) @Basic
	@Getter @Setter private String _organizationOid;

	@Column(name="DIVISION_OID",length=OID.OID_LENGTH,nullable=false) @Basic
	@Getter @Setter private String _divisionOid;

	@Column(name="SERVICE_OID",length=OID.OID_LENGTH,nullable=false) @Basic
	@Getter @Setter private String _serviceOid;

	@Column(name="LOCATION_OID",length=OID.OID_LENGTH) @Basic
	@Getter @Setter private String _locationOid;

	@Column(name="WORKPLACE_OID",length=OID.OID_LENGTH) @Basic
	@Getter @Setter private String _workPlaceOid;



	@Column(name="ORGANIZATION_ID",length=OID.OID_LENGTH,nullable=false) @Basic
	@Getter @Setter private String _organizationId;

	@Column(name="DIVISION_ID",length=OID.OID_LENGTH,nullable=false) @Basic
	@Getter @Setter private String _divisionId;

	@Column(name="SERVICE_ID",length=OID.OID_LENGTH,nullable=false) @Basic
	@Getter @Setter private String _serviceId;

	@Column(name="LOCATION_ID",length=OID.OID_LENGTH) @Basic
	@Getter @Setter private String _locationId;

	@Column(name="WORKPLACE_ID",length=OID.OID_LENGTH) @Basic
	@Getter @Setter private String _workPlaceId;



    @Column(name="STATE",nullable=false) @Basic
	@Getter @Setter protected int _state = 0;

    @Column(name="ALARM_NOTIFICATION",columnDefinition="BLOB",nullable=true) @Lob @Basic(fetch=FetchType.LAZY)
	@Getter @Setter protected byte[] _notifierResponse;

/////////////////////////////////////////////////////////////////////////////////////////
//  PRIMARY KEY
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
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public DBPrimaryKeyForModelObject getDBEntityPrimaryKey() {
		return DBPrimaryKeyForModelObjectImpl.from(_oid);
	}
}
