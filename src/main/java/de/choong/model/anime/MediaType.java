package de.choong.model.anime;

public enum MediaType {
    TV("TV"), //
    MOVIE("Movie"), //
    OVA("OVA"), //
    ;

    private String displayName;

    private MediaType(String displayName) {
        this.setDisplayName(displayName);
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
