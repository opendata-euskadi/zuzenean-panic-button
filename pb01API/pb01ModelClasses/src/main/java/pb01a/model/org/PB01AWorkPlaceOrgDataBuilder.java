package pb01a.model.org;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationServiceLocationGroupID;
import pb01a.model.oids.PB01AServiceIDs.PB01ATerminalID;
import r01f.guids.CommonOIDs.UserCode;
import r01f.patterns.IsBuilder;

/**
 * Builds an {@link PB01AWorkPlaceOrgData} object
 */
@NoArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class PB01AWorkPlaceOrgDataBuilder
	 implements IsBuilder {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public static PB01AWorkPlaceOrgDataBuilderOrgServiceStep createFor(final PB01AOrganizationID orgId) {
		PB01AWorkPlaceOrgData src = new PB01AWorkPlaceOrgData();
		src.setOrgId(orgId);
		return new PB01AWorkPlaceOrgDataBuilder() {/* nothing */}
						.new PB01AWorkPlaceOrgDataBuilderOrgServiceStep(src);
	}
	public static PB01AWorkPlaceOrgDataBuilderOrgServiceStep createForEJGV() {
		return PB01AWorkPlaceOrgDataBuilder.createFor(PB01AOrganizationID.EJGV);
	}
	public static PB01AWorkPlaceOrgDataBuilderOrgServiceLocationStep createForZuzenean() {
		return PB01AWorkPlaceOrgDataBuilder.createFor(PB01AOrganizationID.EJGV)
										   .service(PB01AOrgDivisionServiceID.ZUZENEAN);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@RequiredArgsConstructor(access=AccessLevel.PRIVATE)
	public class PB01AWorkPlaceOrgDataBuilderOrgServiceStep {
		private final PB01AWorkPlaceOrgData _src;

		public PB01AWorkPlaceOrgDataBuilderOrgServiceLocationStep service(final PB01AOrgDivisionServiceID serviceId) {
			_src.setServiceId(serviceId);
			return new PB01AWorkPlaceOrgDataBuilderOrgServiceLocationStep(_src);
		}
	}
	@RequiredArgsConstructor(access=AccessLevel.PRIVATE)
	public class PB01AWorkPlaceOrgDataBuilderOrgServiceLocationStep {
		private final PB01AWorkPlaceOrgData _src;

		public PB01AWorkPlaceOrgDataBuilderUserStep locatedAt(final PB01AOrganizationServiceLocationGroupID groupId,final PB01AOrgDivisionServiceLocationID locId) {
			_src.setLocationGroupId(groupId);
			_src.setLocationId(locId);
			return new PB01AWorkPlaceOrgDataBuilderUserStep(_src);
		}
		public PB01AWorkPlaceOrgDataBuilderUserStep locatedAt(final PB01AOrgDivisionServiceLocationID locId) {
			return this.locatedAt(null,locId);
		}
	}
	@RequiredArgsConstructor(access=AccessLevel.PRIVATE)
	public class PB01AWorkPlaceOrgDataBuilderUserStep {
		private final PB01AWorkPlaceOrgData _src;

		public PB01AWorkPlaceOrgDataBuilderTerminalStep forUser(final UserCode userCode) {
			_src.setUserCode(userCode);
			return new PB01AWorkPlaceOrgDataBuilderTerminalStep(_src);
		}
	}
	@RequiredArgsConstructor(access=AccessLevel.PRIVATE)
	public class PB01AWorkPlaceOrgDataBuilderTerminalStep {
		private final PB01AWorkPlaceOrgData _src;

		public PB01AWorkPlaceOrgData usingTerminalWithId(final PB01ATerminalID terminalId) {
			_src.setTerminalId(terminalId);
			return _src;
		}
	}
}
