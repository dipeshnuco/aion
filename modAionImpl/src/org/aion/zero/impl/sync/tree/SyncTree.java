package org.aion.zero.impl.sync.tree;

import org.aion.base.type.IBlockHeader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * SyncTree represents an alternative syncing strategy, based on the idea of
 * building a tree/graph structure of headers to make the best probable decision
 * as to whom to sync from
 */
public class SyncTree {

    /**
     * <p>Represents the height the tree grows to, if we represent our current position
     * as the ground, then the tree represents the future header blocks that we have not
     * yet seen</p>
     */
    public static final int TREE_HEIGHT = 10240;

    /**
     * <p>Represents the roots of the tree, similar to the description of {@link #TREE_HEIGHT}
     * if we visualize ourselves as the ground, then the root is the backtracking parameter
     * that we are willing to go to</p>
     */
    public static final int TREE_ROOT = 256;

    private final Object groundLock = new Object();

    /**
     * We always calculate root from the perspective of ground - TREE_ROOT, therfore the root
     * refers to the bottom-most element of the tree
     */
    private BHNode rootNode = null;

    private long ground = 0;
    private long rootGround = 0;

    /**
     * Stores the set of tips, each tip represents the currently highest/greatest block
     * that we have retrieved from the peer
     */
    private final Map<SyncPeer, BHNode> tips = new HashMap<>();

    /**
     * Sets the current ground, this can cause the tree to either shift up (thereby removing roots)
     * Or shift down, thereby removing branches, if you shift up or down by more than 1024 + 256
     * you're effectively emptying the tree.
     */
    public void setGround(final long ground) {
        rootNode = new BHNode(null, Math.max(ground - TREE_ROOT, 0), null);
    }

    public long getGround() {
        // TODO
        return 0;
    }

    public void updatePeers() {
        // TODO
    }


    public void putHeaders(List<IBlockHeader> headers, SyncPeer peer) {
        if (rootNode == null)
            throw new NullPointerException("rootNode cannot be null when putting headers");

        headers.removeIf((h) -> h.getNumber() < rootNode.number);
        // indicates that we only set ground, we dont have a hash yet
        // we want to replace this rootNode with one that is correct
        if (rootNode.blockHash == null) {
            if (headers.get(0).getNumber() != rootNode.number)
                return;

            // otherwise, add all the headers
            for (IBlockHeader header : headers) {
                // TODO
            }
        }
    }

    public void run() {

    }
}
