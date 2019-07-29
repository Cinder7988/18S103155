import java.util.List;
import java.util.PrimitiveIterator;

public class Action {
    /*
    围棋的下子动作
     */
    public static String put(Game game, Player player, int x, int y){
        int type = game.getGameType();
        List<List<Piece>> pieces = Position.pieces;
        if(type == 1){//围棋
            if(x >18 || y > 18 || x < 0 || y < 0)
                return "非法位置";
            if(pieces.get(x).get(y).getPlayer() != null)
                return "该位置已有棋子";

            pieces.get(x).get(y).setPlayer(player);
            pieces.get(x).get(y).setColor(player.getRank());
            return "OK";
        }
        else {//国际象棋
            return "非法操作";
        }
    }

    /*
    国际象棋的放子操作，应该是针对禁卫晋升机制
     */
    public static String put(Game game, Player player, int x, int y, int a){
        int type = game.getGameType();
        List<List<Piece>> pieces = Position.pieces;
        if(type == 0){//国际象棋
            if(x >8 || y > 8 || x < 0 || y < 0)
                return "非法位置";
            if(pieces.get(x).get(y).getPlayer() != null && pieces.get(x).get(y).getPlayer() != player)
                return "你不能动别人的棋子";
            if(a > 5 || a < 0)
                return "非法的棋子类型。\n" +
                        "0-国王，1-皇后，2-禁卫，3-主教，4-骑士，5-禁卫\n";
            int[][] count = new int[2][6];
            if(pieces.get(x).get(y).getPlayer() == player && pieces.get(x).get(y).getChess() == a)
                return "OK";
            for(int i = 0; i < 8; i ++){
                List<Piece> temp = pieces.get(i);
                for(int j = 0; j < 8; j ++){
                    if(temp.get(j).getPlayer() == null)
                        continue;
                    count[temp.get(j).getColor()][temp.get(j).getChess()]++;
                }
            }
            if(a == 0 && count[player.getRank()][a] >=1)
                return "当前棋子数量已达上限";
            else if(a == 1 && count[player.getRank()][a] >=1)
                return "当前棋子数量已达上限";
            else if(a == 2 && count[player.getRank()][a] >=2)
                return "当前棋子数量已达上限";
            else if(a == 3 && count[player.getRank()][a] >=2)
                return "当前棋子数量已达上限";
            else if(a == 4 && count[player.getRank()][a] >=2)
                return "当前棋子数量已达上限";
            else if(a == 5 && count[player.getRank()][a] >=8)
                return "当前棋子数量已达上限";
            pieces.get(x).get(y).setPlayer(player);
            pieces.get(x).get(y).setChess(a);
            return "OK";
        }
        else {//国际象棋
            return "非法操作";
        }
    }
    /*
    国际象棋的移动棋子动作,包括吃子
     */
    public static String move(Game game, Player player, int x1, int y1, int x2, int y2){
        int type = game.getGameType();
        List<List<Piece>> pieces = Position.pieces;
        if(type == 0){//国际象棋
            if(x1 > 7 || y1 > 7 ||x2 >7 || y2 > 7 || x1 < 0 || y1 < 0|| x2 < 0 || y2 < 0)
                return "非法位置";
            if(pieces.get(x1).get(y1).getPlayer() == null)
                return "起始位置没有棋子";
            if(pieces.get(x1).get(y1).getPlayer() != player)
                return "该棋子不是你的";
            if(pieces.get(x2).get(y2).getPlayer() == player)
                return "目标位置有己方棋子";
            /*
            以下为合法情况，即起始点是自己的棋子，目标点不是自己的棋子
            不管是空白还是敌方棋子，都是将目标棋子改成与起始位置相同，然后清除起始点
             */
            Piece from = pieces.get(x1).get(y1);
            Piece to = pieces.get(x2).get(y2);
            to.setPlayer(player);
            to.setChess(from.getChess());
            from.setPlayer(null);
            return "OK";
        }
        else {//围棋
            return "非法操作";
        }
    }

    /*
    围棋的吃子操作
     */
    public static String kill(Game game, Player player, int x, int y){
        int type = game.getGameType();
        List<List<Piece>> pieces = Position.pieces;
        if(type == 1) {
            if (x > 18 || y > 18 || x < 0 || y < 0)
                return "非法位置";
            if (pieces.get(x).get(y).getPlayer() == null)
                return "该位置没有棋子";
            if (pieces.get(x).get(y).getPlayer() == player)
                return "这是你的棋子";
            pieces.get(x).get(y).setPlayer(null);
            return "OK";
        }
        else
            return "非法操作";
    }

    /*
    查询某个位置
     */
    public static String partInfo(Game game, int x, int y){
        int type = game.getGameType();
        List<List<Piece>> pieces = Position.pieces;
        String res = "";
        if(type == 1){//围棋
            res += "当前游戏模式：围棋\n";
            if (x > 18 || y > 18 || x < 0 || y < 0)
                return "非法位置";
            Piece piece = pieces.get(x).get(y);
            if(piece.getPlayer() == null){
                res += "当前位置没有棋子\n";
                return res;
            }
            else{
                res += "所属玩家：" + piece.getPlayer().getName()+"\n";
                res += "棋子类型：";
                res += piece.getColor() == 0 ? "黑子\n" : "白子\n";
            }
        }
        else {
            res += "当前游戏模式：国际象棋\n";
            if (x > 7 || y > 7 || x < 0 || y < 0)
                return "非法位置";
            Piece piece = pieces.get(x).get(y);
            if(piece.getPlayer() == null){
                res += "当前位置没有棋子\n";
                return res;
            }
            else{
                res += "所属玩家：" + piece.getPlayer().getName()+"\n";
                res += "棋子类型：";
                if(piece.getChess() == 0)
                    res += "国王\n";
                else if(piece.getChess() == 1)
                    res += "皇后\n";
                else if(piece.getChess() == 2)
                    res += "战车\n";
                else if(piece.getChess() == 3)
                    res += "主教\n";
                else if(piece.getChess() == 4)
                    res += "骑士\n";
                else if(piece.getChess() == 5)
                    res += "禁卫\n";
            }
        }
        return res;
    }

    /*
    查询所有棋子个数
     */
    public static String overallInfo(Game game){
        int type = game.getGameType();
        List<List<Piece>> pieces = Position.pieces;
        String res = "";
        if(type == 1){
            int[] count = new int[2];
            res += "当前游戏模式：围棋\n";
            for(int i = 0; i < 19; i ++){
                List<Piece> temp = pieces.get(i);
                for(int j = 0; j < 19; j ++){
                    if(temp.get(j).getPlayer() == null)
                        continue;
                    count[temp.get(j).getColor()] ++;
                }
            }
            res += "玩家1："+game.getPlayers()[0].getName() + "\n";
            res += "执子：";
            res += game.getPlayers()[0].getRank() == 0 ? "黑子\n" : "白子\n";
            res += "棋子数量：" + String.valueOf(count[game.getPlayers()[0].getRank()]) + " \n";
            res += "玩家2："+game.getPlayers()[1].getName() + "\n";
            res += "执子：";
            res += game.getPlayers()[1].getRank() == 0 ? "黑子\n" : "白子\n";
            res += "棋子数量：" + String.valueOf(count[game.getPlayers()[1].getRank()]) + " \n";
            return res;
        }
        else {
            res += "当前游戏模式：国际象棋\n";
            int[][] count = new int[2][6];
            for(int i = 0; i < 8; i ++){
                List<Piece> temp = pieces.get(i);
                for(int j = 0; j < 8; j ++){
                    if(temp.get(j).getPlayer() == null)
                        continue;
                    count[temp.get(j).getPlayer().getRank()][temp.get(j).getChess()]++;
                }
            }
            int k = 0;
            res += "玩家1："+game.getPlayers()[k].getName() + "\n";
            res += "执子：";
            res += game.getPlayers()[k].getRank() == 0 ? "白子\n" : "黑子\n";
            res += "国王" + String.valueOf(count[game.getPlayers()[k].getRank()][0]) + "个 \n";
            res += "皇后" + String.valueOf(count[game.getPlayers()[k].getRank()][1]) + "个 \n";
            res += "战车" + String.valueOf(count[game.getPlayers()[k].getRank()][2]) + "个 \n";
            res += "主教" + String.valueOf(count[game.getPlayers()[k].getRank()][3]) + "个 \n";
            res += "骑士" + String.valueOf(count[game.getPlayers()[k].getRank()][4]) + "个 \n";
            res += "禁卫" + String.valueOf(count[game.getPlayers()[k].getRank()][5]) + "个 \n";

            k = 1;
            res += "玩家2："+game.getPlayers()[k].getName() + "\n";
            res += "执子：";
            res += game.getPlayers()[k].getRank() == 0 ? "白子\n" : "黑子\n";
            res += "国王" + String.valueOf(count[game.getPlayers()[k].getRank()][0]) + "个 \n";
            res += "皇后" + String.valueOf(count[game.getPlayers()[k].getRank()][1]) + "个 \n";
            res += "战车" + String.valueOf(count[game.getPlayers()[k].getRank()][2]) + "个 \n";
            res += "主教" + String.valueOf(count[game.getPlayers()[k].getRank()][3]) + "个 \n";
            res += "骑士" + String.valueOf(count[game.getPlayers()[k].getRank()][4]) + "个 \n";
            res += "禁卫" + String.valueOf(count[game.getPlayers()[k].getRank()][5]) + "个 \n";
            return res ;
        }
    }
}
