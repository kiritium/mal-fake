package de.choong.model.anime;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_ANIME")
public class AnimeDO implements Serializable {
    private static final long serialVersionUID = 743646977201312600L;

    @Id
    @GeneratedValue
    private int id;

    @Column(length = 40, nullable = false)
    private String title;

    @Column(length = 40)
    private String altTitle;

    @Column(length = 30)
    private String creator;

    @Column(length = 20)
    private String studio;

    @Column
    private Integer year;

    @Enumerated(EnumType.STRING)
    private Season season;

    @Enumerated(EnumType.STRING)
    private MediaType type;

    @Enumerated(EnumType.STRING)
    private AiringStatus status;

    @Column(length = 40)
    private String coverPath;

    public AnimeDO() {
    }

    public AnimeDO(int id, String title, String altTitle, String creator, String studio,
            Integer year) {
        this.id = id;
        this.title = title;
        this.altTitle = altTitle;
        this.creator = creator;
        this.studio = studio;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }
}
