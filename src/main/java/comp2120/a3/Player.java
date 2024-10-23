package comp2120.a3;

import comp2120.a3.*;
import comp2120.a3.gui.*;

import java.util.List;


public class Player {
    public static final int ATTACK_DAMAGE = 50;
    public static final double MAX_BLOOD = 5000;
    //private final Double blood;
    private Double blood=2000d;
    private Double maxHP;
    private Double attack;
    private Double money;
    private Inventory inventory;
    private Integer gameLevel;
    private InventoryGUI inventoryGUI;
    private ItemManager itemManager;

    private static final int OPEN_PATH = 0;
    private static final int NPC = 3;

    //x,y on the map
    private Integer X;
    private Integer Y;
    private Integer lastX;
    private Integer lastY;

    /**
     * Constructor or player
     * @param blood
     * @param maxHP
     * @param attack
     * @param money
     * @param gameLevel
     * @param X
     * @param Y
     * @author Oscar Wei
     */

    public Player(Double blood, Double maxHP, Double attack, Double money, Integer gameLevel, Integer X, Integer Y) {
        //this.hitPoint = blood;
        this.maxHP = maxHP;
        this.attack = attack;
        this.money = money;
        //this.inventory = new Inventory();
        //this.inventoryGUI = new InventoryGUI(this, gameFrame);
        //System.out.println("Player constructor, inventoryGUI is: " + inventoryGUI);
        this.gameLevel = gameLevel;
        this.X = X;
        this.Y = Y;
        this.blood = blood;
    }

    public Player(Double maxHP,Double attack,Double money,Integer x,Integer y,Inventory inventory,Double blood){
        this.maxHP = maxHP;
        this.attack = attack;
        this.money = money;
        //this.inventory = new Inventory();
        //this.inventoryGUI = new InventoryGUI(this, gameFrame);
        //System.out.println("Player constructor, inventoryGUI is: " + inventoryGUI);
        this.inventory = inventory;
        this.X = x;
        this.Y = y;
        this.blood = blood;
    }

    public void setInventory(Inventory inventory) {this.inventory = inventory;}
    public void setInventoryGUI(InventoryGUI inventoryGUI) {
        this.inventoryGUI = inventoryGUI;
    }


    //win return true, otherwise false
    public boolean fight(Enemy enemy){
        Double enemyAttack = enemy.getAttack();
        Double enemyHP = enemy.getHitPoint();
        while(this.blood !=0 && enemyHP != 0){
            this.blood -= enemyAttack;
            enemyHP -= this.attack;
        }

        if(enemyHP > 0 || this.blood <= 0){
            return false;
        }

        enemy.setBeated(true);
        return true;
    }


    //true if it can be consumed.
    public boolean consume(Integer index){
        if(index<0 || index >=10){
            return false;
        }

        Item item = inventory.getItem(index);
        if(item==null){
            return false;
        }

        this.blood = this.blood + item.getRegeneration() > this.maxHP ? this.maxHP : this.blood + item.getRegeneration();
        this.maxHP += item.getMoreHealthEnhanced();
        this.attack += item.getAttackEnhanced();

        inventory.delete(index);
        return true;
    }

    public boolean storeItem(Item item){
        if(inventory.isFull()){
            return false;
        }
        inventory.add(item);
        return true;
    }

    public String getCurrentPosition() {
        return "Current Position: (" + X + ", " + Y + ")";
    }




    //Todo: movement, don't forget check the map boundary

    /**
     * The method handles player movement, interacts with NPCs,
     * collects items, checks move validity, and updates the inventory GUI.
     * @param action
     * @param maze
     * @param itemsInMaze
     * @param npcList
     * @return attack Index
     *
     * @author Oscar Wei, Wanglin Song
     */
    public int move(ActionType action, int[][] maze, Item[][] itemsInMaze, List<NPC> npcList) {
        //movement of player
        int attackIndex = -1;
        int nextX = X;
        int nextNextX = X;
        int nextY = Y;
        int nextNextY = Y;
        lastX = X;
        lastY = Y;
        switch (action) {
            case FORWARD:
                nextY -= 1;
                nextNextY -= 2;
                break;
            case BACKWARD:
                nextY += 1;
                nextNextY += 2;
                break;
            case LEFT:
                nextX -= 1;
                nextNextX -= 2;
                break;
            case RIGHT:
                nextX += 1;
                nextNextX += 2;
                break;
            case TALK:
                // Handle the TALK action if needed
                handleTalkAction();
                //System.out.println("Talking...");
                break;
        }
        //check if the player wants to attack npc
        attackIndex = checkAttack(nextX, nextY, npcList);
        if (attackIndex != -1) {
            X = nextX;
            Y = nextY;
        } else {
            //otherwise,check if the next movement is available(not blocked by walls)
            if (isValidMove(nextX, nextY, maze, npcList)) {
                // Check for items at the new position
                Item itemAtPosition = itemsInMaze[nextY][nextX];
                if (itemAtPosition != null) {
                    //Item item = new Item();//
                    inventory.add(itemAtPosition);
                    itemsInMaze[nextY][nextX] = null; // Remove the item from the maze
                    System.out.println("Collected: " + itemAtPosition.getName());
                }

                //update the position after moving
                attackIndex = checkAttack(nextNextX, nextNextY, npcList);
                if (attackIndex != -1) {
                    lastX = nextX;
                    lastY = nextY;
                    X = nextNextX;
                    Y = nextNextY;
                } else {
                    X = nextX;
                    Y = nextY;
                }
                System.out.println("Moved to position: (" + X + ", " + Y + ")");
//            System.out.println("Now player in " + DataUtil.levels.get(gameLevel));
//            System.out.println("Total " + DataUtil.levels.size() + " levels");
            } else {
                System.out.println("Invalid move. Hit a wall or boundary.");
            }
        }


        if(inventoryGUI != null) {
            inventoryGUI.refreshInventory();
        }
        //Print the player's new position
        System.out.println(this);
        return attackIndex;
    }

    /**
     * restore position
     * @author WangLin Song
     */
    public void restorePosition() {
        //restore position of player if the player pressed cancel when the attack window pop up
        X = lastX;
        Y = lastY;
    }

    /**
     * check attack
     * @param x
     * @param y
     * @param npcList
     * @return i
     * @author WangLin Song
     */
    private int checkAttack(int x, int y, List<NPC> npcList) {
        //check if the player wants to attack
        for (int i = 0; i < npcList.size(); i++) {
            NPC npc = npcList.get(i);
            if (npc.getX() == x && npc.getY() == y) {
                return i;
            }
        }
        return -1;
    }

    /**
     * check player's next move is valid or not
     * @param x
     * @param y
     * @param maze
     * @param npcList
     * @return true or false
     * @author WangLin Song
     */
    private boolean isValidMove(int x, int y, int[][] maze, List<NPC> npcList) {
        //if the player next move is valid
        return isInBounds(x, y, maze) && isPathOpen(x, y, maze) && checkAttack(x, y, npcList) == -1;
    }


    /**
     * check if the player movement is out of bound
     * @param x
     * @param y
     * @param maze
     * @return true or false
     * @author WangLin Song
     */
    private boolean isInBounds(int x, int y, int[][] maze) {
        //check if the movement is out of bound
        return x >= 0 && x < maze[0].length && y >= 0 && y < maze.length;
    }

    /**
     * check if the path is open
     * @param x
     * @param y
     * @param maze
     * @return true or false
     * @author WangLin Song
     */
    private boolean isPathOpen(int x, int y, int[][] maze) {
        //check if the path is open
        return maze[y][x] == OPEN_PATH;
    }


    private void handleTalkAction() {
        // Check for NPCs or interactive objects nearby
        NPC nearbyNPC = checkForNearbyNPCs();
        if (nearbyNPC != null) {
            // Trigger dialogue or event
            System.out.println(nearbyNPC.getDialogue());
        } else {
            System.out.println("There's no one nearby to talk to.");
        }
    }

    private NPC checkForNearbyNPCs() {
        return null;
    }

    //TODO: use items.....

    /**
     * Uses an item from the player's inventory based on the specified index.
     * Depending on the item's properties, it modifies the player's attributes such as health and attack.
     * After using the item, it updates the inventory GUI.
     *
     * @param index
     */
    public void useItem(int index) {
        Item item = inventory.getItem(index);

        if (item != null) {
            if (item.isHealthPotion()) {
                this.blood += item.getRegeneration();
            } else if (item.isHealthEnhance()) {
                this.maxHP += item.getMoreHealthEnhanced();
            } else if (item.isBow()) {
                this.attack += item.getAttackEnhanced();
            } else if (item.isSword()){
                this.attack += item.getAttackEnhanced();
            } else if (item.isSteelArmor()) {
                this.maxHP += item.getMoreHealthEnhanced();
            } else if (item.isLeatherArmor()) {
                this.maxHP += item.getMoreHealthEnhanced();
            } else if (item.isRing()) {
                this.blood += item.getRegeneration();
                this.maxHP += item.getMoreHealthEnhanced();
                this.attack += item.getAttackEnhanced();
            } else if (item.isAmulet()) {

            }
            inventoryGUI.refreshInventory();
        }
        System.out.println("Used it successfully!");
    }




    @Override
    public String toString() {
        return "Player{" +
                "X=" + X +
                ", Y=" + Y +
                ", blood=" + blood +
                ", maxHP=" + maxHP +
                ", attack=" + attack +
                ", money=" + money +
                ", gameLevel=" + gameLevel +
                '}';
    }



    public void setBlood(Double blood) {
        this.blood = blood;
    }

    public Double getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(Double maxHP) {
        this.maxHP = maxHP;
    }

    public Double getAttack() {
        return attack;
    }

    public void setAttack(Double attack) {
        this.attack = attack;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setGameLevel(Integer gameLevel) {
        this.gameLevel = gameLevel;
    }

    public Integer getX() {
        return X;
    }

    public Integer getY() {
        return Y;
    }


    public void setX(int startingX) {
        this.X = startingX;
    }

    public void setY(int startingY){
        this.Y = startingY;
    }

    public Double getBlood() {
        return blood;
    }

}
