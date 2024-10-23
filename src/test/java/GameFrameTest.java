import comp2120.a3.*;
import comp2120.a3.gui.GameFrame;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.event.KeyEvent;

/**
 * unit test for GameFrame
 */
public class GameFrameTest {

    @Before
    public void setUp(){
        System.setProperty("java.awt.headless","false");
        System.setProperty("DISPLAY",":0.0");
    }

    @Test
    //check if the maze can be intialized
    public void testInit() {
        DataUtil.loadData();
        MazeMap currentMaze = DataUtil.getLevel(0);
        ItemManager itemManager = new ItemManager(currentMaze.getMap());
        Item[][] itemsInMaze = itemManager.getItemsInMaze();
        GameFrame gameFrame = new GameFrame(itemsInMaze);
    }

    @Test
    //test if the dialog can appear correctly
    public void testShowDialog() {
        DataUtil.loadData();
        MazeMap currentMaze = DataUtil.getLevel(0);
        ItemManager itemManager = new ItemManager(currentMaze.getMap());
        Item[][] itemsInMaze = itemManager.getItemsInMaze();
        GameFrame gameFrame = new GameFrame(itemsInMaze);
        gameFrame.showAttackDialog(0, new NPC(20,1, 0));
    }

    @Test
    //test if the up keyboard button pressed can be correctly handled
    public void testHandleKeyPressUp() {
        DataUtil.loadData();
        MazeMap currentMaze = DataUtil.getLevel(0);
        ItemManager itemManager = new ItemManager(currentMaze.getMap());
        Item[][] itemsInMaze = itemManager.getItemsInMaze();
        GameFrame gameFrame = new GameFrame(itemsInMaze);
        KeyEvent keyEvent = new KeyEvent(gameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), KeyEvent.VK_UP, KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED);
        gameFrame.handleKeyPress(keyEvent);
    }

    @Test
    //test if the down keyboard button pressed can be correctly handled
    public void testHandleKeyPressDown() {
        DataUtil.loadData();
        MazeMap currentMaze = DataUtil.getLevel(0);
        ItemManager itemManager = new ItemManager(currentMaze.getMap());
        Item[][] itemsInMaze = itemManager.getItemsInMaze();
        GameFrame gameFrame = new GameFrame(itemsInMaze);
        KeyEvent keyEvent = new KeyEvent(gameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), KeyEvent.VK_DOWN, KeyEvent.VK_DOWN, KeyEvent.CHAR_UNDEFINED);
        gameFrame.handleKeyPress(keyEvent);
    }

    @Test
    //test if the left keyboard button pressed can be correctly handled
    public void testHandleKeyPressLeft() {
        DataUtil.loadData();
        MazeMap currentMaze = DataUtil.getLevel(0);
        ItemManager itemManager = new ItemManager(currentMaze.getMap());
        Item[][] itemsInMaze = itemManager.getItemsInMaze();
        GameFrame gameFrame = new GameFrame(itemsInMaze);
        KeyEvent keyEvent = new KeyEvent(gameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), KeyEvent.VK_LEFT, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);
        gameFrame.handleKeyPress(keyEvent);
    }

    @Test
    //test right the up keyboard button pressed can be correctly handled
    public void testHandleKeyPressRight() {
        DataUtil.loadData();
        MazeMap currentMaze = DataUtil.getLevel(0);
        ItemManager itemManager = new ItemManager(currentMaze.getMap());
        Item[][] itemsInMaze = itemManager.getItemsInMaze();
        GameFrame gameFrame = new GameFrame(itemsInMaze);
        KeyEvent keyEvent = new KeyEvent(gameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), KeyEvent.VK_RIGHT, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
        gameFrame.handleKeyPress(keyEvent);
    }

    @Test
    //test if level can be switched correctly
    public void testSwitchToNextLevel() {
        DataUtil.loadData();
        MazeMap currentMaze = DataUtil.getLevel(0);
        ItemManager itemManager = new ItemManager(currentMaze.getMap());
        Item[][] itemsInMaze = itemManager.getItemsInMaze();
        GameFrame gameFrame = new GameFrame(itemsInMaze);
        gameFrame.switchToNextLevel();
        Assert.assertEquals(1, gameFrame.getCurrentLevelIndex());
    }
}
