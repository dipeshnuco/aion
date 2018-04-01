package org.aion.zero.impl.sync.tree;

import org.aion.base.util.ByteArrayWrapper;

public class BHSegment {
    public volatile long relativeDifficulty;
    public volatile long startingPoint;
    public volatile ByteArrayWrapper startingHash;
}
