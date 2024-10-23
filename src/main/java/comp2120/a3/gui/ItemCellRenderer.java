package comp2120.a3.gui;


import comp2120.a3.Item;

import javax.swing.*;
import java.awt.*;

public class ItemCellRenderer extends JLabel implements ListCellRenderer<Item> {
    /**
     * Renders inventory list cells with item name/icon or "Empty Slot", respecting selection state.
     *
     * @param list The JList we're painting.
     * @param item The value returned by list.getModel().getElementAt(index).
     * @param index The cells index.
     * @param isSelected True if the specified cell was selected.
     * @param cellHasFocus True if the specified cell has the focus.
     * @return
     *
     * @author Oscar Wei
     */
    @Override
    public Component getListCellRendererComponent(JList<? extends Item> list, Item item, int index, boolean isSelected, boolean cellHasFocus) {

//        setText(item.getName());
//        setIcon(item.getIcon());

        if (item != null) {
            setText(item.toString());
            setIcon(item.getIcon());
        } else {
            setText("Empty Slot");
            setIcon(null);
        }

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        setEnabled(list.isEnabled());
        setFont(list.getFont());
        setOpaque(true);

        return this;
    }
}

