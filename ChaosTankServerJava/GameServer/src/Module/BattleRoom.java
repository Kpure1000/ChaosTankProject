package Module;

import java.util.List;
import java.util.function.Predicate;

/**
 * Room of player in a serial battle
 */
public class BattleRoom {

    /**
     * Room ID
     */
    public int rid;

    //////////////////////////////////
    /// Player
    //////////////////////////////////

    final public int minPlayerCount = 1;
    final public int maxPlayerCount = 3;

    public List<Player> players;

    /**
     * Add player with pid
     *
     * @param pid Player ID
     * @return False if room is full or player exist
     */
    public boolean AddPlayer(int pid) {
        if (players.size() < maxPlayerCount) {
            for (var player :
                    players) {
                if (pid == player.pid) return false;
            }
            players.add(new Player(pid));
            return true;
        }
        return false;
    }

    /**
     * Remove player with pid
     *
     * @param pid Player ID
     * @return False if room is empty or player not exist
     */
    public boolean RemovePlayer(int pid) {
        return players.removeIf(player -> player.pid == pid);
    }

    //////////////////////////////////
    /// Battle
    //////////////////////////////////

    public Battle currentBattle;

}
