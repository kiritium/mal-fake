package de.choong.model.anime;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.wicket.markup.html.form.upload.FileUpload;

import de.choong.model.BaseDO;

@Entity
@Table(name = "T_CHARACTER")
public class CharacterDO extends BaseDO {

    private static final long serialVersionUID = 2483968038787558600L;

    @Column(length = 40, nullable = false)
    private String name;

    @Column(length = 80)
    private String altName;

    @ManyToMany
    private Set<AnimeDO> animes;

    @Column(length = 750)
    private String description;

    @Transient
    private List<FileUpload> profileImgs;

    // TODO Quotes
    // private List<QuoteDO> quotes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAltName() {
        return altName;
    }

    public void setAltName(String altName) {
        this.altName = altName;
    }

    public Set<AnimeDO> getAnimes() {
        return animes;
    }

    public void setAnimes(Set<AnimeDO> animes) {
        this.animes = animes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<FileUpload> getProfileImgs() {
        return profileImgs;
    }

    public void setProfileImgs(List<FileUpload> profileImgs) {
        this.profileImgs = profileImgs;
    }

    public FileUpload getProfileImg() {
        if (profileImgs != null && profileImgs.isEmpty() == false) {
            return profileImgs.get(0);
        }
        return null;
    }
}
