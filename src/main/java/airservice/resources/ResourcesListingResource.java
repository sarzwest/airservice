/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airservice.resources;

import airservice.resources.mappings.MappingResource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
@Path("/")
public class ResourcesListingResource {

    @Context
    UriInfo context;

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public void getEvent() throws ServletException, IOException {
        HttpServletRequest request = ResteasyProviderFactory.getContextData(HttpServletRequest.class);
        HttpServletResponse response = ResteasyProviderFactory.getContextData(HttpServletResponse.class);
        request.getRequestDispatcher("/index.jsp").forward(request, response);

    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/")
    public List<MappingResource> resources() {
        List<MappingResource> resources = new ArrayList<MappingResource>();
        resources.add(new MappingResource("destinations",
                context.getAbsolutePathBuilder().path("rest/destination")
                .build().getPath()));
        resources.add(new MappingResource("flights",
                context.getAbsolutePathBuilder().path("rest/flight")
                .build().getPath()));
        resources.add(new MappingResource("reservations",
                context.getAbsolutePath().getPath() + "rest/flight/{flightId}/reservation"));
        return resources;
    }
}
