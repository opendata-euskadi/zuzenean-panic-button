package pb01a.model.search;

import java.util.Collection;

import com.google.common.base.Preconditions;

import lombok.experimental.Accessors;
import pb01a.model.PB01APersistableObject;
import pb01a.model.metadata.PB01AHasFieldsMetaDataForHasOrgDivision;
import pb01a.model.metadata.PB01AHasFieldsMetaDataForHasOrgDivisionService;
import pb01a.model.metadata.PB01AHasFieldsMetaDataForHasOrgDivisionServiceLocation;
import pb01a.model.metadata.PB01AHasFieldsMetaDataForHasOrganization;
import pb01a.model.metadata.PB01AHasFieldsMetaDataForHasWorkPlace;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.org.PB01AOrgDivision;
import pb01a.model.org.PB01AOrgDivisionService;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import pb01a.model.org.PB01AOrganization;
import pb01a.model.org.PB01AWorkPlace;
import r01f.model.ModelObject;
import r01f.model.metadata.FieldID;
import r01f.model.search.SearchFilterAsCriteriaString;
import r01f.model.search.SearchFilterForModelObjectBase;
import r01f.model.search.query.BooleanQueryClause.QueryClauseOccur;
import r01f.objectstreamer.annotations.MarshallType;
import r01f.util.types.collections.CollectionUtils;


/**
 * A search filter for a {@link PB01APersistableObject} like {@link PB01AOrganization}, {@link PB01AOrgDivisionServiceLocation} or {@link PB01AWorkPlace}
 * <pre class='brush:java'>
 * // Find all locations or work places with a certain name belonging to an organization
 * PB01ASearchFilterForEntity filter = PB01ASearchFilterForEntity.create()
 * 															.belongingTo(PB01AOrganizationOID.forId("myOrg"))
 * 															.withText("text")
 * 															.in(Language.ENGLISH);
 * </pre>
 */
@MarshallType(as="searchFilterForOrganizationalEntity")
@Accessors(prefix="_")
public class PB01ASearchFilterForPanicButtonOrganizationalEntity
     extends SearchFilterForModelObjectBase<PB01ASearchFilterForPanicButtonOrganizationalEntity> {

	private static final long serialVersionUID = -7328506874819631272L;

/////////////////////////////////////////////////////////////////////////////////////////
//  BUILDER
/////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	public PB01ASearchFilterForPanicButtonOrganizationalEntity() {
		// a filter by all organizational entity model objects
		super(PB01AOrganization.class,
			  PB01AOrgDivision.class,
			  PB01AOrgDivisionService.class,
			  PB01AOrgDivisionServiceLocation.class,
			  PB01AWorkPlace.class);
	}
	@SuppressWarnings("unchecked")
	public PB01ASearchFilterForPanicButtonOrganizationalEntity(final Class<? extends ModelObject>... modelObjectTypes) {
		super(modelObjectTypes);
	}
	/**
	 * Constructor needed to build a filter from the criteria string
	 * @param modelObjectType
	 */
	public PB01ASearchFilterForPanicButtonOrganizationalEntity(final Collection<Class<? extends ModelObject>> modelObjectTypes) {
		super(modelObjectTypes);
	}
	/**
	 * Constructor used at REST services when a filter arrives as QueryParam:
	 * <pre class='brush:java'>
	 *		@GET
	 *		public Response search(@HeaderParam("securityContext") final PB01AUserContext securityContext,
	 *							   @QueryParam("filter")	       final PB01ASearchFilterForEntity filter) {
	 *			...
	 *		}
 	 * </pre>
	 * any of these methods are used at {@link PB01ASearchFilterForPanicButtonOrganizationalEntity}:
	 * <ul>
	 * 		<li>String based constructor</li>
	 * 		<li>valueOf(String) static method</li>
	 * 		<li>fromString(String) static method</li>
	 * </ul>
	 * @param str
	 */
	public static PB01ASearchFilterForPanicButtonOrganizationalEntity valueOf(final String str) {
		return PB01ASearchFilterForPanicButtonOrganizationalEntity.fromString(str);
	}
	public static PB01ASearchFilterForPanicButtonOrganizationalEntity fromString(final String str) {
		return SearchFilterForModelObjectBase.fromCriteriaString(SearchFilterAsCriteriaString.of(str));
	}
	public static PB01ASearchFilterForPanicButtonOrganizationalEntity create() {
		return new PB01ASearchFilterForPanicButtonOrganizationalEntity();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Sets the model object's type
	 * @param modelObjectType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PB01ASearchFilterForPanicButtonOrganizationalEntity filterBy(final Class<? extends ModelObject>... modelObjTypes) {
		final Collection<Class<? extends ModelObject>> modelObjTypesCol = CollectionUtils.of(modelObjTypes)
																				   .asSet();
		return this.filterBy(modelObjTypesCol);
	}
	/**
	 * Sets the model object's type
	 * @param modelObjectType
	 * @return
	 */
	public PB01ASearchFilterForPanicButtonOrganizationalEntity filterBy(final Collection<Class<? extends ModelObject>> modelObjTypesCol) {
		Preconditions.checkArgument(CollectionUtils.hasData(modelObjTypesCol),"The modelObjectTypes cannot be null or empty");
		Preconditions.checkArgument(_checkModelObjType(modelObjTypesCol),"One of the received model object types to filter by is NOT valid: {}",modelObjTypesCol);

		this.setModelObjectTypesToFilterBy(modelObjTypesCol);
		return this;
	}
	/**
	 * Checks that the received model object types are of the correct type
	 * @param modelObjTypes
	 * @return
	 */
	private static boolean _checkModelObjType(final Collection<Class<? extends ModelObject>> modelObjTypes) {
		boolean outOK = true;
		for (final Class<? extends ModelObject> modelObjType : modelObjTypes) {
			if (modelObjType == PB01APersistableObject.class
			 || modelObjType == PB01AOrganization.class
			 || modelObjType == PB01AOrgDivision.class
			 || modelObjType == PB01AOrgDivisionService.class
			 || modelObjType == PB01AOrgDivisionServiceLocation.class
			 || modelObjType == PB01AWorkPlace.class) {
				continue;	// ok
			}
			// not valid model obj type
			outOK = false;
			break;
		}
		return outOK;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01ASearchFilterForPanicButtonOrganizationalEntity belongingTo(final PB01AOrganizationOID orgOid) {
		Preconditions.checkArgument(orgOid != null,"The org oid cannot be null");
		final FieldID fieldId = FieldID.from(PB01AHasFieldsMetaDataForHasOrganization.SEARCHABLE_METADATA.OID);
		return this.getModifierWrapper()
						.addOrUpdateEqualsClause(fieldId,
												 orgOid,
												 QueryClauseOccur.MUST);
	}
	public PB01AOrganizationOID getOrganizationOid() {
		final FieldID fieldId = FieldID.from(PB01AHasFieldsMetaDataForHasOrganization.SEARCHABLE_METADATA.OID);
		return this.getAccessorWrapper().queryClauses()
						.getValueOrNull(fieldId);
	}
	public PB01ASearchFilterForPanicButtonOrganizationalEntity belongingTo(final PB01AOrgDivisionOID orgDivisionOid) {
		Preconditions.checkArgument(orgDivisionOid != null,"The division oid cannot be null");
		final FieldID fieldId = FieldID.from(PB01AHasFieldsMetaDataForHasOrgDivision.SEARCHABLE_METADATA.OID);
		return this.getModifierWrapper()
						.addOrUpdateEqualsClause(fieldId,
												 orgDivisionOid,
												 QueryClauseOccur.MUST);
	}
	public PB01AOrgDivisionOID getOrgDivisionOid() {
		final FieldID fieldId = FieldID.from(PB01AHasFieldsMetaDataForHasOrgDivision.SEARCHABLE_METADATA.OID);
		return this.getAccessorWrapper().queryClauses()
						.getValueOrNull(fieldId);
	}
	public PB01ASearchFilterForPanicButtonOrganizationalEntity belongingTo(final PB01AOrgDivisionServiceOID orgDivisionServiceOid) {
		Preconditions.checkArgument(orgDivisionServiceOid != null,"The service oid cannot be null");
		final FieldID fieldId = FieldID.from(PB01AHasFieldsMetaDataForHasOrgDivisionService.SEARCHABLE_METADATA.OID);
		return this.getModifierWrapper()
						.addOrUpdateEqualsClause(fieldId,
												 orgDivisionServiceOid,
												 QueryClauseOccur.MUST);
	}
	public PB01AOrgDivisionServiceOID getOrgDivisionServiceOid() {
		final FieldID fieldId = FieldID.from(PB01AHasFieldsMetaDataForHasOrgDivisionService.SEARCHABLE_METADATA.OID);
		return this.getAccessorWrapper().queryClauses()
						.getValueOrNull(fieldId);
	}
	public PB01ASearchFilterForPanicButtonOrganizationalEntity belongingTo(final PB01AOrgDivisionServiceLocationOID locOid) {
		Preconditions.checkArgument(locOid != null,"The loc oid cannot be null");
		final FieldID fieldId = FieldID.from(PB01AHasFieldsMetaDataForHasOrgDivisionServiceLocation.SEARCHABLE_METADATA.OID);
		return this.getModifierWrapper()
						.addOrUpdateEqualsClause(fieldId,
									    		 locOid,
									    		 QueryClauseOccur.MUST);
	}
	public PB01AOrgDivisionServiceLocationOID getOrgDivisionServiceLocationOid() {
		final FieldID fieldId = FieldID.from(PB01AHasFieldsMetaDataForHasOrgDivisionServiceLocation.SEARCHABLE_METADATA.OID);
		return this.getAccessorWrapper().queryClauses()
						.getValueOrNull(fieldId);
	}
	public PB01ASearchFilterForPanicButtonOrganizationalEntity belongingTo(final PB01AWorkPlaceOID workPlaceOid) {
		Preconditions.checkArgument(workPlaceOid != null,"The workplace oid cannot be null");
		final FieldID fieldId = FieldID.from(PB01AHasFieldsMetaDataForHasWorkPlace.SEARCHABLE_METADATA.OID);
		return this.getModifierWrapper()
						.addOrUpdateEqualsClause(fieldId,
									    		 workPlaceOid,
									    		 QueryClauseOccur.MUST);
	}
	public PB01AWorkPlaceOID getWorkPlaceOid() {
		final FieldID fieldId = FieldID.from(PB01AHasFieldsMetaDataForHasWorkPlace.SEARCHABLE_METADATA.OID);
		return this.getAccessorWrapper().queryClauses()
						.getValueOrNull(fieldId);
	}
}
