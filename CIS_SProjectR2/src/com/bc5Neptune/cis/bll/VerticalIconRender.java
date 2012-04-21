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
public class VerticalIconRender extends DefaultListCellRenderer {

    private static final Color HIGHLIGHT_COLOR = new Color(67, 177, 237);
    private int hoverIndex = -1;  
    private MouseAdapter handler;  
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
        list.setLayoutOrientation(JList.VERTICAL);

        // set icon for cell image
        IconList obj = (IconList) value;
        JLabel lb = (JLabel)c;
        lb.setIcon(obj.getIcon());
        lb.setText(obj.getName());
        //c.addMouseListener(new MouseListener());
        /* tool tip */
        setToolTipText("Click me to detect face");
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

    public MouseAdapter getHandler(JList list) {
        if (handler == null) {
            handler = new HoverMouseHandler(list);
        }
        return handler;
    }

    class HoverMouseHandler extends MouseAdapter {

        private final JList list;

        public HoverMouseHandler(JList list) {
            this.list = list;
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setHoverIndex(-1);
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            int index = list.locationToIndex(e.getPoint());
            setHoverIndex(list.getCellBounds(index, index).contains(e.getPoint())
                    ? index : -1);
        }

        private void setHoverIndex(int index) {
            if (hoverIndex == index) {
                return;
            }
            hoverIndex = index;
            list.repaint();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("Mouse click");
        }
        
    }
}