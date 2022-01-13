package action.dialog;

import gui.tree.MyTreeNode;
import main.MainFrame;
import model.Project;
import model.RuNode;
import model.Workspace;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.util.Iterator;

public class SelectProjectDialog extends JDialog {
    private final JComboBox<MyTreeNode> cbProjects = new JComboBox<>();
    private MyTreeNode selectedTreeNode = null;

    public SelectProjectDialog(JDialog parent) {
        super(parent, "Select project", true);
        init();
    }

    public SelectProjectDialog(JFrame parent) {
        super(parent, "Select project", true);
        init();
    }

    private void init() {
        setSize(250, 250);
        setLocationRelativeTo(getParent());
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Select project");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(new EmptyBorder(5, 5, 15, 5));
        add(label, BorderLayout.NORTH);

        Iterator<TreeNode> it = MainFrame.getInstance().getTree().getRootNode().children().asIterator();
        while (it.hasNext()) {
            cbProjects.addItem((MyTreeNode) it.next());
        }
        JPanel cbPanel = new JPanel();
        cbPanel.add(cbProjects);
        add(cbPanel, BorderLayout.CENTER);

        JButton button = new JButton("Select");
        button.addActionListener(e -> {
            selectedTreeNode = (MyTreeNode) cbProjects.getSelectedItem();
            setVisible(false);
            dispose();
        });
        add(button, BorderLayout.SOUTH);
    }

    public MyTreeNode showDialog() {
        setVisible(true);
        return selectedTreeNode;
    }
}
