package de.choong.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.wicket.markup.html.form.upload.FileUpload;

// TODO rename
public class ConfigUtil {
    private static final String COVER_ABSOLUTE_PATH = "src/main/webapp/img/cover/";

    private static final String COVER_CONTEXT_PATH = "/img/cover/";

    public static final String COVER_FORMAT = "PNG";

    public static String getAbsoluteCoverPath(int id) {
        return COVER_ABSOLUTE_PATH + id + "." + COVER_FORMAT;
    }

    public static String getCoverPath(int id) {
        return COVER_CONTEXT_PATH + id + "." + COVER_FORMAT;
    }

    public static boolean isCoverAvailable(int id) {
        return new File(COVER_ABSOLUTE_PATH + id + "." + COVER_FORMAT).isFile();
    }

    public static void uploadCover(FileUpload cover, int id) throws IOException {
        if (cover == null) {
            return;
        }
        File file = new File(ConfigUtil.getAbsoluteCoverPath(id));
        BufferedImage img = ImageIO.read(cover.getInputStream());
        ImageIO.write(img, ConfigUtil.COVER_FORMAT, new FileOutputStream(file));
    }
}
