public class Piece {
    private int gameType;//0为国际象棋棋子，1为围棋棋子
    private int color;//当gametype为1时该字段有效，0为黑色，1为白色
    private Player player = null;//所属的玩家，如果该值为null则表示这个地方没有棋子
    private int chess;//当gametype为0时该字段有效，
    //0国王，1皇后，2战车，3主教，4骑士，5禁卫

    public int getGameType() {
        return gameType;
    }

    public void setGameType(int gameType) {
        this.gameType = gameType;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getChess() {
        return chess;
    }

    public void setChess(int chess) {
        this.chess = chess;
    }
}
