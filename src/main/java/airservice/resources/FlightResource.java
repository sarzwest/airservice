/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airservice.resources;

import airservice.db.AOSMemoryDB;
import airservice.entity.flight.FlightEntity;
import airservice.exceptions.BadRequestException;
import airservice.entity.flight.FlightInput;
import airservice.entity.flight.FlightOutput;
import airservice.exceptions.SystemException;
import airservice.restclient.Destination.CurrencyClientAppspot;
import airservice.restclient.Destination.DistanceClientAOS;
import airservice.util.CollectionWrapper;
import airservice.util.Properties;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;
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
@Path("/rest/flight")
public class FlightResource {

    @Context
    private UriInfo context;
    @Inject
    DistanceClientAOS distAOS;
    @Inject
    CurrencyClientAppspot currAppspot;

    @POST
    @Path("/")
    @RolesAllowed("admin")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createFlight(@HeaderParam("X-Currency") String currency, FlightInput newFlight) throws ParseException {
        try {
            FlightEntity flight = new FlightEntity(newFlight, context.getAbsolutePathBuilder());//Mapper.convertToFlight(newFlight);
            float distance = distAOS.getDistance(flight.getFrom().getLocation(), flight.getTo().getLocation());
            flight.setDistance(distance);
            boolean ret = AOSMemoryDB.addFlight(flight);
            if (!ret) {
                throw new BadRequestException("Cannot create flight");
            }
            FlightOutput mf = new FlightOutput(flight);//Mapper.convertToMappingFlight(flight, context.getAbsolutePathBuilder());
            setCurrency(currency, mf);
            return Response.status(Response.Status.OK).entity(mf).build();
        } catch (SystemException ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }

    @GET
    @Path("/")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAllFlights(@HeaderParam("X-Currency") String currency, @HeaderParam("X-Filter") String filter, @HeaderParam("X-Order") String order, @HeaderParam("X-Base") String base, @HeaderParam("X-Offset") String offset) {
        try {
            Date from = parseFilterFromDate(filter);
            Date to = parseFilterToDate(filter);
            int baseNum = parseBase(base);
            int offsetNum = parseOffset(offset);
            Collection<FlightOutput> cmf = new ArrayList<FlightOutput>();
            for (FlightEntity flight : AOSMemoryDB.getAllFlights(from, to, baseNum, offsetNum).orderBy(order)) {
                FlightOutput mf = new FlightOutput(flight);
                setCurrency(currency, mf);
                cmf.add(mf);
            }
            return Response.status(Response.Status.OK).header("X-Count-records", cmf.size()).entity(new CollectionWrapper(cmf)).build();
        } catch (SystemException ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getFlight(@HeaderParam("X-Currency") String currency, @PathParam("id") Long id) {
        try {
            FlightEntity flight = AOSMemoryDB.getFlightById(id);
            FlightOutput mf = new FlightOutput(flight);
            setCurrency(currency, mf);
            return Response.status(Response.Status.OK).entity(mf).build();
        } catch (SystemException ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("admin")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response updateFlight(@HeaderParam("X-Currency") String currency, @PathParam("id") Long id, FlightInput flightToUpdate) {
        FlightEntity flight = AOSMemoryDB.getFlightById(id);
        if (flight != null) {
            flight.setName(flightToUpdate.getName());
            FlightOutput mf = new FlightOutput(flight);
            try {
                setCurrency(currency, mf);
            } catch (SystemException ex) {
                throw new BadRequestException(ex.getMessage());
            }
            return Response.status(Response.Status.OK).entity(mf).build();
        } else {
            throw new BadRequestException("Nelze updatovat let, protoze se v databazi nenachazi zadny s odpovidajicim ID");
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response deleteFlight(@PathParam("id") Long id) {
        boolean status = AOSMemoryDB.removeFlightByID(id);
        return Response.status(Response.Status.OK).entity(status).build();
    }

    private Date parseFilterFromDate(String filter) {
        try {
            String dateOfDepartureFrom = filter.split(",")[0].split("=")[1];
            return new SimpleDateFormat(Properties.DATE_FORMAT).parse(dateOfDepartureFrom);
        } catch (Exception ex) {
            return null;
        }
    }

    private Date parseFilterToDate(String filter) {
        try {
            String dateOfDepartureFrom = filter.split(",")[1].split("=")[1];
            return new SimpleDateFormat(Properties.DATE_FORMAT).parse(dateOfDepartureFrom);
        } catch (Exception ex) {
            return null;
        }
    }

    public int parseBase(String base) {
        int baseNum = Integer.MAX_VALUE;
        if (base != null) {
            try {
                baseNum = Integer.parseInt(base);
            } catch (NumberFormatException ex) {
            }
        }
        return baseNum;
    }

    public int parseOffset(String offset) {
        int offsetNum = 0;
        if (offset != null) {
            try {
                offsetNum = Integer.parseInt(offset);
            } catch (NumberFormatException ex) {
            }
        }
        return offsetNum;
    }

    private void setCurrency(String currency, FlightOutput mf) throws SystemException {
        if (currency != null && !currency.equals("")) {
            float rate = currAppspot.getRate("CZK", currency);
            mf.setPrice(mf.getPrice() * rate);
        }
    }
}
