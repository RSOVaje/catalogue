package si.fri.pictures.api.v1.resources;

import si.fri.pictures.models.dtos.Info;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Path("/demo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DemoResource {

    @GET
    @Path("info")
    public Response getInfo() {
        Info info = new Info();

        List<String> clani = new ArrayList();
        clani.add("db6692");
        clani.add("dm7571");
        info.setClani(clani);

        info.setOpis_projekta("Nas projekt implementira aplikacijo za deljenje slik.");

        List<String> mikrostoritve = new ArrayList();
        mikrostoritve.add("http://35.189.96.118:8081/v1/catalogue");
        mikrostoritve.add("http://35.197.209.159:8080/v1/profile");
        info.setMikrostoritve(mikrostoritve);

        List<String> github = new ArrayList();
        github.add("https://github.com/RSOVaje/catalogue");
        github.add("https://github.com/RSOVaje/profile");
        info.setGithub(github);

        List<String> travis = new ArrayList();
        travis.add("https://travis-ci.com/RSOVaje/catalogue");
        travis.add("https://travis-ci.com/RSOVaje/profile");
        info.setTravis(travis);

        List<String> dockerhub = new ArrayList();
        dockerhub.add("https://hub.docker.com/r/rsovaje/catalogue/");
        dockerhub.add("https://hub.docker.com/r/rsovaje/profile/");
        info.setDockerhub(dockerhub);


        if (info == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(info).build();
    }

}
