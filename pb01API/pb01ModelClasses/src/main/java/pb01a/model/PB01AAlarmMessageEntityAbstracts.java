package pb01a.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgObjectID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgObjectOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.org.PB01AOrgDivision;
import pb01a.model.org.PB01AOrgDivisionService;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import pb01a.model.org.PB01AOrgObjectRef;
import pb01a.model.org.PB01AOrganization;
import pb01a.model.org.PB01AWorkPlace;
import r01f.locale.Language;
import r01f.locale.LanguageTexts;
import r01f.model.PersistableModelObject;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallField.MarshallFieldAsXml;
import r01f.objectstreamer.annotations.MarshallType;

/**
 * Encapsulates abstracts (summaries) for entity
 */
@NoArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class PB01AAlarmMessageEntityAbstracts {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Encapsulates data about an entity ({@link PB01AOrganization}, {@link PB01AOrgDivision}, {@link PB01AOrgDivisionService}, {@link PB01AOrgDivisionServiceLocation}, or {@link PB01AWorkPlace})
	 * to be sent in the {@link PB01AAlarmMessage}
	 * Like {@link PB01AAlarmMessage}, this model object is NOT persisted (it's NOT a {@link PersistableModelObject} instance) since it's composed
	 * when handling the {@link PB01AAlarmEvent} creation event from other model objects
	 */
	@Accessors(prefix="_") @NoArgsConstructor @AllArgsConstructor
	private static class PB01AAlarmMessageEntityAbstract<O extends PB01AOrgObjectOID,ID extends PB01AOrgObjectID<O>> {
		@MarshallField(as="oid",
			   	       whenXml=@MarshallFieldAsXml(attr=true))
		@Getter @Setter private O _entityOid;

		@MarshallField(as="id",
			   	   	   whenXml=@MarshallFieldAsXml(attr=true))
		@Getter @Setter private ID _entityId;

		@MarshallField(as="name")
		@Getter @Setter private LanguageTexts _name;

		public String getDefaultName() {
			String outName = _name != null ? _name.get(Language.SPANISH) : null;
			if (outName == null) outName = _name != null ? _name.get(Language.BASQUE) : null;
			if (outName == null) outName = _entityId != null ? _entityId.asString() : null;
			if (outName == null) outName = _entityOid != null ? _entityOid.asString() : null;
			if (outName == null) throw new IllegalStateException("NO available name for an alarm entity!");
			return outName;
		}
		public PB01AOrgObjectRef<O,ID> getOrgObjectRef() {
			return new PB01AOrgObjectRef<O,ID>(_entityOid,_entityId);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallType(as="organizationAbstract")
	public static class PB01AAlarmMessageAbstractForOrganization
			    extends PB01AAlarmMessageEntityAbstract<PB01AOrganizationOID,PB01AOrganizationID> {
		public PB01AAlarmMessageAbstractForOrganization(final PB01AOrganizationOID oid,final PB01AOrganizationID id,
													   final LanguageTexts name) {
			super(oid,id,
				  name);
		}
		public PB01AAlarmMessageAbstractForOrganization(final PB01AOrganization org) {
			this(org.getOid(),org.getId(),
				 org.getNameByLanguage());
		}
	}
	@MarshallType(as="divisionAbstract")
	public static class PB01AAlarmMessageAbstractForDivision
			    extends PB01AAlarmMessageEntityAbstract<PB01AOrgDivisionOID,PB01AOrgDivisionID> {
		public PB01AAlarmMessageAbstractForDivision(final PB01AOrgDivisionOID oid,final PB01AOrgDivisionID id,
												   final LanguageTexts name) {
			super(oid,id,
				  name);
		}
		public PB01AAlarmMessageAbstractForDivision(final PB01AOrgDivision div) {
			this(div.getOid(),div.getId(),
				 div.getNameByLanguage());
		}
	}
	@MarshallType(as="serviceAbstract")
	public static class PB01AAlarmMessageAbstractForService
			    extends PB01AAlarmMessageEntityAbstract<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID> {
		public PB01AAlarmMessageAbstractForService(final PB01AOrgDivisionServiceOID oid,final PB01AOrgDivisionServiceID id,
												   final LanguageTexts name) {
			super(oid,id,
				  name);
		}
		public PB01AAlarmMessageAbstractForService(final PB01AOrgDivisionService srvc) {
			this(srvc.getOid(),srvc.getId(),
				 srvc.getNameByLanguage());
		}
	}
	@MarshallType(as="locationAbstract")
	public static class PB01AAlarmMessageAbstractForLocation
			    extends PB01AAlarmMessageEntityAbstract<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocationID> {
		public PB01AAlarmMessageAbstractForLocation(final PB01AOrgDivisionServiceLocationOID oid,final PB01AOrgDivisionServiceLocationID id,
												   final LanguageTexts name) {
			super(oid,id,
				  name);
		}
		public PB01AAlarmMessageAbstractForLocation(final PB01AOrgDivisionServiceLocation loc) {
			this(loc.getOid(),loc.getId(),
				 loc.getNameByLanguage());
		}
	}
	@MarshallType(as="workPlaceAbstract")
	public static class PB01AAlarmMessageAbstractForWorkPlace
			    extends PB01AAlarmMessageEntityAbstract<PB01AWorkPlaceOID,PB01AWorkPlaceID> {
		public PB01AAlarmMessageAbstractForWorkPlace(final PB01AWorkPlaceOID oid,final PB01AWorkPlaceID id,
												final LanguageTexts name) {
			super(oid,id,
				  name);
		}
		public PB01AAlarmMessageAbstractForWorkPlace(final PB01AWorkPlace workPlace) {
			this(workPlace.getOid(),workPlace.getId(),
				 workPlace.getNameByLanguage());
		}
	}
}
