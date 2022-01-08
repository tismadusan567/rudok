package action.filefilter;

import java.io.File;

public class PresentationFileFilter extends RudokFileFilter {
    public PresentationFileFilter() {
        extension = ".rps";
    }

    @Override
    public String getDescription() {
        return "Rudok Presentation (*" + extension + ")";
    }

    @Override
    public boolean accept(File f) {
        return (f.isDirectory() ||
                f.getName().toLowerCase().endsWith(extension));
    }

}
