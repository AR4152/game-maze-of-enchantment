package comp2120.a3.gui;

import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import comp2120.a3.*;


public class InventoryGUI extends JDialog {
    private final GameFrame gameFrame;
    private JList<Item> itemList;
    private Player player;
    private DefaultListModel<Item> listModel;


    /**
     * Initializes an inventory GUI, displays player's items, and allows item removal.
     * @param player
     * @param gameFrame
     * @author Oscar Wei
     */
    public InventoryGUI(Player player, GameFrame gameFrame) {
        //super(owner, "Inventory Management", false);  // false indicates non-modal dialog
        this.player = player;
        this.gameFrame = gameFrame;

        setSize(1000, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        updateItemList();

        itemList = new JList<>(listModel);
        itemList.setCellRenderer(new ItemCellRenderer());

        JScrollPane scrollPane = new JScrollPane(itemList);
        scrollPane.setPreferredSize(new Dimension(280, 200));
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton removeButton = new JButton("Remove Item");
        JButton useItemButton = new JButton("Use Item");

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = itemList.getSelectedIndex();
                if (selectedIndex != -1) {
                    player.getInventory().delete(selectedIndex);
                    updateItemList();
                }
            }
        });

        useItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = itemList.getSelectedIndex();
                if (selectedIndex != -1) {
                    player.useItem(selectedIndex);
                    updateItemList();
                } else {
                    JOptionPane.showMessageDialog(null, "No item selected to use!");
                }

                //After use the item, remove it immediately
                if (selectedIndex != -1) {
                    player.getInventory().delete(selectedIndex);
                    updateItemList();
                }
            }
        });

        buttonPanel.add(removeButton);
        buttonPanel.add(useItemButton);

        add(buttonPanel, BorderLayout.SOUTH);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                gameFrame.requestFocusInWindow();
            }
        });

        player.getInventory().addListener(new InventoryChangeListener() {
            @Override
            public void onInventoryChanged() {
                updateItemList();
            }
        });


//        System.out.println("Is listModel initialized? " + (listModel != null));
//        System.out.println("List model initial size: " + listModel.getSize());
//        System.out.println("Dialog size: " + getSize());
//        System.out.println("InventoryGUI constructed successfully.");
        //new Exception("Created InventoryGUI").printStackTrace();

    }

    /**
     * Refreshes the item list, logging each item's name and icon status.
     * @author Oscar Wei
     */
    private void updateItemList() {
        listModel.clear();
        for (int i = 0; i < player.getInventory().getSize(); i++) {
            Item item = player.getInventory().getItem(i);
            if (item != null) {
                listModel.addElement(item);
                System.out.println("Item Name: " + item.getName());
                if (item.getIcon() != null) {
                    System.out.println("Item has an icon.");
                } else {
                    System.out.println("Item does NOT have an icon.");
                }
            }
        }
    }


    /**
     * update the item list
     */
    public void refreshInventory(){
        updateItemList();
    }

    /**
     *
     * @param visible if {@code true}, makes the {@code Dialog} visible,
     * otherwise hides the {@code Dialog}.
     * If the dialog and/or its owner
     * are not yet displayable, both are made displayable.  The
     * dialog will be validated prior to being made visible.
     * If {@code false}, hides the {@code Dialog} and then causes {@code setVisible(true)}
     * to return if it is currently blocked.
     * <p>
     * <b>Notes for modal dialogs</b>.
     * <ul>
     * <li>{@code setVisible(true)}:  If the dialog is not already
     * visible, this call will not return until the dialog is
     * hidden by calling {@code setVisible(false)} or
     * {@code dispose}.
     * <li>{@code setVisible(false)}:  Hides the dialog and then
     * returns on {@code setVisible(true)} if it is currently blocked.
     * <li>It is OK to call this method from the event dispatching
     * thread because the toolkit ensures that other events are
     * not blocked while this method is blocked.
     * </ul>
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
    }


//    public void printItemList() {
//        for (int i = 0; i < listModel.getSize(); i++) {
//            System.out.println(listModel.getElementAt(i));
//        }
//    }


}
