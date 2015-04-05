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

    private boolean required = false;
    private long minSize = 0;
    private long maxSize = Long.MAX_VALUE;
    private Set<MimeType> fileTypes = null;

    public FileUploadValidator(boolean required, long minSize, long maxSize, Set<MimeType> fileTypes) {
        this.required = required;
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

    // TODO error message
    private void validate(FileUpload fileUpload, IValidatable<List<FileUpload>> validatable) {
        if (fileUpload == null) {
            if (required) {
                validatable.error(new ValidationError("File upload is required."));
            }
            return;
        }

        long size = fileUpload.getSize();
        if (isInRange(size) == false) {

            validatable.error(new ValidationError(rangeErrorString()));
        }

        String fileType = fileUpload.getContentType();

        if (fileTypes != null && fileTypes.contains(MimeTypeUtils.parseMimeType(fileType)) == false) {
            validatable.error(new ValidationError(contentTypeErrorString()));
            System.out.println("content type");
        }
    }

    private boolean isInRange(long size) {
        return size >= minSize && size <= maxSize;
    }

    public static FileUploadValidator sizeBetween(long minSize, long maxSize) {
        return new FileUploadValidator(false, minSize, maxSize, null);
    }

    public static FileUploadValidator withContentTypes(MimeType... types) {
        Set<MimeType> fileTypes = new HashSet<MimeType>(Arrays.asList(types));
        return new FileUploadValidator(false, 0, Long.MAX_VALUE, fileTypes);
    }

    private String rangeErrorString() {
        Formatter formatter = new Formatter();
        String str = formatter.format("File size must be between %s and %s.",
                convertToByteUnit(minSize), convertToByteUnit(maxSize)).toString();
        formatter.close();
        return str;
    }

    /**
     * Convert bytes in byte with units (e.g. kB, B) atm supported formats: B,
     * kB
     * 
     * @param bytes
     * @return
     */
    private String convertToByteUnit(long bytes) {
        double bytes2 = bytes;
        String str;
        Formatter formatter = new Formatter();
        if (bytes2 >= 1000) {
            bytes2 /= 1000;
            formatter.format("%.1f kB", bytes2);
        } else {
            formatter.format("%s B", bytes);
        }
        str = formatter.toString();
        formatter.close();
        return str;
    }

    // TODO make it pretty
    private String contentTypeErrorString() {
        StringBuilder sb = new StringBuilder();
        sb.append("File type has to be: ");
        for (MimeType type : fileTypes) {
            sb.append(type.getSubtype() + ", ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        sb.append('.');
        return sb.toString();
    }
}
