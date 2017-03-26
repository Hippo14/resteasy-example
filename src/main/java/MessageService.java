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
    @Path("/{param}")
    public Response printMessage(@PathParam("param") String msg) {
        String result = "Przyklad restful : " + msg;
        return Response.status(200).entity(result).build();
    }

}
