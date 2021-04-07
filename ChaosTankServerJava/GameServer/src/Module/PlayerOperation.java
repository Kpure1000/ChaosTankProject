package Module;

import java.io.Serializable;

/**
 * Logic input (input)
 */
public class PlayerOperation implements Serializable {
    /**
     * player id
     */
    public int pid;

    public int xInput;
    public int yInput;

    @Override
    public String toString() {
        return String.format("player:%d, pos:(%d, %d) ", pid, xInput, yInput);
    }
}
