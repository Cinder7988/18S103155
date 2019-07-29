public class Player {
    private String name;
    private int rank;//0为先手，1为后手
    //如果是国际象棋，0为白，1为黑
    //如果是围棋，那么0为黑，1为白
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
