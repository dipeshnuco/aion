package org.aion.p2p.impl.one.msg;

import org.aion.base.util.ByteUtil;
import org.aion.p2p.Ctrl;
import org.aion.p2p.Msg;
import org.aion.p2p.Ver;
import org.aion.p2p.impl.Act;

public class DisconnectMsg extends Msg {

    private DisconnectReason reason;

    public DisconnectMsg(DisconnectReason reason) {
        super(Ver.V1, Ctrl.NET, Act.DISCONNECT);
        this.reason = reason;
    }

    public DisconnectReason getReason() {
        return this.reason;
    }

    public static DisconnectMsg decode(final byte[] msg) {
        return new DisconnectMsg(DisconnectReason.reason(ByteUtil.byteArrayToInt(msg)));
    }

    @Override
    public byte[] encode() {
        int code = this.reason.getCode();
        return new byte[] {
                (byte) ((code >> 24) & 0xFF),
                (byte) ((code >> 16) & 0xFF),
                (byte) ((code >> 8) & 0xFF),
                (byte) (code & 0xFF)};
    }
}
