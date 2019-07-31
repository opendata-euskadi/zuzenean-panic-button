package x47b.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.locale.Language;
import r01f.locale.LanguageTexts;
import r01f.model.PersistableModelObject;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallField.MarshallFieldAsXml;
import r01f.objectstreamer.annotations.MarshallType;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BOrgDivision;
import x47b.model.org.X47BOrgDivisionService;
import x47b.model.org.X47BOrgDivisionServiceLocation;
import x47b.model.org.X47BOrganization;
import x47b.model.org.X47BWorkPlace;

/**
 * Encapsulates abstracts (summaries) for entity
 */
public class X47BAlarmMessageEntityAbstracts {
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Encapsulates data about an entity ({@link X47BOrganization}, {@link X47BOrgDivision}, {@link X47BOrgDivisionService}, {@link X47BOrgDivisionServiceLocation}, or {@link X47BWorkPlace}) 
	 * to be sent in the {@link X47BAlarmMessage} 
	 * Like {@link X47BAlarmMessage}, this model object is NOT persisted (it's NOT a {@link PersistableModelObject} instance) since it's composed 
	 * when handling the {@link X47BAlarmEvent} creation event from other model objects 
	 */
	@Accessors(prefix="_") @NoArgsConstructor @AllArgsConstructor
	private static class X47BAlarmMessageEntityAbstract<O extends X47BPersistableObjectOID,ID extends X47BPersistableObjectID<O>> {
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
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallType(as="organizationAbstract")
	public static class X47BAlarmMessageAbstractForOrganization
			    extends X47BAlarmMessageEntityAbstract<X47BOrganizationOID,X47BOrganizationID> {
		public X47BAlarmMessageAbstractForOrganization(final X47BOrganizationOID oid,final X47BOrganizationID id,
													   final LanguageTexts name) {
			super(oid,id,
				  name);
		}
		public X47BAlarmMessageAbstractForOrganization(final X47BOrganization org) {
			this(org.getOid(),org.getId(),
				 org.getNameByLanguage());
		}
	}
	@MarshallType(as="divisionAbstract")
	public static class X47BAlarmMessageAbstractForDivision
			    extends X47BAlarmMessageEntityAbstract<X47BOrgDivisionOID,X47BOrgDivisionID> {
		public X47BAlarmMessageAbstractForDivision(final X47BOrgDivisionOID oid,final X47BOrgDivisionID id,
												   final LanguageTexts name) {
			super(oid,id,
				  name);
		}
		public X47BAlarmMessageAbstractForDivision(final X47BOrgDivision div) {
			this(div.getOid(),div.getId(),
				 div.getNameByLanguage());
		}
	}
	@MarshallType(as="serviceAbstract")
	public static class X47BAlarmMessageAbstractForService
			    extends X47BAlarmMessageEntityAbstract<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID> {
		public X47BAlarmMessageAbstractForService(final X47BOrgDivisionServiceOID oid,final X47BOrgDivisionServiceID id,
												   final LanguageTexts name) {
			super(oid,id,
				  name);
		}
		public X47BAlarmMessageAbstractForService(final X47BOrgDivisionService srvc) {
			this(srvc.getOid(),srvc.getId(),
				 srvc.getNameByLanguage());
		}
	}
	@MarshallType(as="locationAbstract")
	public static class X47BAlarmMessageAbstractForLocation
			    extends X47BAlarmMessageEntityAbstract<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID> {
		public X47BAlarmMessageAbstractForLocation(final X47BOrgDivisionServiceLocationOID oid,final X47BOrgDivisionServiceLocationID id,
												   final LanguageTexts name) {
			super(oid,id,
				  name);
		}
		public X47BAlarmMessageAbstractForLocation(final X47BOrgDivisionServiceLocation loc) {
			this(loc.getOid(),loc.getId(),
				 loc.getNameByLanguage());
		}
	}
	@MarshallType(as="workPlaceAbstract")
	public static class X47BAlarmMessageAbstractForWorkPlace
			    extends X47BAlarmMessageEntityAbstract<X47BWorkPlaceOID,X47BWorkPlaceID> {
		public X47BAlarmMessageAbstractForWorkPlace(final X47BWorkPlaceOID oid,final X47BWorkPlaceID id,
												final LanguageTexts name) {
			super(oid,id,
				  name);
		}
		public X47BAlarmMessageAbstractForWorkPlace(final X47BWorkPlace workPlace) {
			this(workPlace.getOid(),workPlace.getId(),
				 workPlace.getNameByLanguage());
		}
	}
}
