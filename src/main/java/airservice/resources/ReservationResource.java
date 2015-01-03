/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airservice.resources;

import airservice.entity.creditcard.CreditCard;
import airservice.entity.creditcard.CreditCardInput;
import airservice.entity.reservation.ReservationEntity;
import airservice.entity.reservation.ReservationInput;
import airservice.exceptions.BadRequestException;
import airservice.exceptions.BusinessLogicException;
import airservice.logic.ReservationManager;
import airservice.entity.reservation.ReservationOutput;
import airservice.exceptions.SystemException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
@Path("/rest/flight/{flightId}/reservation")
public class ReservationResource {

    @Context
    private UriInfo context;
    @Inject
    ReservationManager rm;

    @POST
    @Path("/")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createReservation(@PathParam("flightId") Long id, ReservationInput newReservation) throws ParseException {
        try {
            ReservationEntity reservation = new ReservationEntity(newReservation, id);//Mapper.convertToReservation(newReservation, id);
            rm.createReservation(reservation);

            ReservationOutput mf = new ReservationOutput(reservation, context.getAbsolutePathBuilder());//Mapper.convertToMappingReservation(reservation, context.getAbsolutePathBuilder());
            return Response.status(Response.Status.OK).header("X-Password", reservation.getPassword()).entity(mf).build();
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ReservationResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new BadRequestException(ex.getMessage());
        }
    }

    @GET
    @Path("/")
    @RolesAllowed({"admin", "manager"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection<ReservationOutput> getAllReservationsInFlight(@PathParam("flightId") Long id) {
        Collection<ReservationOutput> cmr = new ArrayList<ReservationOutput>();
        for (ReservationEntity reservation : rm.getAllReservationsInFlight(id)) {
            cmr.add(new ReservationOutput(reservation, context.getAbsolutePathBuilder())/*Mapper.convertToMappingReservation(reservation, context.getAbsolutePathBuilder())*/);
        }
        return cmr;
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"admin", "manager"})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ReservationOutput getReservation(@PathParam("id") Long id, @HeaderParam("X-Password")String check) {
        try {
            ReservationEntity reservation = rm.getReservationById(id);
            reservation.checkPassword(check);
            return new ReservationOutput(reservation, context.getAbsolutePathBuilder());//Mapper.convertToMappingReservation(reservation, context.getAbsolutePathBuilder());
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ReservationResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new BadRequestException(ex.getMessage());
        }
    }

    @PUT
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ReservationOutput updateReservation(@PathParam("id") Long id, @HeaderParam("X-Password")String check, ReservationInput reservationToUpdate) {
        try {
            ReservationEntity reservation = rm.getReservationById(id);
            reservation.checkPassword(check);
            rm.updateReservation(reservationToUpdate, reservation);
            return new ReservationOutput(reservation, context.getAbsolutePathBuilder());//Mapper.convertToMappingReservation(reservation, context.getAbsolutePathBuilder());
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ReservationResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new BadRequestException(ex.getMessage());
        }
    }
    
    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response deleteReservation(@PathParam("id") Long id, @HeaderParam("X-Password")String check) {
        try {
            ReservationEntity reservation = rm.getReservationById(id);
            reservation.checkPassword(check);
            rm.removeReservationById(id);
            return Response.status(Response.Status.OK).build();
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ReservationResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new BadRequestException(ex.getMessage());
        }
    }
    
    @POST
    @Path("/{id}/payment")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response payReservation(@PathParam("id")Long id, @HeaderParam("X-Password")String check, CreditCardInput card){
        try {
            ReservationEntity reservation = rm.getReservationById(id);
            reservation.checkPassword(check);
            rm.payReservation(reservation, card);
            return Response.status(Response.Status.OK).build();
        } catch (BusinessLogicException ex) {
            throw new BadRequestException(ex.getMessage());
        } catch (SystemException ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }
}
