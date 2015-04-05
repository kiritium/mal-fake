package de.choong.model.anime;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

    @Enumerated(EnumType.STRING)
    private Season season;

    @Enumerated(EnumType.STRING)
    private MediaType type;

    @Enumerated(EnumType.STRING)
    private AiringStatus status;

    @Column(length = 40)
    private String coverPath;

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

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
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
}
