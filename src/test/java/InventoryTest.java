import comp2120.a3.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class InventoryTest {

    Inventory inventory;

    @Before
    public void init(){

    }

    @Test
    public void testAdd(){
        inventory = new Inventory();
        Item[] items = new Item[10];
        Item item1 = new Item("Amulet of Shadows","Allows the wearer to become invisible for a short time.",300.0,0.0,0.0,0.0);


        inventory.setSlots(items);
        inventory.add(item1);
        Assert.assertEquals(item1.getName(),inventory.getItem(0).getName());

    }

    @Test
    public void testDelete(){
        inventory = new Inventory();
        Item item1 = new Item("Amulet of Shadows","Allows the wearer to become invisible for a short time.",300.0,0.0,0.0,0.0);
        inventory.add(item1);
        inventory.delete(0);
        Assert.assertEquals(null,inventory.getItem(0));
    }

    @Test
    public void testGetSize(){
        inventory = new Inventory();

        Item item1 = new Item("Amulet of Shadows","Allows the wearer to become invisible for a short time.",300.0,0.0,0.0,0.0);
        Item item2 = new Item("Bow","A long-range weapon favored by archers.",120.0,0.0,0.0,15.0);
        inventory.add(item1);
        inventory.add(item2);
        Assert.assertEquals(2,inventory.getSize());
    }
}
