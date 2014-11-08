/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airservice.resources;

import airservice.restclient.Destination.DestinationClientGoogle;
import airservice.db.AOSMemoryDB;
import airservice.entity.destination.DestinationEntity;
import airservice.exceptions.BadRequestException;
import airservice.entity.destination.DestinationInput;
import airservice.entity.destination.DestinationOutput;
import airservice.exceptions.SystemException;
import airservice.restclient.Destination.DestinationInfoDTO.Location;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service Resource pro spravu destinaci, poskytuje REST rozhrani pro
 * vypis vsech destinaci, tvorbu novych destinaci, vypisovani jednotlivych
 * destinaci, update destinaci a mazani destinaci
 *
 * @author Tomas "sarzwest" Jiricek
 */
@Path("/rest/destination")
public class DestinationResource {

    @Context
    private UriInfo context;
    
    @Inject
    private DestinationClientGoogle destGoogle;

    /**
     * vraci vsechny destinace, bud ve formatu xml nebo json
     *
     * @return vsechny destinace z databaze
     */
    @GET
    @Path("/")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection<DestinationOutput> getAllDestinations() {
        Collection<DestinationOutput> destinationsReturn = new ArrayList<DestinationOutput>();
        for (DestinationEntity destination : AOSMemoryDB.getAllDestinations()) {
            DestinationOutput mappingDestination = new DestinationOutput(destination);
            destinationsReturn.add(mappingDestination);
        }
        return destinationsReturn;
    }

    /**
     * tvori novou destinaci a ulozi do databaze
     *
     * @param newDestination k ulozeni
     * @return pokud uz existuje destinace s fieldem name, tak vyhazuje vyjimku,
     * jinak vraci nove vytvorenou destinaci
     */
    @POST
    @Path("/")
    @RolesAllowed("admin")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createDestination(DestinationInput newDestination) {
        try {
            Location location = destGoogle.getLocation(newDestination.getName());
            DestinationEntity destination = new DestinationEntity(newDestination.getName(), context.getAbsolutePathBuilder(), location);
            boolean ret = AOSMemoryDB.addDestination(destination);
            if (!ret) {
                throw new BadRequestException("Cannot create destination.");
            }
            return Response.status(Response.Status.OK).entity(new DestinationOutput(destination)).build();
        } catch (SystemException ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }

    /**
     * vraci destinaci podle uri, kde hraje roli identifikator
     *
     * @param id identifikator destinace, kterou chceme vratit
     * @return
     */
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public DestinationOutput getDestination(@PathParam("id") Long id) {
        DestinationEntity destination = AOSMemoryDB.getDestinationById(id);
        return new DestinationOutput(destination);//Mapper.convertToMappingDestination(destination, context.getAbsolutePathBuilder());
    }

    /**
     * updatuje jiz ulozenou destinaci v databazi podle id
     *
     * @param id jednoznacne id destinace
     * @param destinationToUpdate updatovana destinace k ulozeni do databaze
     * @return vraci upravenou destinaci v databazi nebo vyhazuje vyjimku pokud
     * se v databazi nenachazi destinace s danym ID
     */
    @PUT
    @Path("/{id}")
    @RolesAllowed("admin")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public DestinationOutput updateDestination(@PathParam("id") Long id, DestinationInput destinationToUpdate) {
        DestinationEntity destination = AOSMemoryDB.getDestinationById(id);
        if (destination != null) {
            destination.setName(destinationToUpdate.getName());
            return new DestinationOutput(destination);//Mapper.convertToMappingDestination(destination, context.getAbsolutePathBuilder());
        } else {
            throw new BadRequestException("Nelze updatovat destinaci, protoze se v databazi nenachazi zadna s odpovidajicim ID");
        }
    }

    /**
     * smaze destinaci z databaze
     *
     * @param id identifikator, podle ktereho se smaze destinace
     * @return true pokud doslo ke smazani, false pokud destinace nebyla v
     * databazi
     */
    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response deleteDestination(@PathParam("id") Long id) {
        boolean status = AOSMemoryDB.removeDestinationByID(id);
        return Response.status(Response.Status.OK).entity(status).build();
    }
}
