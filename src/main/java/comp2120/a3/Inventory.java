package comp2120.a3;

import java.util.HashMap;
import comp2120.a3.*;
import java.util.ArrayList;
import java.util.List;


/**
 * descrpition:inventory system in the game
 *
 * @author: Yuecheng Hao, Oscar Wei
 */
public class Inventory {
    //private Object[] slots;
    //private List<Item> items;
    private Item[] slots;

    //current size
    private int size;
    private final int limitedSize = 10;

    public Inventory(){
        //slots = new Object[this.limitedSize];
        slots = new Item[this.limitedSize];
        size = 0;
    }

    public Inventory(Item[] slots) {
        this.slots = slots;
        size = slots.length;
    }

    private List<InventoryChangeListener> listeners = new ArrayList<>();

    public void addListener(InventoryChangeListener listener) {
        listeners.add(listener);
    }

    //successfully added return true,otherwise return false;
    public boolean add(Item item){
        boolean wasAdded = false;
        if(isFull()){
            return false;
        }
        for(int i = 0; i < limitedSize; i++){
            if(slots[i] == null){
                slots[i] = item;
                size++;
                return true;
            }
        }
        if (wasAdded) {
            notifyListeners();
        }
        return wasAdded;
    }

    public void delete(int index) {
        if (index >= 0 && index < limitedSize && slots[index] != null) {
            slots[index] = null;
            size--;
            notifyListeners();
        }
    }

    public boolean isFull() {
        return size == limitedSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Item getItem(int index){
        if (index >= 0 && index < limitedSize){
            return (Item)slots[index];
        }
     return null;
    }

    public void clear() {
        for (int i = 0; i < limitedSize; i++) {
            slots[i] = null;
        }
        size = 0;
    }

    public boolean contains(Item item) {
        for (int i = 0; i < limitedSize; i++) {
            if (slots[i] != null && slots[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    public int getFreeSlots() { //return how many slots are available
        return limitedSize - size;
    }

    public void printInventory() {
        for (int i = 0; i < limitedSize; i++) {
            if (slots[i] != null) {
                System.out.println("Slot " + i + ": " + slots[i].toString());
            } else {
                System.out.println("Slot " + i + ": Empty");
            }
        }
    }

    public Item[] getSlots() {
        return slots;
    }

    public void addChangeListener(InventoryChangeListener listener) {
        listeners.add(listener);
    }

    public void removeChangeListener(InventoryChangeListener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners() {
        for (InventoryChangeListener listener : listeners) {
            listener.onInventoryChanged();
        }
    }

    public void setSlots(Item[] slots) {
        this.slots = slots;
    }
}
