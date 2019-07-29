import java.util.ArrayList;
import java.util.List;

public class Game {
    private int gameType;//0为国际象棋，1为围棋
    private Player[] players = new Player[2];
    private Board board;
    private List<String> history = new ArrayList<>();

    public List<String> getHistory() {
        return history;
    }

    public void setHistory(List<String> history) {
        this.history = history;
    }

    public int getGameType() {
        return gameType;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setGameType(int gameType) {
        this.gameType = gameType;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public void start(){
        board = new Board();
        board.setPosition(new Position());
        board.getPosition().initPosition(gameType, players[0], players[1]);
    }
}
