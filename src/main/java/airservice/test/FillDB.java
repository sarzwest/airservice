/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airservice.test;

import airservice.db.AOSMemoryDB;
import airservice.entity.destination.Destination;
import airservice.entity.destination.DestinationEntity;
import airservice.entity.flight.FlightEntity;
import airservice.entity.flight.FlightInput;
import airservice.exceptions.SystemException;
import airservice.restclient.Destination.DestinationClientGoogle;
import airservice.restclient.Destination.DestinationInfoDTO;
import airservice.restclient.Destination.DestinationInfoDTO.Location;
import airservice.restclient.Destination.DistanceClientAOS;
import airservice.util.Mapper;
import airservice.util.Properties;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
public class FillDB implements ServletContextListener{
    
    public static void fillDB() throws ParseException, SystemException{
        DestinationClientGoogle destGoogle = new DestinationClientGoogle();
        DistanceClientAOS distAOS = new DistanceClientAOS();
        String[] cities = new String[]{"Praha", "Prostejov", "Brno", "Olomouc", 
            "Zlin", "New York", "Washington", "Jesenik", "Most", "Chrudim"};
        for (int i = 0; i < 10; i++) {
            DestinationEntity destination = new DestinationEntity(cities[i]);
            Location location = destGoogle.getLocation(cities[i]);
            destination.setLocation(location);
            AOSMemoryDB.addDestination(destination);
        }
        for (int i = 0; i < 5; i++) {
            GregorianCalendar calendar = new GregorianCalendar(2013, (i * 7) % 12 + 1, i % 29);
            Date date = calendar.getTime();
            FlightInput fi = new FlightInput(i + 10 * 50, "CSA " + i, i*1000, 100, new SimpleDateFormat(Properties.DATE_FORMAT).format(date), i+1, i+5);
            FlightEntity flightEntity = new FlightEntity(fi);
            float distance = distAOS.getDistance(flightEntity.getFrom().getLocation(), flightEntity.getTo().getLocation());
            flightEntity.setDistance(distance);
            AOSMemoryDB.addFlight(flightEntity);
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            fillDB();
        } catch (ParseException ex) {
            Logger.getLogger(FillDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(FillDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
    
}
