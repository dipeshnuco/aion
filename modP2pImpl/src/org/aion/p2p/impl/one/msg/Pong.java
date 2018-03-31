package org.aion.p2p.impl.one.msg;

import org.aion.base.util.ByteUtil;
import org.aion.p2p.Ctrl;
import org.aion.p2p.Msg;
import org.aion.p2p.Ver;
import org.aion.p2p.impl.Act;

public class Pong extends Msg {

    public static final Pong PONG = new Pong();

    private Pong() {
        super(Ver.V1, Ctrl.NET, Act.PONG);
    }

    @Override
    public byte[] encode() {
        return ByteUtil.EMPTY_BYTE_ARRAY;
    }
}
