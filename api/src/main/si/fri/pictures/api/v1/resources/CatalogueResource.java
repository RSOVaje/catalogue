package main.si.fri.pictures.api.v1.resources;

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

        return
    }

}
