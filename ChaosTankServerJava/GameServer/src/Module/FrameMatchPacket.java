package Module;

import java.io.Serializable;
import java.util.List;

/**
 * Logic frame (output)
 */
public class FrameMatchPacket implements Serializable {
    /**
     * Frame ID
     */
    public int fid;

    public int playerCount;
    List<PlayerOperation> operations;

    ///////////////////////////////////

    public FrameMatchPacket(){
    }

    @Override
    public String toString() {
        return String.format("frame id: %d, player operations: %d/%d", fid, operations.size(), playerCount);
    }
}
