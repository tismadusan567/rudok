package gui.tree;

import model.RuNode;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class MyTreeEditor extends DefaultTreeCellEditor implements ActionListener {

    private Object clickedOn = null;
    private JTextField editTF = null;

    public MyTreeEditor(JTree tree, DefaultTreeCellRenderer renderer) {
        super(tree, renderer);
    }

    @Override
    public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {
//        super.getTreeCellEditorComponent(tree, value, isSelected, expanded, leaf, row);
        clickedOn = value;
        editTF = new JTextField(value.toString());
        editTF.addActionListener(this);
        return editTF;
    }

    @Override
    public boolean isCellEditable(EventObject event) {
        if (event instanceof MouseEvent)
            return ((MouseEvent) event).getClickCount() == 3;
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!(clickedOn instanceof MyTreeNode)) return;

        RuNode ruNode = ((MyTreeNode) clickedOn).getRuNode();
        ruNode.setName(e.getActionCommand());
        stopCellEditing();
    }
}
