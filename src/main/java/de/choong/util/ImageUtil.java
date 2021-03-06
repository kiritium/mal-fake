package de.choong.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.wicket.markup.html.form.upload.FileUpload;

public class ImageUtil {
    private static final String COVER_ABSOLUTE_PATH = "src/main/webapp/img/cover/";

    private static final String COVER_CONTEXT_PATH = "/img/cover/";

    private static final String COVER_DEFAULT_PATH = "/img/cover/nocover.jpg";

    public static final String COVER_FORMAT = "jpg";

    private static final String SLIDESHOW_ABSOLUTE_PATH = "src/main/webapp/img/slideshow/";

    private static final String SLIDESHOW_CONTEXT_PATH = "/img/slideshow/";

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
        File file = new File(getAbsoluteCoverPath(id));
        BufferedImage img = ImageIO.read(cover.getInputStream());
        ImageIO.write(img, COVER_FORMAT, new FileOutputStream(file));
    }

    public static String getCoverDefaultPath() {
        return COVER_DEFAULT_PATH;
    }

    public static String getSlideshowPath(String name) {
        return SLIDESHOW_CONTEXT_PATH + name;
    }

    public static String getAbsoluteSlideshowPath() {
        return SLIDESHOW_ABSOLUTE_PATH;
    }
}
