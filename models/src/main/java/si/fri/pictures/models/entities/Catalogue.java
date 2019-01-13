package si.fri.pictures.models.entities;




import si.fri.pictures.models.dtos.Picture;

import javax.persistence.*;
import java.util.List;

@Entity(name = "catalogue")
@NamedQueries(value = {
       @NamedQuery(name = "Catalogue.getByIdProfile", query = "SELECT c FROM catalogue c WHERE c.idProfila = :idProfila"),
        @NamedQuery(name = "Catalogue.getById", query = "SELECT c FROM catalogue c WHERE c.id = :id"),
        @NamedQuery(name = "Catalogue.getAll", query = "SELECT c FROM catalogue c")
})
public class Catalogue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Transient
    private List<Picture> pictures;

    @Column(name = "idprofila")
    private Integer idProfila;


    private String opis;


    public Integer getId() {
        return id;
    }



    public List<Picture> getPictures() {
        return pictures;
    }

    public Integer getIdProfila() {
        return idProfila;
    }

    public String getOpis() {
        return opis;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public void setIdProfila(Integer idProfila) {
        idProfila = idProfila;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
