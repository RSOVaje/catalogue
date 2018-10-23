package main.si.fri.pictures.models.entities;




import javax.persistence.*;


@Entity(name = "catalogue")
public class Catalogue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String picture;


    private Integer IdProfila;

    public Integer getId() {
        return id;
    }

    public String getPicture() {
        return picture;
    }

    public Integer getIdProfila() {
        return IdProfila;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setIdProfila(Integer idProfila) {
        IdProfila = idProfila;
    }
}
