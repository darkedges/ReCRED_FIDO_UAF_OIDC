package eu.recred.fidouaf.res;

/**
 * Created by georgeg on 13.04.2016.
 */
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.jar.Manifest;

@Path("/about")
public class About {

    @Context
    private ServletContext context;

    @GET
    @Path("/")
    public Response getMsg() {

        Response response = Response.status(200).entity("OK!").build();

        try {
            InputStream inputStream = context.getResourceAsStream("/META-INF/MANIFEST.MF");
            Manifest manifest0 = new Manifest(inputStream);

            StringBuilder sb = new StringBuilder();
            for (Map.Entry entry: manifest0.getMainAttributes().entrySet()) {
                sb.append(entry.getKey() + ": " + entry.getValue() + "\n");
            }
            response = Response.status(200).entity(sb.toString()).build();
        }catch (IOException ex) {
            ex.printStackTrace();
        }

        return response;
    }
}
