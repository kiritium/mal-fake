package de.choong.form.validator;

import java.util.Arrays;
import java.util.Formatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;

public class FileUploadValidator implements IValidator<List<FileUpload>> {

    private static final long serialVersionUID = -4604048805555324687L;

    private long minSize = 0;
    private long maxSize = Long.MAX_VALUE;
    private Set<MimeType> fileTypes = null;

    public FileUploadValidator(long minSize, long maxSize, Set<MimeType> fileTypes) {
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.fileTypes = fileTypes;
    }

    public void validate(IValidatable<List<FileUpload>> validatable) {
        List<FileUpload> files = validatable.getValue();
        for (FileUpload file : files) {
            validate(file, validatable);
        }
    }

    private void validate(FileUpload fileUpload, IValidatable<List<FileUpload>> validatable) {
        if (fileUpload == null) {
            return;
        }

        long size = fileUpload.getSize();
        if (isInRange(size) == false) {
            ValidationError error = new ValidationError(this, "range");
            error.setVariable("minSize", convertToByteUnit(minSize));
            error.setVariable("maxSize", convertToByteUnit(maxSize));
            validatable.error(error);
        }
        String fileType = fileUpload.getContentType();
        if (fileTypes != null && fileTypes.contains(MimeTypeUtils.parseMimeType(fileType)) == false) {
            ValidationError error = new ValidationError(this, "contentType");
            error.setVariable("contentTypes", contentTypeErrorString());
            validatable.error(error);
        }
    }

    private boolean isInRange(long size) {
        return size >= minSize && size <= maxSize;
    }

    public static FileUploadValidator sizeBetween(long minSize, long maxSize) {
        return new FileUploadValidator(minSize, maxSize, null);
    }

    public static FileUploadValidator withContentTypes(MimeType... types) {
        Set<MimeType> fileTypes = new HashSet<MimeType>(Arrays.asList(types));
        return new FileUploadValidator(0, Long.MAX_VALUE, fileTypes);
    }

    /**
     * Convert bytes in byte with units (e.g. kB, B) atm supported formats: B,
     * kB, MB
     * 
     * @param bytes
     * @return
     */
    private String convertToByteUnit(long bytes) {
        double bytes2 = bytes;
        String str;
        Formatter formatter = new Formatter();
        if (bytes2 >= 100000) {
            bytes2 /= 100000;
            formatter.format("%.1f MB", bytes2);
        } else if (bytes2 >= 1000) {
            bytes2 /= 1000;
            formatter.format("%.1f kB", bytes2);
        } else {
            formatter.format("%s B", bytes);
        }
        str = formatter.toString();
        formatter.close();
        return str;
    }

    private String contentTypeErrorString() {
        StringBuilder sb = new StringBuilder();
        for (MimeType type : fileTypes) {
            if (sb.length() != 0) {
                sb.append(", ");
            }
            sb.append(type.getSubtype());
        }
        return sb.toString();
    }
}
