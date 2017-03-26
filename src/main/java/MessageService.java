import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by MSI on 2017-03-26.
 */
@Path("/message")
@Produces(MediaType.APPLICATION_JSON)
public class MessageService {

    @GET
    @Path("/{p1}")
    public Response printMessage(@PathParam("p1") String p1) {
        String result = "Przyklad pierwszy restful : " + p1;
        return Response.status(200).entity(result).build();
    }

    @GET
    @Path("/{p1}/{p2}")
    public Response printMessage(@PathParam("p1") String p1, @PathParam("p2") String p2) {
        String result = "Przyklad drugi restful : " + p1 + " " + p2;
        return Response.status(200).entity(result).build();
    }

}