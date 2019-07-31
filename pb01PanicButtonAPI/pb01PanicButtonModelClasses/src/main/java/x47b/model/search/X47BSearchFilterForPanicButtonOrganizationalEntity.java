package x47b.model.search;

import java.util.Collection;

import com.google.common.base.Preconditions;

import lombok.experimental.Accessors;
import r01f.model.ModelObject;
import r01f.model.metadata.FieldID;
import r01f.model.search.SearchFilterAsCriteriaString;
import r01f.model.search.SearchFilterForModelObjectBase;
import r01f.model.search.query.BooleanQueryClause.QueryClauseOccur;
import r01f.objectstreamer.annotations.MarshallType;
import r01f.util.types.collections.CollectionUtils;
import x47b.model.X47BPersistableObject;
import x47b.model.metadata.X47BHasFieldsMetaDataForHasOrgDivision;
import x47b.model.metadata.X47BHasFieldsMetaDataForHasOrgDivisionService;
import x47b.model.metadata.X47BHasFieldsMetaDataForHasOrgDivisionServiceLocation;
import x47b.model.metadata.X47BHasFieldsMetaDataForHasOrganization;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrgDivision;
import x47b.model.org.X47BOrgDivisionService;
import x47b.model.org.X47BOrgDivisionServiceLocation;
import x47b.model.org.X47BOrganization;
import x47b.model.org.X47BWorkPlace;


/**
 * A search filter for a {@link X47BPersistableObject} like {@link X47BOrganization}, {@link X47BLocation} or {@link X47BWorkPlace}
 * <pre class='brush:java'>
 * // Find all locations or work places with a certain name belonging to an organization
 * X47BSearchFilterForEntity filter = X47BSearchFilterForEntity.create()
 * 															.belongingTo(X47BOrganizationOID.forId("myOrg"))
 * 															.withText("text")
 * 															.in(Language.ENGLISH);
 * </pre>
 */
@MarshallType(as="searchFilterForOrganizationalEntity")
@Accessors(prefix="_")
public class X47BSearchFilterForPanicButtonOrganizationalEntity 
     extends SearchFilterForModelObjectBase<X47BSearchFilterForPanicButtonOrganizationalEntity> {

	private static final long serialVersionUID = -7328506874819631272L;

/////////////////////////////////////////////////////////////////////////////////////////
//  BUILDER
/////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	public X47BSearchFilterForPanicButtonOrganizationalEntity() {
		// a filter by all organizational entity model objects
		super(X47BOrganization.class,
			  X47BOrgDivision.class,
			  X47BOrgDivisionService.class,
			  X47BOrgDivisionServiceLocation.class,
			  X47BWorkPlace.class);
	}
	public X47BSearchFilterForPanicButtonOrganizationalEntity(final Class<? extends ModelObject>... modelObjectTypes) {
		super(modelObjectTypes);
	}
	/**
	 * Constructor needed to build a filter from the criteria string
	 * @param modelObjectType
	 */
	public X47BSearchFilterForPanicButtonOrganizationalEntity(final Collection<Class<? extends ModelObject>> modelObjectTypes) {
		super(modelObjectTypes);
	}
	/**
	 * Constructor used at REST services when a filter arrives as QueryParam:
	 * <pre class='brush:java'>
	 *		@GET 
	 *		public Response search(@HeaderParam("securityContext") final X47BUserContext securityContext,
	 *							   @QueryParam("filter")	       final X47BSearchFilterForEntity filter) {
	 *			...
	 *		}
 	 * </pre>
	 * any of these methods are used at {@link X47BSearchFilterForPanicButtonOrganizationalEntity}:
	 * <ul>
	 * 		<li>String based constructor</li>
	 * 		<li>valueOf(String) static method</li>
	 * 		<li>fromString(String) static method</li>
	 * </ul>
	 * @param str
	 */
	public static X47BSearchFilterForPanicButtonOrganizationalEntity valueOf(final String str) {
		return X47BSearchFilterForPanicButtonOrganizationalEntity.fromString(str);
	}
	public static X47BSearchFilterForPanicButtonOrganizationalEntity fromString(final String str) {
		return SearchFilterForModelObjectBase.fromCriteriaString(SearchFilterAsCriteriaString.of(str));
	}
	public static X47BSearchFilterForPanicButtonOrganizationalEntity create() {
		return new X47BSearchFilterForPanicButtonOrganizationalEntity();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Sets the model object's type
	 * @param modelObjectType
	 * @return
	 */
	public X47BSearchFilterForPanicButtonOrganizationalEntity filterBy(final Class<? extends ModelObject>... modelObjTypes) {
		Collection<Class<? extends ModelObject>> modelObjTypesCol = CollectionUtils.of(modelObjTypes)
																				   .asSet();
		return this.filterBy(modelObjTypesCol);
	}
	/**
	 * Sets the model object's type
	 * @param modelObjectType
	 * @return
	 */
	public X47BSearchFilterForPanicButtonOrganizationalEntity filterBy(final Collection<Class<? extends ModelObject>> modelObjTypesCol) {
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
		for (Class<? extends ModelObject> modelObjType : modelObjTypes) {
			if (modelObjType == X47BPersistableObject.class
			 || modelObjType == X47BOrganization.class
			 || modelObjType == X47BOrgDivision.class
			 || modelObjType == X47BOrgDivisionService.class
			 || modelObjType == X47BOrgDivisionServiceLocation.class
			 || modelObjType == X47BWorkPlace.class) {
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
	public X47BSearchFilterForPanicButtonOrganizationalEntity belongingTo(final X47BOrganizationOID orgOid) {
		Preconditions.checkArgument(orgOid != null,"The org oid cannot be null");
		FieldID fieldId = FieldID.from(X47BHasFieldsMetaDataForHasOrganization.SEARCHABLE_METADATA.OID);
		return this.getModifierWrapper()
						.addOrUpdateEqualsClause(fieldId,
												 orgOid,
												 QueryClauseOccur.MUST);
	}
	public X47BOrganizationOID getOrganizationOid() {
		FieldID fieldId = FieldID.from(X47BHasFieldsMetaDataForHasOrganization.SEARCHABLE_METADATA.OID);
		return this.getAccessorWrapper().queryClauses()
						.getValueOrNull(fieldId);
	}
	public X47BSearchFilterForPanicButtonOrganizationalEntity belongingTo(final X47BOrgDivisionOID orgDivisionOid) {
		Preconditions.checkArgument(orgDivisionOid != null,"The division oid cannot be null");
		FieldID fieldId = FieldID.from(X47BHasFieldsMetaDataForHasOrgDivision.SEARCHABLE_METADATA.OID);
		return this.getModifierWrapper()
						.addOrUpdateEqualsClause(fieldId,
												 orgDivisionOid,
												 QueryClauseOccur.MUST);
	}
	public X47BOrgDivisionOID getOrgDivisionOid() {
		FieldID fieldId = FieldID.from(X47BHasFieldsMetaDataForHasOrgDivision.SEARCHABLE_METADATA.OID);
		return this.getAccessorWrapper().queryClauses()
						.getValueOrNull(fieldId);
	}
	public X47BSearchFilterForPanicButtonOrganizationalEntity belongingTo(final X47BOrgDivisionServiceOID orgDivisionServiceOid) {
		Preconditions.checkArgument(orgDivisionServiceOid != null,"The service oid cannot be null");
		FieldID fieldId = FieldID.from(X47BHasFieldsMetaDataForHasOrgDivisionService.SEARCHABLE_METADATA.OID);
		return this.getModifierWrapper()
						.addOrUpdateEqualsClause(fieldId,
												 orgDivisionServiceOid,
												 QueryClauseOccur.MUST);
	}
	public X47BOrgDivisionOID getOrgDivisionServiceOid() {
		FieldID fieldId = FieldID.from(X47BHasFieldsMetaDataForHasOrgDivisionService.SEARCHABLE_METADATA.OID);
		return this.getAccessorWrapper().queryClauses()
						.getValueOrNull(fieldId);
	}
	public X47BSearchFilterForPanicButtonOrganizationalEntity belongingTo(final X47BOrgDivisionServiceLocationOID locOid) {
		Preconditions.checkArgument(locOid != null,"The loc oid cannot be null");
		FieldID fieldId = FieldID.from(X47BHasFieldsMetaDataForHasOrgDivisionServiceLocation.SEARCHABLE_METADATA.OID);
		return this.getModifierWrapper()
						.addOrUpdateEqualsClause(fieldId,
									    		 locOid,
									    		 QueryClauseOccur.MUST);
	}
	public X47BOrgDivisionServiceLocationOID getLocationOid() {
		FieldID fieldId = FieldID.from(X47BHasFieldsMetaDataForHasOrgDivisionServiceLocation.SEARCHABLE_METADATA.OID);
		return this.getAccessorWrapper().queryClauses()
						.getValueOrNull(fieldId);
	}
}
