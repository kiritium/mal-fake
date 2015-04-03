package de.choong.model.anime;

public enum AiringStatus {
    NOT_AIRED_YET("Not aired yet"), //
    AIRING("Airing"), //
    COMPLETED("Completed"), //
    ;

    private String displayName;

    private AiringStatus(String displayName) {
        this.setDisplayName(displayName);
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
