package action.dialog;

import main.MainFrame;
import model.Project;
import model.RuNode;
import model.Workspace;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SelectProjectDialog extends JDialog {
    private final JComboBox<Project> cbProjects = new JComboBox<>();
    private Project selectedProject = null;

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

        Workspace ws = (Workspace) MainFrame.getInstance().getTree().getRootNode().getRuNode();
        for (RuNode ruNode : ws.getChildren()) {
            cbProjects.addItem((Project) ruNode);
        }
        JPanel cbPanel = new JPanel();
        cbPanel.add(cbProjects);
        add(cbPanel, BorderLayout.CENTER);

        JButton button = new JButton("Select");
        button.addActionListener(e -> {
            selectedProject = (Project) cbProjects.getSelectedItem();
            setVisible(false);
            dispose();
        });
        add(button, BorderLayout.SOUTH);
    }

    public Project showDialog() {
        setVisible(true);
        return selectedProject;
    }
}
