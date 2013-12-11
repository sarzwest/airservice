package airservice.db;



import airservice.entity.destination.DestinationEntity;
import airservice.entity.flight.FlightEntity;
import airservice.entity.reservation.ReservationEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that simulates in-memory database.
 */
public class AOSMemoryDB {
    
    private static Map<Long, DestinationEntity> destinationsById = new HashMap<Long, DestinationEntity>();
    private static Map<String, DestinationEntity> destinationsByName = new HashMap<String, DestinationEntity>();
    
    private static Map<Long, FlightEntity> flightsById = new HashMap<Long, FlightEntity>();
    private static Map<String, FlightEntity> flightsByName = new HashMap<String, FlightEntity>();
    
    private static Map<Long, ReservationEntity> reservationsById = new HashMap<Long, ReservationEntity>();
    
    public static boolean addDestination(DestinationEntity destination){
        String name = destination.getName();
        Long id = destination.getId();
        if(destinationsByName.containsKey(name)){
            return false;
        }
        destinationsById.put(id, destination);
        destinationsByName.put(name, destination);
        return true;
    }
    
    public static boolean addFlight(FlightEntity flight){
        String name = flight.getName();
        Long id = flight.getId();
        if(flightsByName.containsKey(name)){
            return false;
        }
        flightsById.put(id, flight);
        flightsByName.put(name, flight);
        return true;
    }
    
    public static boolean addReservation(ReservationEntity reservation){
        Long id = reservation.getId();
        if(reservationsById.containsKey(id)){
            return false;
        }
        reservationsById.put(id, reservation);
        return true;
    }
    
    public static DestinationEntity getDestinationById(Long id){
        return destinationsById.get(id);
    }
    
    public static FlightEntity getFlightById(Long id){
        return flightsById.get(id);
    }
    
    public static ReservationEntity getReservationById(Long id){
        return reservationsById.get(id);
    }
    
    public static AOSMemoryDBDestinationQueryset getAllDestinations(){
        return new AOSMemoryDBDestinationQueryset(destinationsById.values());
    }
    
    public static AOSMemoryDBFlightQueryset getAllFlights(Date from, Date to, int base, int offset){
        List<FlightEntity> flightsFiltered = filterByDate(from, to, flightsById.values());
        List<FlightEntity> flightsCutted = cutFlights(offset, flightsFiltered, base);
        return new AOSMemoryDBFlightQueryset(flightsCutted);
    }
    
    public static AOSMemoryDBReservationQueryset getAllReservations(){
        return new AOSMemoryDBReservationQueryset(reservationsById.values());
    }
   
    public static boolean removeDestinationByID(Long id){
        DestinationEntity remove = destinationsById.remove(id);
        if(remove == null){
            return false;
        }else{
            destinationsByName.remove(remove.getName());
            return true;
        }
    }
    
    public static boolean removeFlightByID(Long id){
        FlightEntity remove = flightsById.remove(id);
        if(remove == null){
            return false;
        }else{
            flightsByName.remove(remove.getName());
            return true;
        }
    }
    
    public static boolean removeReservationByID(Long id){
        ReservationEntity remove = reservationsById.remove(id);
        if(remove == null){
            return false;
        }else{
            return true;
        }
    }

    public static List<FlightEntity> filterByDate(Date from, Date to, Collection<FlightEntity> flightsOld) {
        List<FlightEntity> flightsFiltered = new ArrayList<FlightEntity>();
        for (FlightEntity flight : flightsById.values()) {
            if(from == null || to == null){
                return new AOSMemoryDBFlightQueryset(flightsById.values());
            }else{
                if(from.before(flight.getDateOfDeparture()) && to.after(flight.getDateOfDeparture())){
                    flightsFiltered.add(flight);
                }
            }
        }
        return flightsFiltered;
    }

    private static List<FlightEntity> cutFlights(int offset, List<FlightEntity> flightsFiltered, int base) {
         List<FlightEntity> flightsCutted = new ArrayList<FlightEntity>();
        for (int i = offset, num = 0; i < flightsFiltered.size()&& num < base; i++, num++) {
            FlightEntity flight = flightsFiltered.get(i);
            flightsCutted.add(flight);
        }
        return flightsCutted;
    }
    
}
