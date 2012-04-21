/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bc5Neptune.cis.bll;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 *
 * @author phu.huynh
 */
/*
 * Description: render a lot of image in JList
 */
public class HorizontalIconRender extends DefaultListCellRenderer {

    final Color HIGHLIGHT_COLOR = new Color(67, 177, 237);

    class PopupTriggerListener extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent ev) {
            if (ev.isPopupTrigger()) {
                // menu.show(ev.getComponent(), ev.getX(), ev.getY());
                System.out.println("mousePress");
            }
        }

        @Override
        public void mouseReleased(MouseEvent ev) {
            if (ev.isPopupTrigger()) {
                //menu.show(ev.getComponent(), ev.getX(), ev.getY());
                System.out.println("mouseReleased");
            }
        }

        @Override
        public void mouseClicked(MouseEvent ev) {
            System.out.println("mouse click");
        }
    }

    @Override
    public Component getListCellRendererComponent(JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {
//        Border m_noFocusBorder =
//                // new EmptyBorder(4, 0, -5, 0); //top, left, right, bottom
//                new LineBorder(Color.LIGHT_GRAY, 2);;
        //value is a image will render
        // for default cell renderer behavior
        final Component c = super.getListCellRendererComponent(list, value,
                index, isSelected, cellHasFocus);
        list.setVisibleRowCount(1);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);

        // set icon for cell image
        IconList obj = (IconList) value;
        JLabel lb = (JLabel) c;
        lb.setIcon(obj.getIcon());
        lb.setText(obj.getName());
        //addMouseListener(null);
        //c.addMouseListener(new MouseListener());
        /* tool tip */
        setToolTipText("Right click");
        /* set border for face */
        //setBorder((cellHasFocus) ? UIManager.getBorder("List.focusCellHighlightBorder") : m_noFocusBorder);

        if (isSelected) {
            setBackground(HIGHLIGHT_COLOR);
            setForeground(Color.white);
            // System.out.println(index);
        } else {
            setBackground(Color.white);
            setForeground(Color.black);
        }

        return c;

    }
}