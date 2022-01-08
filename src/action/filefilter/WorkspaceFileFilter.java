package action.filefilter;

import java.io.File;

public class WorkspaceFileFilter extends RudokFileFilter {
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
