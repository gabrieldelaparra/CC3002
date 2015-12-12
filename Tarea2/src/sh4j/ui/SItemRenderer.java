package sh4j.ui;

import sh4j.model.browser.SObject;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * Each object of this class represent a cell in the browser for instance, package cell, class cell
 * and method cell.
 *
 * @author juampi
 */
@SuppressWarnings("ALL")
class SItemRenderer extends JLabel implements ListCellRenderer<SObject> {
  protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

  /**
   * create a item renderere
   */
  public SItemRenderer() {
    setOpaque(true);
    setBorder(BorderFactory.createLineBorder(Color.WHITE));
  }

  /**
   * return a JLabel for each SObject in the JList
   */
  public Component getListCellRendererComponent(
      JList<? extends SObject> list, SObject value, int index,
      boolean isSelected, boolean cellHasFocus) {
    setText(value.toString());
    setFont(value.font());
    setIcon(new ImageIcon(value.icon()));
    setBackground(value.background());
    if (isSelected) {
      setBorder(BorderFactory.createLineBorder(Color.BLUE));
    } else {
      setBorder(BorderFactory.createLineBorder(Color.WHITE));
    }
    return this;
  }

}
