package si.fri.pictures.models.entities;

import si.fri.pictures.models.dtos.Picture;

import javax.persistence.*;

@Entity(name = "cataloguePictures")
@NamedQueries(value = {
        @NamedQuery(name = "CataloguePictures.getById", query = "SELECT c FROM cataloguePictures c WHERE c.id = :id"),
        @NamedQuery(name = "CataloguePictures.getAll", query = "SELECT c FROM cataloguePictures c")
})
public class CataloguePictures {

    @Id
    private Integer id;

    @Column(name = "idpicture")
    private Integer idPicture;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPicture() {
        return idPicture;
    }

    public void setIdPicture(Integer idPicture) {
        this.idPicture = idPicture;
    }
}
