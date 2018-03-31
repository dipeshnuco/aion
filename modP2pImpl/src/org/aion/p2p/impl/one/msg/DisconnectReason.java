package org.aion.p2p.impl.one.msg;

import java.util.HashMap;
import java.util.Map;

public enum DisconnectReason {
    UNHANDLED_ERROR("Message caused an unhandled error, usually related to IOException", 0),
    TIMEOUT("Peer disconnected because of TIMEOUT", 1),
    REPLACED("Peer replaced this connection with newer one", 2);

    private final String msg;
    private final int code;

    private DisconnectReason(String message, int code) {
        this.msg = message;
        this.code = code;
    }

    private static final Map<Integer, DisconnectReason> reverseMap = new HashMap<>();
    static {
        reverseMap.put(UNHANDLED_ERROR.code, UNHANDLED_ERROR);
        reverseMap.put(TIMEOUT.code, TIMEOUT);
        reverseMap.put(REPLACED.code, REPLACED);
    }

    public String getMessage() {
        return this.getMessage();
    }

    public int getCode() {
        return this.code;
    }

    public static DisconnectReason reason(int code) {
        return reverseMap.get(code);
    }
}
