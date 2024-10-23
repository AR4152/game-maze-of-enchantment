package comp2120.a3;

import java.util.List;

public class NPC {
    //create a class for npc, set different attack damage and different blood for different monster on different level
    //as the level increase, enemy will become stronger
    //set level 1 enemy as knight,level 2 as titan,level 3 as dragon
    //set parameters for patrol/ movement for future use
    //set 2 different state for npc(move/attack)
    public static final int LEVEL_1_ATTACK_DAMAGE = 40;
    public static final int LEVEL_2_ATTACK_DAMAGE = 50;
    public static final int LEVEL_3_ATTACK_DAMAGE = 60;
    public static final int LEVEL_1_MAX_BLOOD = 200;
    public static final int LEVEL_2_MAX_BLOOD = 300;
    public static final int LEVEL_3_MAX_BLOOD = 400;
    public static final String LEVEL_1_NAME = "Knight";
    public static final String LEVEL_2_NAME = "Titan";
    public static final String LEVEL_3_NAME = "Dragon";
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    private static final int MAX_DISTANCE = 3;

    public static final int NPC_STATUS_MOVE = 1;
    public static final int NPC_STATUS_ATTACK = 2;


    private int x;
    private int y;
    private int direction;
    private int distance;

    private Inventory inventory;
    private int level = 0;
    private int blood = LEVEL_1_MAX_BLOOD;
    private int attack = LEVEL_1_ATTACK_DAMAGE;
    private String name;

    // Placeholder NPC class
    public String getDialogue() {
        return "Hello, traveler!";
    }

    /**
     * constructor of npc
     * @param x
     * @param y
     * @param level
     */
    public NPC(int x, int y, int level) {
        //constructor for npc
        this.x = x;
        this.y = y;
        this.direction = 1;
        this.inventory = new Inventory();
        this.level = level;
        if (level == 0) {
            blood = LEVEL_1_MAX_BLOOD;
            attack = LEVEL_1_ATTACK_DAMAGE;
            name = LEVEL_1_NAME;
        } else if (level == 1) {
            blood = LEVEL_2_MAX_BLOOD;
            attack = LEVEL_2_ATTACK_DAMAGE;
            name = LEVEL_2_NAME;
        } else if (level == 2) {
            blood = LEVEL_3_MAX_BLOOD;
            attack = LEVEL_3_ATTACK_DAMAGE;
            name = LEVEL_3_NAME;
        }
    }

    public NPC(int x, int y, int level,int direction) {
        //constructor for npc
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.inventory = new Inventory();
        this.level = level;
        if (level == 0) {
            blood = LEVEL_1_MAX_BLOOD;
            attack = LEVEL_1_ATTACK_DAMAGE;
            name = LEVEL_1_NAME;
        } else if (level == 1) {
            blood = LEVEL_2_MAX_BLOOD;
            attack = LEVEL_2_ATTACK_DAMAGE;
            name = LEVEL_2_NAME;
        } else if (level == 2) {
            blood = LEVEL_3_MAX_BLOOD;
            attack = LEVEL_3_ATTACK_DAMAGE;
            name = LEVEL_3_NAME;
        }
    }

    /**
     * determine the direction of movement
     * @param maze
     * @author WangLin Song
     */
    public void checkMoveDirection(int[][] maze) {
        //check the default movement position of npc
        if (isValidMove(x, y - 1, maze)) {
            direction = UP;
        } else if (isValidMove(x + 1, y, maze)) {
            direction = RIGHT;
        } else if (isValidMove(x, y + 1, maze)) {
            direction = DOWN;
        } else if (isValidMove(x - 1, y, maze)) {
            direction = LEFT;
        }
    }

    /**
     * update the movement of npc, check if it can attack player
     * @param maze
     * @param itemsInMaze
     * @param npcList
     * @param player
     * @return NPC_STATUS_MOVE or NPC_STATUS_ATTACK
     * @author WangLin Song
     */
    public int move(int[][] maze, Item[][] itemsInMaze, List<NPC> npcList, Player player) {
        //movement for npc in 0-1 maze
        int nextX = x;
        int nextY = y;
        checkChasing(x, y, player, maze);
        switch (direction) {
            case UP:
                nextY -= 1;
                break;
            case RIGHT:
                nextX += 1;
                break;
            case DOWN:
                nextY += 1;
                break;
            case LEFT:
                nextX -= 1;
                break;
        }
        if (checkAttack(nextX, nextY, player)) {
            //check if the npc need to attack player
            System.out.println("attack !!!");
            return NPC_STATUS_ATTACK;
        }
        if (distance < MAX_DISTANCE && isValidMove(nextX, nextY, maze, npcList)) {
            //check if the npc can make a valid move
//            Item itemAtPosition = itemsInMaze[nextY][nextX];
//            if (itemAtPosition != null) {
//                Item item = new Item("Item " + itemAtPosition); // Simplified item creation
//                inventory.add(item);
//                itemsInMaze[nextY][nextX] = null; // Remove the item from the maze
//                System.out.println("Collected: " + item.getName());
//            }
            x = nextX;
            y = nextY;
            distance++;
        } else {
            //if the movement is not valid(next movement is wall or out of max movement(3 blocks))
            // change direction
            direction = (direction + 2) % 4;
            // reset distance
            distance = 0;
        }
        return NPC_STATUS_MOVE;
    }

    /**
     * check if npc need to change direction to attack player
     * @param x
     * @param y
     * @param player
     * @param maze
     * @author WangLin Song
     */
    public void checkChasing(int x, int y, Player player, int[][] maze) {
        //check if npc need to change direction to attack player
        //e.g.if the player is at the left of npc, npc will move left to chase player
        //the rest has the same logic
        if (player.getX() - x <= 3 && player.getX() - x > 0 && player.getY() == y) {
            if (checkWallHorizontal(x, y, player, maze, RIGHT) && (direction == LEFT || direction == RIGHT)) {
                direction = RIGHT;
                distance = 0;
            }
        }
        if (player.getX() - x > -3 && player.getX() - x < 0 && player.getY() == y) {
            if (checkWallHorizontal(x, y, player, maze, LEFT) && (direction == LEFT || direction == RIGHT)) {
                direction = LEFT;
                distance = 0;
            }
        }
        if (player.getY() - y <= 3 && player.getY() - y > 0 && player.getX() == x) {
            if (checkWallVertical(x, y, player, maze, DOWN) && (direction == UP || direction == DOWN)) {
                direction = DOWN;
                distance = 0;
            }
        }
        if (player.getY() - y > -3 && player.getY() - y < 0 && player.getX() == x) {
            if (checkWallVertical(x, y, player, maze, UP) && (direction == UP || direction == DOWN)) {
                direction = UP;
                distance = 0;
            }
        }
    }

    /**
     * check if there is a wall when npc moves vertically
     * @param x
     * @param y
     * @param player
     * @param maze
     * @param dir
     * @return true or false
     * @author WangLin Song
     */
    private boolean checkWallVertical(int x, int y, Player player, int[][] maze, int dir) {
        //check if there is a wall when npc moves vertically
        for (int i = 0; i < Math.abs(player.getY() - y); i++) {
            if (dir == DOWN && maze[x][y + i] == 0) {
                return true;
            }
            if (dir == UP && maze[x][y - i] == 0) {
                return true;
            }
        }
        return false;
    }
    /**
     * check if there is a wall when npc moves hortizontally
     * @param x
     * @param y
     * @param player
     * @param maze
     * @param dir
     * @return true or false
     * @author WangLin Song
     */
    private boolean checkWallHorizontal(int x, int y, Player player, int[][] maze, int dir) {
        //check if there is wall when npc moves horizontally
        for (int i = 0; i < Math.abs(player.getX() - x); i++) {
            if (dir == RIGHT && maze[x + i][y] == 0) {
                return true;
            }
            if (dir == LEFT && maze[x - i][y] == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * check if npc can attack the player
     * @param x
     * @param y
     * @param player
     * @return true or false
     * @author WangLin Song
     */
    private boolean checkAttack(int x, int y, Player player) {
        //check if npc can attack the player
        if (player.getX() == x && player.getY() == y) {
            return true;
        }
        return false;
    }

    /**
     * check if the next move of npc is valid
     * @param x
     * @param y
     * @param maze
     * @return true or false
     * @author WangLin Song
     */
    private boolean isValidMove(int x, int y, int[][] maze) {
        //check if the next move of npc is valid
        if (x < 0 || x >= maze[0].length || y < 0 || y >= maze.length) {
            return false;
        }
        return maze[y][x] == 0; // 0 represents an open path
    }

    /**
     * check if the next move of npc is not block by another npc
     * @param x
     * @param y
     * @param maze
     * @param npcList
     * @return true or false
     * @author WangLin Song
     */
    private boolean isValidMove(int x, int y, int[][] maze, List<NPC> npcList) {
        //check if the next move of npc is not block by another npc
        if (x < 0 || x >= maze[0].length || y < 0 || y >= maze.length) {
            return false;
        }
        boolean hasOtherNpc = false;
        for (int i = 0; i < npcList.size(); i++) {
            NPC npc = npcList.get(i);
            if (npc.getX() == x && npc.getY() == y) {
                hasOtherNpc = true;
                break;
            }
        }
        return !hasOtherNpc && maze[y][x] == 0;
    }
    //get functions for parameters of npc
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }


    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public int getAttack() {
        return attack;
    }

    public String getName() {
        return name;
    }


    public int getDirection() {
        return direction;
    }

    public int getLevel() {
        return level;
    }
}
