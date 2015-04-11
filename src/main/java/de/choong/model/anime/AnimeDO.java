package de.choong.model.anime;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.wicket.markup.html.form.upload.FileUpload;

import de.choong.model.BaseDO;

@Entity
@Table(name = "T_ANIME")
public class AnimeDO extends BaseDO {
    private static final long serialVersionUID = 743646977201312600L;

    @Column(length = 40, nullable = false)
    private String title;

    @Column(length = 40)
    private String altTitle;

    @Column(length = 750)
    private String summary;

    @Column(length = 30)
    private String creator;

    @Column(length = 20)
    private String studio;

    @Column
    private Integer year;

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private Season season;

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private MediaType type;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private AiringStatus status;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Genre.class)
    @CollectionTable(name = "T_ANIME_GENRES", joinColumns = @JoinColumn(name = "anime_fk"))
    private Set<Genre> genres;

    // TODO remove, after CharacterDao
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "T_ANIME_CHARACTER",
            joinColumns = { @JoinColumn(name = "anime_id", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "character_id", nullable = false) })
    private Set<CharacterDO> characters;

    @Transient
    private List<FileUpload> covers;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getAltTitle() {
        return altTitle;
    }

    public void setAltTitle(String altTitle) {
        this.altTitle = altTitle;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public MediaType getType() {
        return type;
    }

    public void setType(MediaType type) {
        this.type = type;
    }

    public AiringStatus getStatus() {
        return status;
    }

    public void setStatus(AiringStatus status) {
        this.status = status;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<FileUpload> getCovers() {
        return covers;
    }

    public void setCovers(List<FileUpload> covers) {
        this.covers = covers;
    }

    public FileUpload getCover() {
        if (covers != null && covers.isEmpty() == false) {
            return covers.get(0);
        }
        return null;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<CharacterDO> getCharacters() {
        return characters;
    }

    public void setCharacters(Set<CharacterDO> characters) {
        this.characters = characters;
    }
}
