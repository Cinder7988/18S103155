import java.util.ArrayList;
import java.util.List;

public class Position {

    public static List<List<Piece>> pieces = new ArrayList<>();

    public void initPosition(int gameType, Player first, Player last){
        if(gameType == 1){//围棋
            for(int i = 0; i < 19; i ++){
                List<Piece> temp = new ArrayList<>();
                for(int j = 0; j < 19; j ++){
                    Piece a = new Piece();
                    a.setGameType(1);
                    temp.add(a);
                }
                pieces.add(temp);
            }
        }
        else if(gameType == 0){//国际象棋
            for(int i = 0; i < 8; i ++){
                List<Piece> temp = new ArrayList<>();
                for(int j = 0; j < 8; j ++){
                    Piece a = new Piece();
                    a.setGameType(0);
                    temp.add(a);
                }
                pieces.add(temp);
            }
            for(int i = 0; i < 8; i ++){
                Piece temp = pieces.get(1).get(i);
                temp.setPlayer(last);
                temp.setChess(5);
                temp = pieces.get(6).get(i);
                temp.setPlayer(first);
                temp.setChess(5);
            }
            //初始化战车
            pieces.get(0).get(0).setChess(2);
            pieces.get(0).get(0).setPlayer(last);
            pieces.get(0).get(7).setChess(2);
            pieces.get(0).get(7).setPlayer(last);
            pieces.get(7).get(0).setChess(2);
            pieces.get(7).get(0).setPlayer(first);
            pieces.get(7).get(7).setChess(2);
            pieces.get(7).get(7).setPlayer(first);
            //初始化骑士
            pieces.get(0).get(1).setChess(4);
            pieces.get(0).get(1).setPlayer(last);
            pieces.get(0).get(6).setChess(4);
            pieces.get(0).get(6).setPlayer(last);
            pieces.get(7).get(1).setChess(4);
            pieces.get(7).get(1).setPlayer(first);
            pieces.get(7).get(6).setChess(4);
            pieces.get(7).get(6).setPlayer(first);
            //初始化主教
            pieces.get(0).get(2).setChess(3);
            pieces.get(0).get(2).setPlayer(last);
            pieces.get(0).get(5).setChess(3);
            pieces.get(0).get(5).setPlayer(last);
            pieces.get(7).get(2).setChess(3);
            pieces.get(7).get(2).setPlayer(first);
            pieces.get(7).get(5).setChess(3);
            pieces.get(7).get(5).setPlayer(first);
            //初始化后
            pieces.get(0).get(3).setChess(1);
            pieces.get(0).get(3).setPlayer(last);
            pieces.get(7).get(3).setChess(1);
            pieces.get(7).get(3).setPlayer(first);
            //初始化王
            pieces.get(0).get(4).setChess(0);
            pieces.get(0).get(4).setPlayer(last);
            pieces.get(7).get(4).setChess(0);
            pieces.get(7).get(4).setPlayer(first);
        }
    }
}
