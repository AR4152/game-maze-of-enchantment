package comp2120.a3;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.util.List;


/**desprition: save game and load game
 *
 *
 * author: Yuecheng Hao
 */
public class SaveAndLoader {

    HashMap<Integer,String> map;
    HashMap<String,Item> itemMap;
    public SaveAndLoader() {
        map = new HashMap<>();
        map.put(1,"src/main/java/comp2120/a3/save1.xml");
        map.put(2,"src/main/java/comp2120/a3/save2.xml");
        map.put(3,"src/main/java/comp2120/a3/save3.xml");

        itemMap = new HashMap<>();
        Item silverSword = new Item("Silver Sword","A sharp blade made of enchanted silver.",150.0,0.0,0.0,20.0);
        itemMap.put("Silver Sword",silverSword);
        Item bow = new Item("Bow","A long-range weapon favored by archers.",120.0,0.0,0.0,15.0);
        itemMap.put("Bow",bow);
        Item healthPortion = new Item("Health Elixir","Restores a portion of your health.",50.0,50.0,0.0,0.0);
        itemMap.put("Health Elixir",healthPortion);
        Item manaPortion = new Item("Mana Potion","Restores a portion of your mana.",40.0,0.0,0.0,0.0);
        itemMap.put("Mana Potion",manaPortion);
        Item steelArmor = new Item("Steel Plate Armor","Heavy armor that offers great protection.",200.0,0.0,50.0,0.0);
        itemMap.put("Steel Plate Armor",steelArmor);
        Item leatherArmor = new Item("Leather Vest","Light armor that allows for quick movement.",100.0,0.0,20.0,0.0);
        itemMap.put("Leather Vest",leatherArmor);
        Item magicRing = new Item("Ring of Fortitude","A magical ring that enhances the wearer's vitality.",250.0,5.0,30.0,10.0);
        itemMap.put("Ring of Fortitude",magicRing);
        Item mystical = new Item("Amulet of Shadows","Allows the wearer to become invisible for a short time.",300.0,0.0,0.0,0.0);
        itemMap.put("Amulet of Shadows",mystical);
    }

    /**
     * descprition: save the game
     *
     *
     *
     * author: Yuecheng Hao
     * */
    public void save(Integer number,GameState gameState){

        String fileName = map.get(number);

        try {
            //
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            //
            Document doc = docBuilder.newDocument();

            // root <game>
            Element gameElement = doc.createElement("game");
            doc.appendChild(gameElement);



            //  <players>
            Element playersElement = doc.createElement("players");
            gameElement.appendChild(playersElement);


            Element playerElement = doc.createElement("player");
            playersElement.appendChild(playerElement);


            Element indexElement = doc.createElement("index");
            indexElement.setTextContent(String.valueOf(gameState.getLevel()));
            playerElement.appendChild(indexElement);


            // maxHP
            Element maxHPElement = doc.createElement("MaxHp");
            maxHPElement.setTextContent(String.valueOf(gameState.getPlayer().getMaxHP()));
            playerElement.appendChild(maxHPElement);

            //blood
            Element bloodElement = doc.createElement("blood");
            bloodElement.setTextContent(String.valueOf(gameState.getPlayer().getBlood()));
            playerElement.appendChild(bloodElement);

            // attack
            Element attackElement = doc.createElement("attack");
            attackElement.appendChild(doc.createTextNode(String.valueOf(gameState.getPlayer().getAttack())));
            playerElement.appendChild(attackElement);

            // money
            Element moneyElement = doc.createElement("money");
            moneyElement.setTextContent(String.valueOf(gameState.getPlayer().getMoney()));
            playerElement.appendChild(moneyElement);

            // x element
            Element xElement = doc.createElement("x");
            xElement.setTextContent(String.valueOf(gameState.getPlayer().getX()));
            playerElement.appendChild(xElement);

            // y element
            Element yElement = doc.createElement("y");
            yElement.setTextContent(String.valueOf(gameState.getPlayer().getY()));
            playerElement.appendChild(yElement);

            //inventory
            Element inventoryElement = doc.createElement("inventory");
            Inventory inventory = gameState.getPlayer().getInventory();
            Item[] slots = inventory.getSlots();
            for (int i=0;i<slots.length;i++) {


                if(slots[i] == null) {continue;}
                Element nameElement = doc.createElement("name");
                nameElement.setTextContent(slots[i].getName());


                inventoryElement.appendChild(nameElement);
            }
            playerElement.appendChild(inventoryElement);



            //npc list
            Element npcsElement = doc.createElement("npcs");

            List<NPC> npcList = gameState.getNpcList();
            for(NPC npc : npcList){
                Element npcElement = doc.createElement("npc");
                npcsElement.appendChild(npcElement);

                Element XElement = doc.createElement("x");
                XElement.setTextContent(String.valueOf(npc.getX()));
                npcElement.appendChild(XElement);

                Element YElement = doc.createElement("y");
                YElement.setTextContent(String.valueOf(npc.getY()));
                npcElement.appendChild(YElement);

                Element levelElement = doc.createElement("level");
                levelElement.setTextContent(String.valueOf(npc.getLevel()));
                npcElement.appendChild(levelElement);

                Element directionElement = doc.createElement("direction");
                directionElement.setTextContent(String.valueOf(npc.getDirection()));
                npcElement.appendChild(directionElement);


                npcsElement.appendChild(npcElement);
            }
            gameElement.appendChild(npcsElement);



            // 创建一个TransformerFactory
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            // 创建一个DOMSource对象，将DOM文档传递给Transformer
            DOMSource source = new DOMSource(doc);

            // 创建一个StreamResult对象，指定要保存的XML文件路径
            StreamResult result = new StreamResult(new File(fileName));

            // 使用Transformer将DOM文档保存为XML文件
            transformer.transform(source, result);



            System.out.println("already saved");
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }


    /**
     * descprition: load the game
     *
     *
     *
     * author: Yuecheng Hao
     * */
    public GameState load(Integer number) {

        GameState gameState = new GameState();
        String filename = map.get(number);

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();


            Document document = builder.parse(filename);


            Element gameElement = (Element) document.getElementsByTagName("game").item(0);


            Element playerElement = (Element) document.getElementsByTagName("player").item(0);
            int index = Integer.parseInt(playerElement.getElementsByTagName("index").item(0).getTextContent());
            gameState.setLevel(index);
            Double maxHP = Double.parseDouble(playerElement.getElementsByTagName("MaxHp").item(0).getTextContent());
            Double blood = Double.parseDouble(playerElement.getElementsByTagName("blood").item(0).getTextContent());
            Double attack = Double.parseDouble(playerElement.getElementsByTagName("attack").item(0).getTextContent());
            Double money = Double.parseDouble(playerElement.getElementsByTagName("money").item(0).getTextContent());
            Integer x = Integer.parseInt(playerElement.getElementsByTagName("x").item(0).getTextContent());
            int y = Integer.parseInt(playerElement.getElementsByTagName("y").item(0).getTextContent());

            //recover inventory
            Element inventoryElement = (Element) playerElement.getElementsByTagName("inventory").item(0);
            NodeList nameNodes = inventoryElement.getElementsByTagName("name");
            Item[] slots = new Item[10];
            for(int i=0;i<nameNodes.getLength();i++){
                Element nameElement = (Element) nameNodes.item(i);
                String itemName = nameElement.getTextContent();

                slots[i] = itemMap.get(itemName);
            }
            Inventory inventory = new Inventory(slots);

            //recover player
            Player player = new Player(maxHP,attack,money,x,y,inventory,blood);
            gameState.setPlayer(player);


            List<NPC> npcList = new ArrayList<>();
            Element enemiesElement = (Element) document.getElementsByTagName("npcs").item(0);
            NodeList npcNodes = enemiesElement.getElementsByTagName("npc");
            for(int i = 0;i<npcNodes.getLength();i++){
                Element npcElement = (Element) npcNodes.item(i);
                int npcX = Integer.parseInt(npcElement.getElementsByTagName("x").item(0).getTextContent());
                int npcY = Integer.parseInt(npcElement.getElementsByTagName("y").item(0).getTextContent());
                int npcLevel = Integer.parseInt(npcElement.getElementsByTagName("level").item(0).getTextContent());
                int direction = Integer.parseInt(npcElement.getElementsByTagName("direction").item(0).getTextContent());
                //int enemyBlood = Integer.parseInt(npcElement.getElementsByTagName("blood").item(0).getTextContent());
                //String enemyName = npcElement.getElementsByTagName("name").item(0).getTextContent();
                NPC npc = new NPC(npcX,npcY,npcLevel,direction);
                npcList.add(npc);
            }
            gameState.setNpcList(npcList);


        } catch (Exception e) {
            e.printStackTrace();
        }


        return gameState;
    }

    public static void main(String[] args) {
        int level = 0;
        GameState gameState = new GameState();
        SaveAndLoader saveAndLoader = new SaveAndLoader();
        List<NPC> enemylist = new ArrayList<>();
        NPC npc1 = new NPC(1,2,1,1);
        NPC npc2 = new NPC(3,4,1,2);
        enemylist.add(npc1);
        enemylist.add(npc2);
        Item item1 = new Item("Amulet of Shadows","Allows the wearer to become invisible for a short time.",300.0,0.0,0.0,0.0);
        Item item2 = new Item("Bow","A long-range weapon favored by archers.",120.0,0.0,0.0,15.0);
        Item[] slot = new Item[2];
        slot[0] = item1;
        slot[1] = item2;
        Inventory inventory = new Inventory(slot);
        Player player = new Player(2001.0,222.0,100.0,4,5,inventory,2200.0);
        gameState.setPlayer(player);
        gameState.setNpcList(enemylist);
        gameState.setLevel(level);
        saveAndLoader.save(1,gameState);

        GameState gameState1 = saveAndLoader.load(1);
        System.out.println(gameState1.getLevel());


    }
}
