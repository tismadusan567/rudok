package gui.tree;

import model.*;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class MyTreeCellRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(
            JTree tree,
            Object value,
            boolean sel,
            boolean expanded,
            boolean leaf,
            int row,
            boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        RuNode ruNode = ((MyTreeNode) value).getRuNode();
        URL imageURL = null;
        int fontStyle = Font.PLAIN;
        if (ruNode instanceof Workspace) {
            imageURL = getClass().getResource("/icons/icons32/book2.png");
        } else if (ruNode instanceof Project) {
            imageURL = getClass().getResource("/icons/icons32/folders.png");
        } else if (ruNode instanceof Presentation presentation) {
            imageURL = getClass().getResource("/icons/icons32/presentation2.png");
            //if presentation is shared set font to bold
            if (presentation.isShared()) fontStyle = Font.BOLD;
        } else if (ruNode instanceof Slide) {
            imageURL = getClass().getResource("/icons/icons32/slide.png");
        }
        Icon icon = null;
        if (imageURL != null)
            icon = new ImageIcon(imageURL);
        setIcon(icon);

        //if the runode is changed set the font to italic
        if (ruNode.isChanged()) fontStyle |= Font.ITALIC;
        Font font = getFont().deriveFont(fontStyle);
        setFont(font);

        return this;
    }
}
