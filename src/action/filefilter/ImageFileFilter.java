package action.filefilter;

import java.io.File;
import java.util.Arrays;
import javax.swing.filechooser.FileFilter;

public class ImageFileFilter extends FileFilter {
    private final String[] okFileExtensions = new String[] { ".jpg", ".jpeg", ".png", ".gif" };

    @Override
    public String getDescription() {
        return "Image files (" + Arrays.toString(okFileExtensions) + ")";
    }

    @Override
    public boolean accept(File file) {
        if(file.isDirectory()) return true;
        for (String extension : okFileExtensions) {
            if (file.getName().toLowerCase().endsWith(extension)) {
                return true;
            }
        }
        return false;
    }


}