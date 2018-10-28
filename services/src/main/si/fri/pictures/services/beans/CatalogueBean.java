package main.si.fri.pictures.services.beans;

import javax.enterprise.context.RequestScoped;
import com.kumuluz.ee.discovery.annotations.DiscoverService;
import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;


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

        TypedQuery<Catalogue> query = em.createNamedQuery("Catalogue.getByIdProfile", Customer.class).setParameter("idProfila", idProfila);

        return query.getResultList();

    }

    public Catalogue getCatalogueById(Integer id) {

        Catalogue query = em.createNamedQuery("Catalogue.getById", Customer.class).setParameter("id", id);

        return query.getResultList();

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
