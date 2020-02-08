package pb01a.model.oids;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import pb01a.model.oids.PB01AIDs.PB01AIDBase;
import r01f.annotations.Immutable;
import r01f.objectstreamer.annotations.MarshallType;

/**
 * Panic button service identifiers definitions.
 */
@RequiredArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class PB01AServiceIDs {
/////////////////////////////////////////////////////////////////////////////////////////
//  Inbound channel
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * ID for an  channel
	 */
	@Immutable
	@MarshallType(as="channelId")
	@NoArgsConstructor
	public static class PB01AInboundChannelID
				extends PB01AIDBase {
		private static final long serialVersionUID = 261971775658181041L;
		public PB01AInboundChannelID(final String oid) {
			super(oid);
		}
		public static PB01AInboundChannelID valueOf(final String s) {
			return PB01AInboundChannelID.forId(s);
		}
		public static PB01AInboundChannelID fromString(final String s) {
			return PB01AInboundChannelID.forId(s);
		}
		public static PB01AInboundChannelID forId(final String id) {
			return new PB01AInboundChannelID(id);
		}
		public static PB01AInboundChannelID PHONE = PB01AInboundChannelID.forId("phone");
		public static PB01AInboundChannelID FACE_TO_FACE = PB01AInboundChannelID.forId("faceToFace");
		public static PB01AInboundChannelID ELECTRONIC = PB01AInboundChannelID.forId("electronic");
		public static PB01AInboundChannelID TELEGRAM = PB01AInboundChannelID.forId("telegram");

		public static PB01AInboundChannelID PHONE_CODE = PB01AInboundChannelID.forId("1");
		public static PB01AInboundChannelID FACE_TO_FACE_CODE = PB01AInboundChannelID.forId("0");
		public static PB01AInboundChannelID TELEGRAM_CODE = PB01AInboundChannelID.forId("2");
		public static PB01AInboundChannelID ELECTRONIC_CODE = PB01AInboundChannelID.forId("3");

		public static PB01AInboundChannelID fromCode(final String code) {
			PB01AInboundChannelID outChannel = null;
			if (code.equals(PHONE_CODE.asString())) {
				outChannel = PHONE;
			}
			else if (code.equals(FACE_TO_FACE_CODE.asString())) {
				outChannel = FACE_TO_FACE;
			}
			else if (code.equals(TELEGRAM_CODE.asString())) {
				outChannel = TELEGRAM;
			}
			else if (code.equals(ELECTRONIC_CODE.asString())) {
				outChannel = ELECTRONIC;
			}
			if (outChannel == null) throw new IllegalArgumentException(code + " is NOT a valid inbound channel!");
			return outChannel;
		}

		public static int convertToIntegerFromID(final String id) {
			int outChannel = -1;
			if (id.equals(PHONE.asString())) {
				outChannel = Integer.parseInt(PHONE_CODE.asString());
			}
			else if (id.equals(FACE_TO_FACE.asString())) {
				outChannel = Integer.parseInt(FACE_TO_FACE_CODE.asString());
			}
			else if (id.equals(TELEGRAM.asString())) {
				outChannel = Integer.parseInt(TELEGRAM_CODE.asString());
			}
			else if (id.equals(ELECTRONIC.asString())) {
				outChannel = Integer.parseInt(ELECTRONIC_CODE.asString());
			}
			if (outChannel == -1) throw new IllegalArgumentException(id + " is NOT a valid inbound channel!");
			return outChannel;
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  TERMINAL
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * ID for a  source service location
	 */
	@Immutable
	@MarshallType(as="terminalId")
	@NoArgsConstructor
	public static class PB01ATerminalID
				extends PB01AIDBase {
		private static final long serialVersionUID = -6471134589795874469L;
		public PB01ATerminalID(final String oid) {
			super(oid);
		}
		public static PB01ATerminalID valueOf(final String s) {
			return PB01ATerminalID.forId(s);
		}
		public static PB01ATerminalID fromString(final String s) {
			return PB01ATerminalID.forId(s);
		}
		public static PB01ATerminalID forId(final String id) {
			return new PB01ATerminalID(id);
		}
	}
}
