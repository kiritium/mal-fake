package de.choong.model.anime;

import de.choong.model.Displayable;

public enum MediaType implements Displayable {
    TV("TV"), //
    MOVIE("Movie"), //
    OVA("OVA"), //
    ;

    private String displayName;

    private MediaType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
