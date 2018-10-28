package main.si.fri.pictures.models.entities;




import javax.persistence.*;

@Entity(name = "catalogue")
@NamedQueries(value = {
       @NamedQuery(name = "Catalogue.getByIdProfile", query = "SELECT c FROM catalogue c WHERE c.idProfila = :idProfila"),
        @NamedQuery(name = "Catalogue.getById", query = "SELECT c FROM catalogue c WHERE c.id = :id")
})
public class Catalogue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String picture;


    private Integer idProfila;


    private String opis;


    public Integer getId() {
        return id;
    }

    public String getPicture() {
        return picture;
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

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setIdProfila(Integer idProfila) {
        idProfila = idProfila;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
