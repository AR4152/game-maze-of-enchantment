package comp2120.a3.gui;

import comp2120.a3.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GameFrame extends JFrame implements KeyListener {

    private  int level;
    private Item[][] itemsInMaze;
    private ItemManager itemManager;

    private Player player;
    private List<NPC> npcList;
    private InventoryGUI inventoryGUI;
    private JPanel mazePanel;
    private Timer timer;

    private ImageIcon wallIcon = new ImageIcon(readImageByte("/Images/maze_images/walls.png"));
    private ImageIcon pathIcon = new ImageIcon(readImageByte("/Images/maze_images/path_vert.png"));
    private ImageIcon playerIcon = new ImageIcon(readImageByte("/Images/maze_images/player.png"));
    private ImageIcon attackIcon = new ImageIcon(readImageByte("/Images/maze_images/attack.gif"));
    private ImageIcon level1NpcIcon = new ImageIcon(readImageByte("/Images/maze_images/npc1.png"));
    private ImageIcon level2NpcIcon = new ImageIcon(readImageByte("/Images/maze_images/npc2.png"));
    private ImageIcon level3NpcIcon = new ImageIcon(readImageByte("/Images/maze_images/npc3.png"));
    private int currentLevelIndex = 0;
    private ImageIcon currentNpcIcon = level1NpcIcon;
    private int[][] mazeData;

    public GameFrame(Item[][] itemsInMaze) {

        setPlayer(new Player(1000.0, 1000.0, 200.0, 0.0, 1, 20, 1));

        mazeData = DataUtil.getLevel(currentLevelIndex).getMap();


        if (itemsInMaze == null) {
            this.itemsInMaze = new Item[mazeData.length][mazeData[0].length];

        } else {
            this.itemsInMaze = itemsInMaze;
        }
        createNpcs();
        initTime();

        setTitle("Maze Game");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });

        setFocusable(true);
        setResizable(false);
        requestFocusInWindow();

        setLayout(new BorderLayout());

        JButton inventoryButton = new JButton("Inventory");
        inventoryButton.setPreferredSize(new Dimension(100, 50));
        inventoryButton.addActionListener(e -> {
            inventoryGUI.setVisible(true);
        });

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(inventoryButton, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.NORTH);


        mazePanel = new JPanel();
        add(mazePanel, BorderLayout.CENTER);

        initUI();
    }



    public GameFrame(Player player, List<NPC> NpcList, int level) {
        this.level = level;
        this.player = player;
        this.npcList = NpcList;
        //mazeData = DataUtil.getLevel(level).getMap();
        if (!DataUtil.loadData()) {
            System.out.println("Failed to load maze data.");
            return;
        }
        mazeData = DataUtil.getLevel(0).getMap();

        setItemsInMaze();



        setTitle("Maze Game");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame


        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });

        setFocusable(true);
        setResizable(false);
        requestFocusInWindow();

        setLayout(new BorderLayout());

        JButton inventoryButton = new JButton("Inventory");
        inventoryButton.setPreferredSize(new Dimension(100, 50));
        inventoryButton.addActionListener(e -> {
            inventoryGUI.setVisible(true);
        });

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(inventoryButton, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.NORTH);

        mazePanel = new JPanel();
        add(mazePanel, BorderLayout.CENTER);

        initUI();

    }

    /**
     * create all npcs
     * @author WangLin Song
     */
    private void createNpcs() {
        //create npc in the map
        npcList = new ArrayList<>();
        for (int i = 0; i < DataUtil.MAX_COL; i++) {
            for (int j = 0; j < DataUtil.MAX_ROW; j++) {
                if (mazeData[i][j] == 3) {
                    NPC npc = new NPC(i, j, currentLevelIndex);
                    npc.checkMoveDirection(mazeData);
                    npcList.add(npc);
                    mazeData[i][j] = 0;
                }
            }
        }
    }

    /**
     * initialization timer
     * @author WangLin Song
     */
    public void initTime() {
        //initial timer in the game
        ActionListener keepTime = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //all the npc action performed is included in this, including move,chase,attack
                //also includes pop up windows for attack
                for (int i = 0; i < npcList.size(); i++) {
                    NPC npc = npcList.get(i);
                    int status = npc.move(mazeData, itemsInMaze, npcList, player);
                    if (status == NPC.NPC_STATUS_ATTACK) {
                        timer.stop();
                        int result = JOptionPane.showConfirmDialog(null, npc.getName() + " wants to fight", "NPC Talk", JOptionPane.DEFAULT_OPTION);
                        if (result == JOptionPane.OK_OPTION) {
                            showAttackDialog(i, npc);
                        }
                    }
                }
                repaintMaze();
            }
        };
        timer = new Timer(1000, keepTime);
        timer.start();
    }

    /**
     * Maps key presses to game actions and handles player interactions.
     * @param e
     * @author Oscar Wei
     */
    public void handleKeyPress(KeyEvent e) {
        //handles the key pressed by player correspond the player character action in the game
        int key = e.getKeyCode();
        ActionType action = null;
        //use up/w in keyboard to move up, down/s in keyboard to move down
        //use left/a in keyboard to move left, right/d in keyboard to move right
        switch (key) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                action = ActionType.FORWARD;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                action = ActionType.BACKWARD;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                action = ActionType.LEFT;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                action = ActionType.RIGHT;
                break;
            case KeyEvent.VK_1:
                saveGame(1);
                break;
            case KeyEvent.VK_2:
                saveGame(2);
                break;
            case KeyEvent.VK_3:
                saveGame(3);
                break;
        }


        if (action != null) {
            //includes all the player actions
            int attackIndex = player.move(action, mazeData, itemsInMaze, npcList);
            if (attackIndex != -1) {
                //if the player pressed the corresponding direction button of the enemy position towards player
                //and the enemy is in the attack range, a pop-up window will show up to confirm if the player
                //wants to attack. if the player pressed ok then battle will start.
                //Otherwise, check the distance from player to npc, if there is one block between them then move
                //1 block to enemy. If the enemy is next to the player then do nothing.
                timer.stop();
                int result = JOptionPane.showConfirmDialog(null, "Do you want to attack " + npcList.get(attackIndex).getName(), "Player Talk", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    showAttackDialog(attackIndex, npcList.get(attackIndex));
                } else {
                    timer.start();
                    player.restorePosition();
                    repaintMaze();
                }
                return;
            }
            if (player.getX() == 1 && player.getY() == 20) {
                switchToNextLevel();
            } else {
                repaintMaze();
            }
        }
    }

    /**
     * Saves current game state to a selected slot
     * @param selectSaveSlot
     * @author Oscar Wei
     */
    private void saveGame(int selectSaveSlot) {
        SaveAndLoader sal = new SaveAndLoader();
        GameState currentState = getCurrentGameState();
        sal.save(selectSaveSlot, currentState);  // Saving to slot 1 for simplicity. You can choose other slots or even provide an UI to let user choose

        JOptionPane.showMessageDialog(this, "Game has been saved successfully!");
    }

    private GameState getCurrentGameState() {
        return new GameState(this.player, this.npcList);
    }


    /**
     * show the attack dialog after battle start
     * @param index
     * @param npc
     * @author WangLin Song
     */
    public void showAttackDialog(int index, NPC npc) {
        //attack dialogue shows to the player, includes player hp and enemy hp
        //player and enemy will automatically attack when timer is up(1 second)
        //if the enemy hp becomes 0,then it will disappear and player will move to enemy's position
        //otherwise, pop-up windows will show game over and if the player wants to leave the game
        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(null);
        JLabel attackLabel = new JLabel(attackIcon);
        attackLabel.setBounds(0,0, 300, 230);
        mainPanel.add(attackLabel);

        JPanel dataPanel = new JPanel();
        dataPanel.setBackground(Color.ORANGE);
        dataPanel.setLayout(null);
        dataPanel.setBounds(300, 0, 240, 260 );

        JLabel playerBloodLabel = new JLabel();
        playerBloodLabel.setText("player's blood:" + player.getBlood());
        playerBloodLabel.setBounds(20, 80, 200, 20);
        dataPanel.add(playerBloodLabel);

        JLabel npcBloodLabel = new JLabel();
        npcBloodLabel.setText("npc blood:" + npc.getBlood());
        npcBloodLabel.setBounds(20, 120, 200,20);
        dataPanel.add(npcBloodLabel);
        mainPanel.add(dataPanel);

        JDialog dialog = new JDialog();
        dialog.setTitle("Attack Dialog");
        dialog.setSize(550, 260);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setModal(true);
        dialog.add(mainPanel);
        Timer dialogTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.setBlood(player.getBlood() - npc.getAttack());
                npc.setBlood(npc.getBlood() - Player.ATTACK_DAMAGE);
                playerBloodLabel.setText("player blood:" + player.getBlood());
                npcBloodLabel.setText("npc blood:" + npc.getBlood());
                dataPanel.revalidate();
                dataPanel.repaint();
                if (npc.getBlood() <= 0) {
                    if (index >= 0 && index < npcList.size()) {
                        npcList.remove(index);
                    }
                    ((Timer) e.getSource()).stop();
                    dialog.dispose();
                    timer.start();
                }
                if (player.getBlood() < 0) {
                    ((Timer) e.getSource()).stop();
                    dialog.dispose();
                    JOptionPane.showConfirmDialog(null, "game over! do you want to exit the system?", "Confirm Exit", JOptionPane.DEFAULT_OPTION);
                    System.exit(0);
                }
            }
        });
        dialogTimer.setRepeats(true);
        dialogTimer.start();
        dialog.setVisible(true);
    }

    /**
     * Removes maze contents, reinitializes UI, and refreshes maze display.
     * @author Oscar Wei
     */
    private void repaintMaze() {
        //getContentPane().removeAll();
        mazePanel.removeAll();
        initUI();
        mazePanel.revalidate();
        mazePanel.repaint();
    }


    /**
     * Initializes game UI, populating maze with player, NPCs, walls, items, and paths.
     * @author Oscar Wei
     */
    private void initUI() {

        //initialize ui for the game.Including image of player, npc,wall ,road and items
        mazePanel.setLayout(new GridLayout(mazeData.length, mazeData[0].length, 1, 1));

        for (int i = 0; i < mazeData.length; i++) {
            for (int j = 0; j < mazeData[0].length; j++) {
                //JPanel cell = new JPanel();
                JLabel cell = new JLabel();
                if (mazeData[i][j] == DataUtil.TYPE_WALL) {
                    cell.setIcon(wallIcon);
                } else if (itemsInMaze[i][j] != null) {
                    cell.setIcon(itemsInMaze[i][j].getIcon());


                } else if (player.getX() == j && player.getY() == i) {
                    cell.setIcon(playerIcon);
                } else if (checkNpc(j, i)) {
                    cell.setIcon(getNpcIcon());
                } else if (mazeData[i][j] == DataUtil.TYPE_PATH || mazeData[i][j] == DataUtil.TYPE_NPC) {
                    cell.setIcon(pathIcon);
                }
                mazePanel.add(cell);
            }
        }

        //add(panel);
    }

    /**
     * check NPC position
     * @param x
     * @param y
     * @return true or false
     * @author WangLin Song
     */
    private boolean checkNpc(int x, int y) {
        //check if the current position is npc
        for (int i = 0; i < npcList.size(); i++) {
            NPC npc = npcList.get(i);
            if (npc.getX() == x && npc.getY() == y) {
                return true;
            }
        }
        return false;
    }

    /**
     * Transitions player to the next game level, reinitializes UI, and checks level data.
     * @author Oscar Wei
     */
    public void switchToNextLevel() {
        //move to next level if the player beats the level
        System.out.println("Switch to next level!");
        currentLevelIndex++;
        System.out.println("Now you are in level: " + currentLevelIndex);
        if(DataUtil.getLevel(currentLevelIndex) != null) {
            mazeData = DataUtil.getLevel(currentLevelIndex).getMap();
            // itemsInMaze = DataUtil.getItemForLevel(currentLevelIndex);
            int startingX = 20;
            int startingY = 1;
            player.setX(startingX);
            player.setY(startingY);
            mazePanel.removeAll();
            mazePanel.revalidate();
            mazePanel.repaint();
            createNpcs();
            initUI();
        } else {

            System.err.println("Error: Level data is null for level " + currentLevelIndex);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) { }
    @Override
    public void keyPressed(KeyEvent e) { int key = e.getKeyCode(); }
    @Override
    public void keyReleased(KeyEvent e) { }


    /**
     * Assigns player, initializes its inventory, and links player with an inventory GUI.
     * @param samplePlayer
     * @author Oscar Wei
     */
    public void setPlayer(Player samplePlayer) {
        this.player = samplePlayer;
        samplePlayer.setInventory(new Inventory());
        inventoryGUI = new InventoryGUI(player, this);
        player.setInventoryGUI(inventoryGUI);
    }

    /**
     * Initializes item array based on maze dimensions.
     * @author Oscar Wei
     */
    public void setItemsInMaze(){
        this.itemsInMaze = new Item[mazeData.length][mazeData[0].length];
    }

    /**
     * get the icon of npc
     * @return ImageIcon
     * @author WangLin Song
     */
    public ImageIcon getNpcIcon() {
        //get the icon of the npc in the corresponding level
        switch (currentLevelIndex) {
            case 0 -> currentNpcIcon = level1NpcIcon;
            case 1 -> currentNpcIcon = level2NpcIcon;
            case 2 -> currentNpcIcon = level3NpcIcon;
        }
        return currentNpcIcon;
    }

    /**
     * get the index of current level
     * @return currentLevelIndex
     * @author WangLin Song
     */

    public int getCurrentLevelIndex() {
        return currentLevelIndex;
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
    public byte[] readImageByte(String path) {
        final byte[] buffer = new byte[256];
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            try (InputStream in = DataUtil.class.getResourceAsStream(path)) {
                int bytesRead;
                while ((bytesRead = in.read(buffer)) > 0)
                    out.write(buffer, 0, bytesRead);
            }
            return out.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Assigns a list of NPCs to the current game instance.
     * @param npcList
     */
    public void setNpcList(List<NPC> npcList) {
        this.npcList = npcList;
    }


}
