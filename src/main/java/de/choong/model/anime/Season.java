package de.choong.model.anime;

import de.choong.model.Displayable;

public enum Season implements Displayable {
    NONE(""), //
    SPRING("Spring"), //
    SUMMER("Summer"), //
    FALL("Fall"), //
    WINTER("Winter"), //
    ;

    private String displayName;

    private Season(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
