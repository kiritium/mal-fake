package de.choong.model.anime;

import de.choong.model.Displayable;

public enum AiringStatus implements Displayable {
    NOT_AIRED_YET("Not aired yet"), //
    AIRING("Airing"), //
    COMPLETED("Completed"), //
    ;

    private String displayName;

    private AiringStatus(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }
}
