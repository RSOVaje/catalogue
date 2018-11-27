package si.fri.pictures.api.v1.resources;

import si.fri.pictures.models.dtos.Info;
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

    @GET
    @Path("/demo/info")
    public Response getInfo() {
        Info info = new Info();

        List<String> clani = info.getClani();
        clani.add("db6692");
        clani.add("dm7571");
        info.setClani(clani);

        info.setOpis_projekta("Nas projekt implementira aplikacijo za deljenje slik.");

        List<String> mikrostoritve = info.getMikrostoritve();
        mikrostoritve.add("http://35.189.96.118:8081/v1/catalogue");
        mikrostoritve.add("http://35.197.209.159:8080/v1/profile");
        info.setMikrostoritve(mikrostoritve);

        List<String> github = info.getGithub();
        github.add("https://github.com/RSOVaje/catalogue");
        github.add("https://github.com/RSOVaje/profile");
        info.setGithub(github);

        List<String> travis = info.getTravis();
        travis.add("https://travis-ci.com/RSOVaje/catalogue");
        travis.add("https://travis-ci.com/RSOVaje/profile");
        info.setTravis(travis);

        List<String> dockerhub = info.getDockerhub();
        dockerhub.add("https://hub.docker.com/r/rsovaje/catalogue/");
        dockerhub.add("https://hub.docker.com/r/rsovaje/profile/");
        info.setDockerhub(dockerhub);


        if (info == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(info).build();
    }

}
