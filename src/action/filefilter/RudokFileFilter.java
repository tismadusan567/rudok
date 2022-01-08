package action.filefilter;

import javax.swing.filechooser.FileFilter;

public abstract class RudokFileFilter extends FileFilter {
    protected String extension;

    public String getExtension() {
        return extension;
    }
}
