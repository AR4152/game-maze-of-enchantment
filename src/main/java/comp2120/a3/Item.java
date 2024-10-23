package comp2120.a3;

import javax.swing.*;
import java.util.Objects;

/**
 * descrpition:item in the game
 *
 * author: Yuecheng Hao, Oscar Wei
 */
public class Item {
    public boolean isRing;
    public boolean isAmulet;
    private String name;
    private String description;
    private Double value;
    private Double regeneration;
    private Double moreHealthEnhanced;
    private Double attackEnhanced;
    private Icon icon;

    public Item(String name, String description, Double value, Double regeneration, Double moreHealthEnhanced, Double attackEnhanced) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.regeneration = regeneration;
        this.moreHealthEnhanced = moreHealthEnhanced;
        this.attackEnhanced = attackEnhanced;
    }

//    public Item(String name, String description, Double value, Double regeneration, Double moreHealthEnhanced, Double attackEnhanced, Icon icon) {
//        this.name = name;
//        this.description = description;
//        this.value = value;
//        this.regeneration = regeneration;
//        this.moreHealthEnhanced = moreHealthEnhanced;
//        this.attackEnhanced = attackEnhanced;
//        this.icon = icon;
//    }

    public Item(String name, String description, Double value, Double regeneration, Double moreHealthEnhanced, Double attackEnhanced, Icon icon) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.regeneration = regeneration;
        this.moreHealthEnhanced = moreHealthEnhanced;
        this.attackEnhanced = attackEnhanced;
        this.icon = icon;
        switch(name) {
            case "Silver Sword":
                this.icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Images/new_image/sword.png")));
                break;
            case "Longbow":
                this.icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Images/new_image/bow.png")));
                break;
            case "Health Elixir":
                this.icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Images/new_image/health.png")));
                break;
            case "Mana Potion":
                this.icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Images/new_image/mana.png")));
                break;
            case "Steel Plate Armor":
                this.icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Images/new_image/steel.png")));
                break;
            case "Leather Vest":
                this.icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Images/new_image/leather.png")));
                break;
            case "Ring of Fortitude":
                this.icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Images/new_image/ring.png")));
                break;
            case "Amulet of Shadows":
                this.icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Images/new_image/amulet.png")));
                break;
            default:
                break;
        }

        System.out.println("Item name: " + name);
        System.out.println("Icon URL: " + Item.class.getResource("/Images/new_image/sword.png"));
        System.out.println("Icon: " + this.icon);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getRegeneration() {
        return regeneration;
    }

    public void setRegeneration(Double regeneration) {
        this.regeneration = regeneration;
    }

    public Double getMoreHealthEnhanced() {
        return moreHealthEnhanced;
    }

    public void setMoreHealthEnhanced(Double moreHealthEnhanced) {
        this.moreHealthEnhanced = moreHealthEnhanced;
    }

    public Double getAttackEnhanced() {
        return attackEnhanced;
    }

    public void setAttackEnhanced(Double attackEnhanced) {
        this.attackEnhanced = attackEnhanced;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", value=" + value +
                ", regeneration=" + regeneration +
                ", healthEnhancement=" + moreHealthEnhanced +
                ", attackEnhancement=" + attackEnhanced +
                '}';
    }


    public boolean isHealthPotion() {
        return "Health Elixir".equalsIgnoreCase(this.name);
    }

    public boolean isHealthEnhance() {
        return "Mana Potion".equalsIgnoreCase(this.name);
    }

    public boolean isBow() {
        return "Longbow".equalsIgnoreCase(this.name);
    }

    public boolean isSword() {
        return "Silver Sword".equalsIgnoreCase(this.name);
    }

    public boolean isSteelArmor() {
        return "Steel Plate Armor".equalsIgnoreCase(this.name);
    }

    public boolean isLeatherArmor() {
        return "Leather Vest".equalsIgnoreCase(this.name);
    }

    public boolean isRing() {
        return "Ring of fortitude".equalsIgnoreCase(this.name);
    }

    public boolean isAmulet() {
        return "Amulet of Shadows".equalsIgnoreCase(this.name);
    }


    // Add a getter for the icon
    public Icon getIcon() {
        return icon;
    }
}
