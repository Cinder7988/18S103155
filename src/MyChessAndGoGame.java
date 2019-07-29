import java.lang.management.GarbageCollectorMXBean;
import java.util.Scanner;

public class MyChessAndGoGame {
    public static Game gameStart(){
        System.out.println("棋类游戏--胥岩\n");
        System.out.print("输入游戏类型：");
        Scanner scanner = new Scanner(System.in);
        String gameStart = scanner.nextLine();
        Game game = new Game();
        if(gameStart.equals("chess"))
            game.setGameType(0);
        else if(gameStart.equals("go"))
            game.setGameType(1);
        else {
            System.out.println("非法输入，游戏结束，：D");
            return null;
        }
        System.out.print("请输入先手玩家昵称：");
        String name = scanner.nextLine();
        Player player = new Player();
        player.setName(name);
        player.setRank(0);
        game.getPlayers()[0] = player;
        System.out.print("请输入后手玩家昵称：");
        String name2 = scanner.nextLine();
        while(name2.equals(name)){
            System.out.println("玩家昵称不可重复。");
            System.out.print("请输入后手玩家昵称：");
            name2 = scanner.nextLine();
        }
        player = new Player();
        player.setName(name2);
        player.setRank(1);
        game.getPlayers()[1] = player;
        game.start();
        return game;
    }
    public static void playGame(Game game){
        if(game == null)
            return ;
        String s = game.getGameType() == 0? "国际象棋":"围棋";
        System.out.println("游戏类型：" + s);
        System.out.println("先手：" + game.getPlayers()[0].getName());
        System.out.println("后手：" + game.getPlayers()[1].getName());
        System.out.println("游戏开始：D");
        Scanner scanner = new Scanner(System.in);
        int rightPlayer = 0;
        while(true){
            /*
            此处开始获取用户输入
            "info"：查看棋盘上所有棋子信息
            "info x y"：查看坐标为x,y的位置的信息
            "put x y"：针对围棋，在x y处下一个子
            "move x1 y1 x2 y2"：针对国际象棋，将x1, y1位置的棋子移动到x2，y2处
            "kill x y"：针对围棋，将x y 处的棋子干掉
            "end"：结束游戏，显示历史操作记录
            "put a x y"：针对国际象棋，在x，y的位置放一个类型为a的棋子，a为整数
            "pass"：跳过当前回合

             */
            Player player = game.getPlayers()[rightPlayer];
            System.out.print(player.getName() + ": ");
            String op = scanner.nextLine();
            op = op.trim();
            String [] temp = op.split(" ");
            if(temp[0].equals("info") && temp.length == 1)
                System.out.println(Action.overallInfo(game));
            else if(temp[0].equals("info") && temp.length == 3) {
                int x = 0;
                int y = 0;
                try{
                    x = Integer.parseInt(temp[1]);
                    y = Integer.parseInt(temp[2]);
                }catch (NumberFormatException e){
                    System.out.println("坐标格式错误");
                    continue;
                }
                System.out.println(Action.partInfo(game, x, y));
            }
            else if(temp[0].equals("put") && temp.length == 3) {
                int x = 0;
                int y = 0;
                try{
                    x = Integer.parseInt(temp[1]);
                    y = Integer.parseInt(temp[2]);
                }catch (NumberFormatException e){
                    System.out.println("坐标格式错误");
                    continue;
                }
                String returnString = Action.put(game, player, x, y);
                if(!returnString.equals("OK")){
                    System.out.println(returnString);
                    continue;
                }
                game.getHistory().add(player.getName() + ":" + op);
                rightPlayer = rightPlayer == 0 ? 1 : 0;
            }
            else if(temp[0].equals("put") && temp.length == 4) {
                int x = 0;
                int y = 0;
                int a = 0;
                try{
                    a = Integer.parseInt(temp[1]);
                    x = Integer.parseInt(temp[2]);
                    y = Integer.parseInt(temp[3]);
                }catch (NumberFormatException e){
                    System.out.println("参数格式错误");
                    continue;
                }
                String returnString = Action.put(game, player, x, y,a);
                if(!returnString.equals("OK")){
                    System.out.println(returnString);
                    continue;
                }
                game.getHistory().add(player.getName() + ":" + op);
                rightPlayer = rightPlayer == 0 ? 1 : 0;
            }

            else if(temp[0].equals("move") && temp.length == 5) {
                int x1 = 0;
                int y1 = 0;
                int x2 = 0;
                int y2 = 0;
                try{
                    x1 = Integer.parseInt(temp[1]);
                    y1 = Integer.parseInt(temp[2]);
                    x2 = Integer.parseInt(temp[3]);
                    y2 = Integer.parseInt(temp[4]);
                }catch (NumberFormatException e){
                    System.out.println("参数格式错误");
                    continue;
                }
                String returnString = Action.move(game,player,x1,y1,x2,y2);
                if(!returnString.equals("OK")){
                    System.out.println(returnString);
                    continue;
                }
                game.getHistory().add(player.getName() + ":" + op);
                rightPlayer = rightPlayer == 0 ? 1 : 0;
            }

            else if(temp[0].equals("kill") && temp.length == 3) {
                int x = 0;
                int y = 0;
                try{
                    x = Integer.parseInt(temp[1]);
                    y = Integer.parseInt(temp[2]);
                }catch (NumberFormatException e){
                    System.out.println("参数格式错误");
                    continue;
                }
                String returnString = Action.kill(game,player,x,y);
                if(!returnString.equals("OK")){
                    System.out.println(returnString);
                    continue;
                }
                game.getHistory().add(player.getName() + ":" + op);
                rightPlayer = rightPlayer == 0 ? 1 : 0;
            }
            else if(temp[0].equals("pass") && temp.length == 1) {
                game.getHistory().add(player.getName() + ":" + op);
                rightPlayer = rightPlayer == 0 ? 1 : 0;
            }
            else if(temp[0].equals("end") && temp.length == 1) {
                for(int i = 0; i < game.getHistory().size(); i ++)
                    System.out.println(game.getHistory().get(i));
                break;
            }
            else {
                System.out.println("未知指令");
            }
        }
    }
    public static void main(String[] a){
        Game game = gameStart();
        playGame(game);
    }
}
