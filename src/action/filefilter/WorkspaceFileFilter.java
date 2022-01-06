package action.filefilter;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class WorkspaceFileFilter extends MyFileFilter {
    public WorkspaceFileFilter() {
        extension = ".rws";
    }

    @Override
    public String getDescription() {
        return "Rudok Workspace (*" + extension + ")";
    }

    @Override
    public boolean accept(File f) {
        return (f.isDirectory() ||
                f.getName().toLowerCase().endsWith(extension));
    }

}
