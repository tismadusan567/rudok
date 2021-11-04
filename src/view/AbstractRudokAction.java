package view;

import javax.swing.*;
import java.net.URL;

public abstract class AbstractRudokAction extends AbstractAction {

    public Icon loadIcon(String filename) {
        URL imageURL = getClass().getResource(filename);
        Icon icon = null;

        if(imageURL != null) {
            icon = new ImageIcon(imageURL);
        } else {
            System.err.println("Resource not found " + filename);
        }
        return icon;
    }
}
