package comp2120.a3;

import java.util.ArrayList;
import java.util.List;

/**
 * description: store the dynamic element in the game
 *
 * author:Yuecheng Hao
 */
public class GameState {
    private Player player;
    private List<NPC> npcList;

    int index;


    public GameState() {

    }

    public GameState(Player player, List<NPC> npcList) {
        this.player = player;
        this.npcList = npcList;
    }

    public GameState(Player player, List<NPC> npcList, int level) {
        this.player = player;
        this.npcList = npcList;
        this.index = level;
    }

    public GameState(int index) {
        this.index = index;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<NPC> getNpcList() {
        return npcList;
    }

    public void setNpcList(List<NPC> npcList) {
        this.npcList = npcList;
    }

    // Add an NPC to the list
    public void addNPC(NPC npc) {
        npcList.add(npc);
    }

    // Remove an NPC from the list
    public void removeNPC(NPC npc) {
        npcList.remove(npc);
    }

    public int getLevel() {
        return index;
    }

    public void setLevel(int level) {
        this.index = level;
    }

    @Override
    public String toString() {
        return "GameState [player=" + player + ", npcList=" + npcList + "]";
    }
}
