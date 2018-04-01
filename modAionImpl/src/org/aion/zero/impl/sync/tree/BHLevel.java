package org.aion.zero.impl.sync.tree;

import org.aion.base.util.ByteArrayWrapper;
import org.aion.zero.types.A0BlockHeader;

import java.math.BigInteger;
import java.util.*;

public class BHLevel {
    private long level = 0;
    private final Map<SyncPeer, BHNode> peerBlockMap = new HashMap<>();

    public BHLevel(long level) {
        assert level >= 0;
        this.level = level;
    }

    // policy is always replacement, just in case
    private BHNode putHeader(A0BlockHeader header, BigInteger previousDiff, SyncPeer peer) {
        assert header.getNumber() == this.level;
        BHNode wrapper = new BHNode(
                new ByteArrayWrapper(header.getHash()),
                header.getNumber(),
                previousDiff.add(header.getDifficultyBI()),
                peer);
        return this.peerBlockMap.put(peer, wrapper);
    }
}
