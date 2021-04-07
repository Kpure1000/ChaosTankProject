package Module;

import java.io.Serializable;

/**
 * Player
 */
public class Player implements Serializable {
    public int pid;

    public int state;

    public Player() {
    }

    public Player(int pid) {
        this.pid = pid;
    }
}
