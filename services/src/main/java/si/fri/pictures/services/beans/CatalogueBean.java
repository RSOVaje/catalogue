package si.fri.pictures.services.beans;

import javax.enterprise.context.RequestScoped;
import com.kumuluz.ee.discovery.annotations.DiscoverService;
import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.pictures.models.dtos.Picture;
import si.fri.pictures.models.entities.Catalogue;
import si.fri.pictures.models.entities.CataloguePictures;
import si.fri.pictures.services.configuration.AppProperties;


import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RequestScoped
public class CatalogueBean {

    private Logger log = Logger.getLogger(CatalogueBean.class.getName());

    @Inject
    private EntityManager em;

    @Inject
    private AppProperties appProperties;

    @Inject
    private CatalogueBean catalogueBean;

    @Inject
    @DiscoverService("picture")
    private Optional<String> pictureUrl;

    private Client httpClient;

    //@Inject
    //@DiscoverService("")
    //private Optional<String> baseUrl;

    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
//        baseUrl = "http://localhost:8081"; // only for demonstration
    }

    public List<Catalogue> getCatalogueByPerson(Integer idProfila) {

        TypedQuery<Catalogue> query = em.createNamedQuery("Catalogue.getByIdProfile", Catalogue.class).setParameter("idProfila", idProfila);

        return query.getResultList();

    }
    public List<Catalogue> getCatalogue() {

        TypedQuery<Catalogue> query = em.createNamedQuery("Catalogue.getAll", Catalogue.class);

        return query.getResultList();

    }

    public Catalogue getCatalogueById(Integer id) {
        TypedQuery<Catalogue> query = em.createNamedQuery("Catalogue.getById", Catalogue.class).setParameter("id", id);
        Catalogue catalogue =  query.getSingleResult();

        TypedQuery<CataloguePictures> query2 = em.createNamedQuery("CataloguePictures.getByIdCatalogue", CataloguePictures.class).setParameter("idCatalogue", id);

        List<CataloguePictures> cp = query2.getResultList();
        Iterator it = cp.iterator();

        List<Picture> pic = catalogue.getPictures();
        if(it.hasNext() == true) {
            pic = catalogue.getPictures();
            for (int i = 0; i < cp.size(); i++) {
                CataloguePictures cat = cp.get(i);
                Integer pid = cat.getIdPicture();
                Picture pi = getPicture(pid);
                if (pi != null) {
                    pic.add(pi);
                }
            }
        }
        catalogue.setPictures(pic);
        return catalogue;

    }

    public Picture getPicture(Integer id) {
        if(appProperties.isExternalServicesEnabled() && pictureUrl.isPresent()) {
            try {
                return httpClient
                        .target(pictureUrl.get() + "/v1/picture/" + id)
                        .request().get(new GenericType<Picture>() {
                        });
            } catch (WebApplicationException | ProcessingException e) {
                log.severe(e.getMessage());
                throw new InternalServerErrorException(e);
            }
        }
        return null;
    }

    public Catalogue createCatalogue(Catalogue catalogue) {

        try {
            beginTx();
            em.persist(catalogue);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        return catalogue;
    }

    public CataloguePictures addPictureInCatalogue(CataloguePictures cp) {

        try {
            beginTx();
            em.persist(cp);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        return cp;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive())
            em.getTransaction().begin();
    }

    private void commitTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().commit();
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().rollback();
    }


}
