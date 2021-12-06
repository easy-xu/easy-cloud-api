package cloud.easy.utils;

import java.io.File;
import java.io.IOException;

/**
 * FileUtils
 *
 * @author xu honglin
 * @date 2021/12/4 20:10
 */
public class FileUtils {
    private FileUtils() {
    }

    public static void makeDirs(File dir) throws IOException {
        if (dir.exists()) {
            if (!dir.isDirectory()) {
                throw new IOException(dir.getAbsolutePath() + " exist, and not a directory");
            }
        } else {
            if (!dir.mkdirs() && !dir.isDirectory()) {
                throw new IOException(dir.getAbsolutePath() + " not a directory");
            }
        }
    }
}
