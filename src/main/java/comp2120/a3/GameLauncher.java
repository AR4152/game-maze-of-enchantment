package comp2120.a3;

import comp2120.a3.gui.GameFrame;
import comp2120.a3.gui.InventoryGUI;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;

public class GameLauncher {


    private GameFrame gameFrame;

    public void GameLauncher() {

    }

    /**
     * initialize game data, including item data, game frame and player
     * @throws IOException
     * @author Oscar Wei
     */
    public void initializeGame() throws IOException {

        if (!DataUtil.loadData()) {
            System.out.println("Failed to load maze data.");
            return;
        }

        // Initialize the game frame with the first level
        MazeMap currentMaze = DataUtil.getLevel(0);
        if (currentMaze == null) {
            System.out.println("Failed to load the initial maze.");
            return;
        }

        //Initialize items and player
        // Item[][] itemsInMaze = new Item[22][22];

        ItemManager itemManager = new ItemManager(currentMaze.getMap());

        Icon swordIcon = new ImageIcon(readImageByte("/Images/new_image/sword.png"));
        Item sword = new Item("Silver Sword", "A sharp blade made of enchanted silver.", 150.0, 0.0, 0.0, 20.0,swordIcon);
        Icon bowIcon = new ImageIcon(readImageByte("/Images/new_image/bow.png"));
        Item bow = new Item("Longbow", "A long-range weapon favored by archers.", 120.0, 0.0, 0.0, 15.0,bowIcon);

// Potions
        Icon redPotIcon = new ImageIcon(readImageByte("/Images/new_image/health.png"));
        Item redPotion = new Item("Health Elixir", "Restores a portion of your health.", 50.0, 250.0, 0.0, 0.0,redPotIcon);
        Icon bluePotIcon = new ImageIcon(readImageByte("/Images/new_image/mana.png"));
        Item bluePotion = new Item("Mana Potion", "Restores a portion of your mana.", 40.0, 0.0, 0.0, 0.0, bluePotIcon);

// Armors
        Icon armorIcon = new ImageIcon(readImageByte("/Images/new_image/steel.png"));
        Item armor = new Item("Steel Plate Armor", "Heavy armor that offers great protection.", 200.0, 0.0, 50.0, 0.0, armorIcon);
        Icon vestIcon = new ImageIcon(readImageByte("/Images/new_image/leather.png"));
        Item vest = new Item("Leather Vest", "Light armor that allows for quick movement.", 100.0, 0.0, 20.0, 0.0, vestIcon);

// Special Items
        Icon ringIcon = new ImageIcon(readImageByte("/Images/new_image/ring.png"));
        Item ring = new Item("Ring of Fortitude", "A magical ring that enhances the wearer's vitality.", 250.0, 5.0, 30.0, 10.0,ringIcon);
        Icon amuletIcon = new ImageIcon(readImageByte("/Images/new_image/amulet.png"));
        Item amulet = new Item("Amulet of Shadows", "Allows the wearer to become invisible for a short time.", 300.0, 0.0, 0.0, 0.0, amuletIcon);

        itemManager.addItems(sword);
        itemManager.addItems(bow);
        itemManager.addItems(redPotion);
        itemManager.addItems(bluePotion);
        itemManager.addItems(armor);
        itemManager.addItems(vest);
        itemManager.addItems(ring);
        itemManager.addItems(amulet);


        itemManager.placeRandomItems();

        Item[][] itemsInMaze = itemManager.getItemsInMaze();

        GameFrame gameFrame = new GameFrame(itemsInMaze);

        Player samplePlayer = new Player(1000.0, 1000.0, 20.0, 0.0, 1, 20, 1);
        samplePlayer.setInventory(new Inventory());
        InventoryGUI gui = new InventoryGUI(samplePlayer, gameFrame);
        samplePlayer.setInventoryGUI(gui);

        gameFrame.setPlayer(samplePlayer);

        // Create the game frame with the maze, items, and player
        // gameFrame = new GameFrame(itemsInMaze, samplePlayer);
        gameFrame.setVisible(true);
    }

    /**
     * Extract Bytes of Images
     *
     * @param path path of image relative to resources folder
     * @return byte array of image
     * @throws IOException when file not found
     *
     * @author Arjun Raj
     */
    public byte[] readImageByte(String path) throws IOException {
        final byte[] buffer = new byte[256];
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            try (InputStream in = DataUtil.class.getResourceAsStream(path)) {
                int bytesRead;
                while ((bytesRead = in.read(buffer)) > 0)
                    out.write(buffer, 0, bytesRead);
            }
            return out.toByteArray();
        }
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            try {
                new GameLauncher().initializeGame();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public GameFrame getGameFrame() {
        return gameFrame;
    }

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }
}
