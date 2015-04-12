package de.choong.model.anime;

import de.choong.model.Displayable;

public enum Genre implements Displayable {
    ACTION("Action"), //
    ADVENTURE("Adventure"), //
    CARS("Cars"), //
    COMEDY("Comedy"), //
    DEMENTIA("Dementia"), //
    DEMONS("Demons"), //
    DRAMA("Drama"), //
    ECCHI("Ecchi"), //
    FANTASY("Fantasy"), //
    GAME("Game"), //
    HAREM("Harem"), //
    HENTAI("Hentai"), //
    HISTORICAL("Historical"), //
    HORROR("Horror"), //
    JOSEI("Josei"), //
    KIDS("Kids"), //
    MAGIC("Kids"), //
    MARTIALARTS("Martial Arts"), //
    MECHA("Mecha"), //
    MILITARY("Military"), //
    MUSIC("Music"), //
    MYSTERY("Mystery"), //
    PARODY("Parody"), //
    POLICE("Police"), //
    PSYCHOLOGICAL("Psychological"), //
    ROMANCE("Romance"), //
    SAMURAI("Samurai"), //
    SCHOOL("School"), //
    SCIFI("Sci-Fi"), //
    SEINEN("Seinen"), //
    SHOUJO("Shoujo"), //
    SHOUJOAI("Shoujo Ai"), //
    SHOUNEN("Shounen"), //
    SHOUNENAI("Shounen Ai"), //
    SLICEOFLIFE("Slice of Life"), //
    SPACE("Space"), //
    SPORTS("Sports"), //
    SUPERPOWER("Super Power"), //
    SUPERNATURAL("Supernatural"), //
    THRILLER("Thriller"), //
    YAOI("Yaoi"), //
    YURI("Yuri"), //
    ;

    private String displayName;

    private Genre(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }
}
