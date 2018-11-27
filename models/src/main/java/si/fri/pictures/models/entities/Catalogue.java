package si.fri.pictures.models.entities;




import javax.persistence.*;

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


    private Byte[] picture;

    @Column(name = "idprofila")
    private Integer idProfila;


    private String opis;


    public Integer getId() {
        return id;
    }



    public Byte[] getPicture() {
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

    public void setPicture(Byte[] picture) {
        this.picture = picture;
    }

    public void setIdProfila(Integer idProfila) {
        idProfila = idProfila;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
