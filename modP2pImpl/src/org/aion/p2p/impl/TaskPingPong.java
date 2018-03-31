package org.aion.p2p.impl;

import org.aion.p2p.impl.one.msg.Ping;

import java.util.List;

public class TaskPingPong implements Runnable {

    private final P2pMgr p2p;
    private final NodeMgr nodes;
    private final long timeout;
    private final long initialDelay;

    public TaskPingPong(P2pMgr p2p, NodeMgr nodeMgr, long timeoutMillis, long initialDelayMillis) {
        this.p2p = p2p;
        this.nodes = nodeMgr;
        this.timeout = timeoutMillis;
        this.initialDelay = initialDelayMillis;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(this.initialDelay);
        } catch (InterruptedException e) {
            return;
        }

        while(!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                break;
            }

            long time = System.currentTimeMillis();
            List<Node> nodes = this.nodes.getActiveNodesList();

            for (Node n : nodes) {
                if (time - n.getTimestamp() > this.timeout)
                    this.p2p.send(n.getIdHash(), Ping.PING);
            }
        }
    }
}
