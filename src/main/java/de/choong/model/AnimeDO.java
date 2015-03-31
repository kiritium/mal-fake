package de.choong.model;

import java.io.Serializable;

public class AnimeDO implements Serializable {
    private static final long serialVersionUID = 743646977201312600L;
    private int id;
    private String title;
    private int year;
    private String author;

    public AnimeDO() {
    }
    
    public AnimeDO(int id, String title, int year, String author) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.author = author;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
