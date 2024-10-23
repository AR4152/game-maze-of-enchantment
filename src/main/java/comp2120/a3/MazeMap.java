package comp2120.a3;

public class MazeMap {
    //used as object to obtain the data analysis from json file,each level corresponding to each maze
    private String name;
    private int observeTime;
    private int limitedTime;
    private int index;
    private boolean isDark;
    private int[][] map;

    /**
     * set the name of maze
     * @param name
     * @author WangLin Song
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * set observe time of maze
     * @param observeTime
     * @author WangLin Song
     */
    public void setObserveTime(int observeTime) {
        this.observeTime = observeTime;
    }

    /**
     * set limited time of maze
     * @param limitedTime
     * @author WangLin Song
     */

    public void setLimitedTime(int limitedTime) {
        this.limitedTime = limitedTime;
    }

    /**
     * set index of maze
     * @param index
     * @author WangLin Song
     */

    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * set dark of maze
     * @param dark
     * @author WangLin Song
     */

    public void setDark(boolean dark) {
        isDark = dark;
    }

    /**
     * get map of maze
     * @return map
     * @author WangLin Song
     */
    public int[][] getMap() {
        return map;
    }

    /**
     * set map of maze
     * @param map
     * @author WangLin Song
     */
    public void setMap(int[][] map) {
        this.map = map;
    }
}
