package comp2120.a3.gui;

import comp2120.a3.*;

import javax.swing.*;
        import java.awt.*;
import java.awt.event.ItemEvent;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class StartGamePage extends JFrame{
    private int selectedSaveNumber = 1;
    private SaveAndLoader saveAndLoader = new SaveAndLoader();


    /**
     * Creates a game start page UI with options to start, load, or exit, set against a background.
     * @author Oscar Wei
     */
    public StartGamePage() {
        setTitle("Game Start Page");
        setSize(300, 230);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon backgroundIcon = new ImageIcon(readImageByte("/Images/maze_images/attack.gif"));
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setLayout(new BoxLayout(backgroundLabel, BoxLayout.Y_AXIS));
        setContentPane(backgroundLabel);

        JButton btnStartNewGame = new JButton("Start New Game");
        btnStartNewGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnStartNewGame.addActionListener(e -> {
            try {
                startNewGame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton btnLoadGame = new JButton("Load Game");
        btnLoadGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLoadGame.addActionListener(e -> loadGame(selectedSaveNumber));

        String[] saveOptions = {"Save 1", "Save 2", "Save 3"};
        JComboBox<String> saveDropdown = new JComboBox<>(saveOptions);
        saveDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveDropdown.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {

                String selectedSave = (String) e.getItem();

                //int saveNumber;
                if ("Save 1".equals(selectedSave)) {
                    selectedSaveNumber = 1;
                } else if ("Save 2".equals(selectedSave)) {
                    selectedSaveNumber = 2;
                } else if ("Save 3".equals(selectedSave)) {
                    selectedSaveNumber = 3;
                } else {
                    selectedSaveNumber= 1;
                }
                loadGame(selectedSaveNumber);
            }
        });

        JButton btnExit = new JButton("Exit Game");
        btnExit.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnExit.addActionListener(e -> System.exit(0));

        backgroundLabel.add(Box.createVerticalGlue());
        backgroundLabel.add(btnStartNewGame);
        backgroundLabel.add(Box.createVerticalStrut(10));
        backgroundLabel.add(btnLoadGame);
        backgroundLabel.add(Box.createVerticalStrut(10));
        backgroundLabel.add(saveDropdown);
        backgroundLabel.add(Box.createVerticalStrut(10));
        backgroundLabel.add(btnExit);
        backgroundLabel.add(Box.createVerticalGlue());

        // Make components transparent to show the background
        btnStartNewGame.setContentAreaFilled(false);
        btnLoadGame.setContentAreaFilled(false);
        saveDropdown.setOpaque(false);
        btnExit.setContentAreaFilled(false);
    }

    /**
     * Initializes a new game and hides the start game page.
     * @author Oscar Wei
     */
    private void startNewGame() throws IOException {
        //resetGameData();
        // Initialize GameLauncher
        new GameLauncher().initializeGame();
        this.setVisible(false);
    }

//    public void saveGame() {
//        GameState currentGameState = saveAndLoader.save();
//    }

    /**
     * Loads saved game state from a specified slot and initializes the game; alerts if failed.
     * @param saveSlotNumber
     * @author Oscar Wei
     */
    public void loadGame(int saveSlotNumber) {
        //Integer saveSlotNumber;
        //GameFrame gameFrame = new GameFrame();

        GameState loadGameState = saveAndLoader.load(saveSlotNumber);
        if (loadGameState != null) {
            Player loadPlayer = loadGameState.getPlayer();
            List<NPC> loadNpcList = loadGameState.getNpcList();
            int LoadLevel = loadGameState.getLevel();

            GameFrame gameFrame = new GameFrame(loadPlayer, loadNpcList, LoadLevel);

            //gameFrame.setVisible(true);

            GameLauncher gameLauncher = new GameLauncher();
            gameLauncher.setGameFrame(gameFrame);

            try {
                gameLauncher.initializeGame();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Failed to load game from Save " + saveSlotNumber + ".", "Error", JOptionPane.ERROR_MESSAGE);
        }

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
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StartGamePage().setVisible(true);
        });
    }
}

