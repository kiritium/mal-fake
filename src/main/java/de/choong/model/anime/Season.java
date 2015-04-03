package de.choong.model.anime;

public enum Season {
    SPRING("Spring"), //
    SUMMER("Summer"), //
    FALL("Fall"), //
    WINTER("Winter"), //
    ;

    private String displayName;

    private Season(String displayName) {
        this.setDisplayName(displayName);
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
