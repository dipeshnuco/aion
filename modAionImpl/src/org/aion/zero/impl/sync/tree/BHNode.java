package org.aion.zero.impl.sync.tree;

import org.aion.base.util.ByteArrayWrapper;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class BHNode {
    public final ByteArrayWrapper blockHash;
    public final long number;
    public final BigInteger relDiff;
    public final Set<SyncPeer> hasPeers = new LinkedHashSet<>();
    public final List<BHNode> nextLinks = new ArrayList<>();

    public BHNode(final ByteArrayWrapper blockHash,
                  final long number,
                  final BigInteger relDiff) {
        this.blockHash = blockHash;
        this.number = number;
        this.relDiff = relDiff;
    }

    /**
     * Adds a peer into the set, indicates that the current blockHash
     * is is maintained by the peer
     */
    public void addPeer(SyncPeer peer) {
        hasPeers.add(peer);
    }

    public void removePeer(SyncPeer peer) {
        hasPeers.remove(peer);
    }

    public boolean isMaintained() {
        return !hasPeers.isEmpty();
    }

    public void addLink(BHNode next) {
        for (BHNode wrapper : nextLinks) {
            if (wrapper == next)
                    return;
        }
        nextLinks.add(next);
    }
}
