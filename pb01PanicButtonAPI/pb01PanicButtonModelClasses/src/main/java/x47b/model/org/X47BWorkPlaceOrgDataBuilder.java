package x47b.model.org;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import r01f.guids.CommonOIDs.UserCode;
import r01f.patterns.IsBuilder;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationServiceLocationGroupID;
import x47b.model.oids.X47BServiceIDs.X47BTerminalID;

/**
 * Builds an {@link X47BWorkPlaceOrgData} object
 */
@NoArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class X47BWorkPlaceOrgDataBuilder
	 implements IsBuilder {
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public static X47BWorkPlaceOrgDataBuilderOrgServiceStep createFor(final X47BOrganizationID orgId) {
		X47BWorkPlaceOrgData src = new X47BWorkPlaceOrgData();
		src.setOrgId(orgId);
		return new X47BWorkPlaceOrgDataBuilder() {/* nothing */}
						.new X47BWorkPlaceOrgDataBuilderOrgServiceStep(src);
	}
	public static X47BWorkPlaceOrgDataBuilderOrgServiceStep createForEJGV() {
		return X47BWorkPlaceOrgDataBuilder.createFor(X47BOrganizationID.EJGV);
	}
	public static X47BWorkPlaceOrgDataBuilderOrgServiceLocationStep createForZuzenean() {
		return X47BWorkPlaceOrgDataBuilder.createFor(X47BOrganizationID.EJGV)
										   .service(X47BOrgDivisionServiceID.ZUZENEAN);		
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@RequiredArgsConstructor(access=AccessLevel.PRIVATE)
	public class X47BWorkPlaceOrgDataBuilderOrgServiceStep {
		private final X47BWorkPlaceOrgData _src;
		
		public X47BWorkPlaceOrgDataBuilderOrgServiceLocationStep service(final X47BOrgDivisionServiceID serviceId) {
			_src.setServiceId(serviceId);
			return new X47BWorkPlaceOrgDataBuilderOrgServiceLocationStep(_src);
		}
	}
	@RequiredArgsConstructor(access=AccessLevel.PRIVATE)
	public class X47BWorkPlaceOrgDataBuilderOrgServiceLocationStep {
		private final X47BWorkPlaceOrgData _src;
		
		public X47BWorkPlaceOrgDataBuilderUserStep locatedAt(final X47BOrganizationServiceLocationGroupID groupId,final X47BOrgDivisionServiceLocationID locId) {
			_src.setLocationGroupId(groupId);
			_src.setLocationId(locId);
			return new X47BWorkPlaceOrgDataBuilderUserStep(_src);
		}
		public X47BWorkPlaceOrgDataBuilderUserStep locatedAt(final X47BOrgDivisionServiceLocationID locId) {
			return this.locatedAt(null,locId);
		}		
	}
	@RequiredArgsConstructor(access=AccessLevel.PRIVATE)
	public class X47BWorkPlaceOrgDataBuilderUserStep {
		private final X47BWorkPlaceOrgData _src;
		
		public X47BWorkPlaceOrgDataBuilderTerminalStep forUser(final UserCode userCode) {
			_src.setUserCode(userCode);
			return new X47BWorkPlaceOrgDataBuilderTerminalStep(_src);
		}
	}
	@RequiredArgsConstructor(access=AccessLevel.PRIVATE)
	public class X47BWorkPlaceOrgDataBuilderTerminalStep {
		private final X47BWorkPlaceOrgData _src;
		
		public X47BWorkPlaceOrgData usingTerminalWithId(final X47BTerminalID terminalId) {
			_src.setTerminalId(terminalId);
			return _src;
		}
	}
}
