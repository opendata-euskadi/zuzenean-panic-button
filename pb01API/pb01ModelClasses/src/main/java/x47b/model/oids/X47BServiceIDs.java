package x47b.model.oids;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import r01f.annotations.Immutable;
import r01f.objectstreamer.annotations.MarshallType;
import x47b.model.oids.X47BIDs.X47BIDBase;

/**
 * Panic button service identifiers definitions.
 */
@RequiredArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class X47BServiceIDs {
/////////////////////////////////////////////////////////////////////////////////////////
//  Inbound channel
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * ID for an  channel
	 */
	@Immutable
	@MarshallType(as="channelId")
	@NoArgsConstructor
	public static class X47BInboundChannelID
				extends X47BIDBase {
		private static final long serialVersionUID = 261971775658181041L;
		public X47BInboundChannelID(final String oid) {
			super(oid);
		}
		public static X47BInboundChannelID valueOf(final String s) {
			return X47BInboundChannelID.forId(s);
		}
		public static X47BInboundChannelID fromString(final String s) {
			return X47BInboundChannelID.forId(s);
		}
		public static X47BInboundChannelID forId(final String id) {
			return new X47BInboundChannelID(id);
		}
		public static X47BInboundChannelID PHONE = X47BInboundChannelID.forId("phone");
		public static X47BInboundChannelID FACE_TO_FACE = X47BInboundChannelID.forId("faceToFace");
		public static X47BInboundChannelID ELECTRONIC = X47BInboundChannelID.forId("electronic");
		public static X47BInboundChannelID TELEGRAM = X47BInboundChannelID.forId("telegram");
		
		public static X47BInboundChannelID PHONE_CODE = X47BInboundChannelID.forId("1");
		public static X47BInboundChannelID FACE_TO_FACE_CODE = X47BInboundChannelID.forId("0");
		public static X47BInboundChannelID TELEGRAM_CODE = X47BInboundChannelID.forId("2");
		public static X47BInboundChannelID ELECTRONIC_CODE = X47BInboundChannelID.forId("3");
		
		public static X47BInboundChannelID fromCode(final String code) {
			X47BInboundChannelID outChannel = null;
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
	public static class X47BTerminalID
				extends X47BIDBase {
		private static final long serialVersionUID = -6471134589795874469L;
		public X47BTerminalID(final String oid) {
			super(oid);
		}
		public static X47BTerminalID valueOf(final String s) {
			return X47BTerminalID.forId(s);
		}
		public static X47BTerminalID fromString(final String s) {
			return X47BTerminalID.forId(s);
		}
		public static X47BTerminalID forId(final String id) {
			return new X47BTerminalID(id);
		}
	}
}
