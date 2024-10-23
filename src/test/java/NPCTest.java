import comp2120.a3.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * unit test for enemy
 */
public class NPCTest {

    @Before
    public void setUp(){
    }

    @Test
    //check if data can be correct loaded
    public void testLoadData() {
        Assert.assertTrue(DataUtil.loadData());
    }

    @Test
    //check if test map is not null
    public void testMazeMapNotNull() {
        MazeMap currentMaze = DataUtil.getLevel(0);
        Assert.assertNotNull(currentMaze);
    }

    @Test
    //check if level 1 npc is correct(e.g. attack/name/blood)
    public void testLevel1Npc() {
        DataUtil.loadData();
        MazeMap currentMaze = DataUtil.getLevel(0);
        int[][] map = currentMaze.getMap();
        NPC npc = new NPC(20, 3, 0);
        npc.checkMoveDirection(map);
        Assert.assertEquals(NPC.LEVEL_1_ATTACK_DAMAGE, npc.getAttack());
        Assert.assertEquals(NPC.LEVEL_1_MAX_BLOOD, npc.getBlood());
        Assert.assertEquals(NPC.LEVEL_1_NAME, npc.getName());
    }

    @Test
    //check if level 2 npc is correct(e.g. attack/name/blood)
    public void testLevel2Npc() {
        DataUtil.loadData();
        MazeMap currentMaze = DataUtil.getLevel(1);
        int[][] map = currentMaze.getMap();
        NPC npc = new NPC(20, 3, 1);
        npc.checkMoveDirection(map);
        Assert.assertEquals(NPC.LEVEL_2_ATTACK_DAMAGE, npc.getAttack());
        Assert.assertEquals(NPC.LEVEL_2_MAX_BLOOD, npc.getBlood());
        Assert.assertEquals(NPC.LEVEL_2_NAME, npc.getName());
    }

    @Test
    //check if level 3 npc is correct(e.g. attack/name/blood)
    public void testLevel3Npc() {
        DataUtil.loadData();
        MazeMap currentMaze = DataUtil.getLevel(2);
        int[][] map = currentMaze.getMap();
        NPC npc = new NPC(20, 3, 2);
        npc.checkMoveDirection(map);
        Assert.assertEquals(NPC.LEVEL_3_ATTACK_DAMAGE, npc.getAttack());
        Assert.assertEquals(NPC.LEVEL_3_MAX_BLOOD, npc.getBlood());
        Assert.assertEquals(NPC.LEVEL_3_NAME, npc.getName());
    }

    @Test
    //test if npc's moving direction is correct
    public void testMoveDirection() {
        DataUtil.loadData();
        MazeMap currentMaze = DataUtil.getLevel(0);
        int[][] map = currentMaze.getMap();
        NPC npc = new NPC(20, 3, 0);
        npc.checkMoveDirection(map);
        Assert.assertEquals(NPC.UP, npc.getDirection());
        NPC npc1 = new NPC(20, 0, 0);
        npc1.checkMoveDirection(map);
        Assert.assertEquals(NPC.DOWN, npc1.getDirection());
        NPC npc2 = new NPC(18, 1, 0);
        npc2.checkMoveDirection(map);
        Assert.assertEquals(NPC.LEFT, npc2.getDirection());
        NPC npc3 = new NPC(17, 3, 0);
        npc3.checkMoveDirection(map);
        Assert.assertEquals(NPC.RIGHT, npc3.getDirection());
    }

    @Test
    //check if npc can correctly chase player up
    public void testChasingUp() {
        DataUtil.loadData();
        MazeMap currentMaze = DataUtil.getLevel(0);
        int[][] map = currentMaze.getMap();
        Player player =new Player(1000.0, 1000.0, 200.0, 0.0, 0, 20, 1);
        NPC npc = new NPC(20, 3, 0);
        npc.checkMoveDirection(map);
        npc.checkChasing(20, 3, player, map);
        Assert.assertEquals(NPC.UP, npc.getDirection());
    }

    @Test
    //check if npc can correctly chase player down
    public void testChasingDown() {
        DataUtil.loadData();
        MazeMap currentMaze = DataUtil.getLevel(0);
        int[][] map = currentMaze.getMap();
        Player player =new Player(1000.0, 1000.0, 200.0, 0.0, 0, 20, 3);
        NPC npc = new NPC(20, 1, 0);
        npc.checkMoveDirection(map);
        npc.checkChasing(20, 1, player, map);
        Assert.assertEquals(NPC.DOWN, npc.getDirection());
    }

    @Test
    //check if npc can correctly chase player left
    public void testChasingLeft() {
        DataUtil.loadData();
        MazeMap currentMaze = DataUtil.getLevel(0);
        int[][] map = currentMaze.getMap();
        Player player =new Player(1000.0, 1000.0, 200.0, 0.0, 0, 17, 3);
        NPC npc = new NPC(19, 3, 0);
        npc.checkMoveDirection(map);
        npc.checkChasing(19, 3, player, map);
        Assert.assertEquals(NPC.LEFT, npc.getDirection());
    }

    @Test
    //check if npc can correctly chase player right
    public void testChasingRight() {
        DataUtil.loadData();
        MazeMap currentMaze = DataUtil.getLevel(0);
        int[][] map = currentMaze.getMap();
        Player player =new Player(1000.0, 1000.0, 200.0, 0.0, 0, 20, 3);
        NPC npc = new NPC(18, 3, 0);
        npc.checkMoveDirection(map);
        npc.checkChasing(18, 3, player, map);
        Assert.assertEquals(NPC.RIGHT, npc.getDirection());
    }

    @Test
    //check if npc can move up
    public void testMoveUp() {
        DataUtil.loadData();
        MazeMap currentMaze = DataUtil.getLevel(0);
        int[][] map = currentMaze.getMap();
        Player player =new Player(1000.0, 1000.0, 200.0, 0.0, 0, 20, 1);
        NPC npc = new NPC(20, 3, 0);
        npc.checkMoveDirection(map);
        npc.move(map, new Item[map.length][map[0].length], new ArrayList<>(), player);
        Assert.assertEquals(NPC.UP, npc.getDirection());
        Assert.assertEquals(2, npc.getY());
    }

    @Test
    //check if npc can move down
    public void testMoveDown() {
        DataUtil.loadData();
        MazeMap currentMaze = DataUtil.getLevel(0);
        int[][] map = currentMaze.getMap();
        Player player =new Player(1000.0, 1000.0, 200.0, 0.0, 0, 20, 3);
        NPC npc = new NPC(20, 1, 0);
        npc.checkMoveDirection(map);
        npc.move(map, new Item[map.length][map[0].length], new ArrayList<>(), player);
        Assert.assertEquals(NPC.DOWN, npc.getDirection());
        Assert.assertEquals(2, npc.getY());
    }

    @Test
    //check if npc can move left
    public void testMoveLeft() {
        DataUtil.loadData();
        MazeMap currentMaze = DataUtil.getLevel(0);
        int[][] map = currentMaze.getMap();
        Player player =new Player(1000.0, 1000.0, 200.0, 0.0, 0, 17, 3);
        NPC npc = new NPC(19, 3, 0);
        npc.checkMoveDirection(map);
        npc.move(map, new Item[map.length][map[0].length], new ArrayList<>(), player);
        Assert.assertEquals(NPC.LEFT, npc.getDirection());
        Assert.assertEquals(18, npc.getX());
    }

    @Test
    //check if npc can move right
    public void testMoveRight() {
        DataUtil.loadData();
        MazeMap currentMaze = DataUtil.getLevel(0);
        int[][] map = currentMaze.getMap();
        Player player =new Player(1000.0, 1000.0, 200.0, 0.0, 0, 20, 3);
        NPC npc = new NPC(18, 3, 0);
        npc.checkMoveDirection(map);
        npc.move(map, new Item[map.length][map[0].length], new ArrayList<>(), player);
        Assert.assertEquals(NPC.RIGHT, npc.getDirection());
        Assert.assertEquals(19, npc.getX());
    }

    @Test
    //check if npc can attack the player
    public void testNpcAttackPlayer() {
        DataUtil.loadData();
        MazeMap currentMaze = DataUtil.getLevel(0);
        int[][] map = currentMaze.getMap();
        Player player =new Player(1000.0, 1000.0, 200.0, 0.0, 0, 20, 3);
        NPC npc = new NPC(20, 2, 0);
        npc.checkMoveDirection(map);
        int action = npc.move(map, new Item[map.length][map[0].length], new ArrayList<>(), player);
        Assert.assertEquals(NPC.NPC_STATUS_ATTACK, action);
    }
}
