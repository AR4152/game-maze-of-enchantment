package comp2120.a3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemManager {

    private int[][] maze;
    private Item[][] items;
    private List<Item> allItems;

    public ItemManager(int[][] maze) {
        this.maze = maze;
        this.items = new Item[maze.length][maze[0].length];
        this.allItems = new ArrayList<>();
    }

    /**
     * add items
     * @param item
     * @author Oscar Wei
     */
    public void addItems(Item item){
        allItems.add(item);
    }

    /**
     * Return the items in the maze
     * @return items
     * @author Oscar Wei
     */
    public Item[][] getItemsInMaze() {
        return items;
    }

    /**
     * make items appear in the maze randomly
     * @author Oscar Wei
     */
    public void placeRandomItems() {
        List<Point> availablePoints = getAvailablePoints();

        Random rand = new Random();

        for (Item item : allItems) {
            if (availablePoints.isEmpty()) {
                System.out.println("No more available points to place items!");
                return;
            }

            int randomIndex = rand.nextInt(availablePoints.size());
            Point point = availablePoints.get(randomIndex);
            items[point.x][point.y] = item;

            // Remove the point from available points to avoid placing multiple items on the same spot
            availablePoints.remove(randomIndex);
        }
    }

    /**
     * return the position which can place items
     * @return list of pos
     * @author Oscar Wei
     */
    private List<Point> getAvailablePoints() {
        List<Point> availablePoints = new ArrayList<>();

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 0) {
                    availablePoints.add(new Point(i, j));
                }
            }
        }

        return availablePoints;
    }

    private static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
