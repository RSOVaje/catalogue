package si.fri.pictures.api.v1.resources;

import si.fri.pictures.models.entities.Catalogue;
import si.fri.pictures.services.beans.CatalogueBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;


@RequestScoped
@Path("/catalogue")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CatalogueResource {

    @Inject
    private CatalogueBean catalogueBean;

    @Context
    protected UriInfo uriInfo;

    @POST
    public Response UploadAndSave(Catalogue catalogue) {


        catalogue = catalogueBean.createCatalogue(catalogue);

        if (catalogue.getId() != null) {
            return Response.status(Response.Status.CREATED).entity(catalogue).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity(catalogue).build();
        }
    }

    @GET
    @Path("profil/{idProfila}")
    public Response getCatalogueByIdProfila(@PathParam("idProfila") Integer idProfila) {
        List<Catalogue> list = catalogueBean.getCatalogueByPerson(idProfila);
        if (list == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(list).build();
    }

    @GET
    @Path("/{id}")
    public Response getCatalogueById(@PathParam("id") Integer id) {
         Catalogue catalogue = catalogueBean.getCatalogueById(id);
        if (catalogue == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(catalogue).build();
    }

}
