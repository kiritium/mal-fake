package de.choong.model;

import java.io.Serializable;

import javax.persistence.Entity;
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

    private String title;
    private String altTitle;
    private String creator;
    private String studio;
    private Integer year;

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
}
