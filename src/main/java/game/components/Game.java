package game.components;
import java.util.*;
public abstract class Game {
	protected List<Players> playersList;
    protected String gameName;

    public Game(String gameName) {
        this.gameName = gameName;
    }

    public void end() {
        System.out.println("Game Over!");
    }

    public List<Players> getPlayersList() {
        return playersList;
    }
}
