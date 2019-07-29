package x47b.model.oids;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import r01f.annotations.Immutable;
import r01f.objectstreamer.annotations.MarshallType;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOIDBase;

/**
 * Panic button service identifiers definitions.
 */
@RequiredArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class X47BPanicButtonOIDs {
	/**
	 * OID for a raised alarm event
	 */
	@Immutable
	@MarshallType(as="alarmEventOid")
	@NoArgsConstructor
	public static class X47BAlarmEventOID
				extends X47BPersistableObjectOIDBase {

		private static final long serialVersionUID = 2287252000929127752L;
		public X47BAlarmEventOID(final String oid) {
			super(oid);
		}
		public static X47BAlarmEventOID valueOf(final String s) {
			return X47BAlarmEventOID.forId(s);
		}
		public static X47BAlarmEventOID fromString(final String s) {
			return X47BAlarmEventOID.forId(s);
		}
		public static X47BAlarmEventOID forId(final String id) {
			return new X47BAlarmEventOID(id);
		}
		public static X47BAlarmEventOID supply() {
			return X47BAlarmEventOID.forId(X47BPersistableObjectOIDBase.supplyId());
		}
	}
}
