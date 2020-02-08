package pb01a.model.oids;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOIDBase;
import r01f.annotations.Immutable;
import r01f.objectstreamer.annotations.MarshallType;

/**
 * Panic button service identifiers definitions.
 */
@RequiredArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class PB01APanicButtonOIDs {
	/**
	 * OID for a raised alarm event
	 */
	@Immutable
	@MarshallType(as="alarmEventOid")
	@NoArgsConstructor
	public static class PB01AAlarmEventOID
				extends PB01APersistableObjectOIDBase {

		private static final long serialVersionUID = 2287252000929127752L;
		public PB01AAlarmEventOID(final String oid) {
			super(oid);
		}
		public static PB01AAlarmEventOID valueOf(final String s) {
			return PB01AAlarmEventOID.forId(s);
		}
		public static PB01AAlarmEventOID fromString(final String s) {
			return PB01AAlarmEventOID.forId(s);
		}
		public static PB01AAlarmEventOID forId(final String id) {
			return new PB01AAlarmEventOID(id);
		}
		public static PB01AAlarmEventOID supply() {
			return PB01AAlarmEventOID.forId(PB01APersistableObjectOIDBase.supplyId());
		}
	}
}
