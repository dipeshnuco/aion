package org.aion.zero.impl.sync.tree;

import java.math.BigInteger;

/**
 * Maintains a model of a peer, from the context of Syncing
 */
public class SyncPeer {
    private boolean requestStatusSent;
    private long resStatusSentTimestamp;

    private boolean requestHeaderSent;
    private long resHeaderSentTimestamp;

    private boolean requestBodiesSent;
    private long resBodiesSentTimestamp;

    private BigInteger latestTotalDifficulty;
    private BigInteger latestBlockNumber;

    private long numFailed = 0;
    private long numSuccess = 0;
    private long rating = 0;

    private boolean isActive = false;

    // periodically called to update state, this is used to track the state
    // of the peer over a period of time so we can make judgement calls
    public synchronized void update() {

    }

    public synchronized long getFailed() {
        return this.numFailed;
    }

    public synchronized long getSuccess() {
        return this.numSuccess;
    }

    private synchronized long latestTimestamp() {
        return Math.max(Math.max(resStatusSentTimestamp, resHeaderSentTimestamp), resBodiesSentTimestamp);
    }

    public synchronized void setInactive() {
        this.isActive = false;
    }

    public synchronized void setActive() {
        this.isActive = true;
    }
}
