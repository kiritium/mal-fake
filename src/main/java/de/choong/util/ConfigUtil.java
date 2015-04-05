package de.choong.util;

import java.io.File;

public class ConfigUtil {
    private static final String COVER_ABSOLUTE_PATH = "src/main/webapp/img/cover/";

    private static final String COVER_CONTEXT_PATH = "/img/cover/";

    public static String getCoverPath(String coverName) {
        return COVER_CONTEXT_PATH + coverName;
    }

    public static boolean isCoverAvailable(String coverName) {
        return new File(COVER_ABSOLUTE_PATH + coverName).isFile();
    }
}
