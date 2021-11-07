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
        if (ruNode instanceof Workspace) {
            imageURL = getClass().getResource("/icons/icons32/book2.png");
        } else if (ruNode instanceof Project) {
            imageURL = getClass().getResource("/icons/icons32/folders.png");
        } else if (ruNode instanceof Presentation) {
            imageURL = getClass().getResource("/icons/icons32/presentation2.png");
        } else if (ruNode instanceof Slide) {
            imageURL = getClass().getResource("/icons/icons32/slide.png");
        }
        Icon icon = null;
        if (imageURL != null)
            icon = new ImageIcon(imageURL);
        setIcon(icon);

        return this;
    }
}
