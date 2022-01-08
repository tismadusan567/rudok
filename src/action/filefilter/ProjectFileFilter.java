package action.filefilter;

import java.io.File;

public class ProjectFileFilter extends RudokFileFilter {
    public ProjectFileFilter() {
        extension = ".rpj";
    }

    @Override
    public String getDescription() {
        return "Rudok Project (*" + extension + ")";
    }

    @Override
    public boolean accept(File f) {
        return (f.isDirectory() ||
                f.getName().toLowerCase().endsWith(extension));
    }

}
