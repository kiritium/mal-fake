package de.choong.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public class BaseDO implements Serializable {

    private static final long serialVersionUID = -2658667658971240676L;

    @Id
    @GeneratedValue
    private int id;

    @Column
    private Date created;

    @Column
    private Date modified;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    // Does not work with session API
    @PrePersist
    public void onCreate() {
        created = new Date();
        onUpdate();
    }

    // Does not work with session API
    @PreUpdate
    public void onUpdate() {
        modified = new Date();
    }
}