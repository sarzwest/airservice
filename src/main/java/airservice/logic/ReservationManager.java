/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airservice.logic;

import airservice.db.AOSMemoryDB;
import airservice.entity.flight.FlightEntity;
import airservice.entity.reservation.ReservationEntity;
import airservice.entity.StateChoices;
import airservice.entity.creditcard.CreditCard;
import airservice.entity.reservation.ReservationInput;
import airservice.exceptions.BusinessLogicException;
import airservice.entity.reservation.ReservationOutput;
import airservice.exceptions.SystemException;
import airservice.qualifiers.SecuredClient;
import airservice.util.Mapper;
import airservice.util.Properties;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;
//import org.centralbank.client.SecuredBankClient;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
public class ReservationManager {

    @Inject
    TimerBean timer;
//    @Inject
//    @SecuredClient
//    SecuredBankClient bankClient;

    public ReservationManager() {
    }

    public void createReservation(ReservationEntity reservation) throws BusinessLogicException {
        reservation.getFlight().reserveSeats(reservation.getSeats());
        boolean ret = AOSMemoryDB.addReservation(reservation);
        if (!ret) {
            throw new BusinessLogicException("Reservation with id: " + reservation.getId() + "already exists in database.");
        }
        timer.startTimer(reservation.getId());
    }

    public Collection<ReservationEntity> getAllReservationsInFlight(Long id) {
        Collection<ReservationEntity> cr = new ArrayList<ReservationEntity>();
        for (ReservationEntity reservation : AOSMemoryDB.getAllReservations()) {
            if (reservation.getFlight().getId() == id) {
                cr.add(reservation);
            }
        }
        return cr;
    }

    public void updateReservation(ReservationInput reservationToUpdate, ReservationEntity reservation) throws BusinessLogicException {
        FlightEntity flight = reservation.getFlight();
        int seatsReserved = flight.getSeatsReserved();
        if (seatsReserved - reservation.getSeats() + reservationToUpdate.getSeats() > flight.getSeats()) {
            throw new BusinessLogicException("Cannot update seats, capacity of flight is full.");
        }
        if (reservationToUpdate.getState().equals("CANCELED")
                && reservation.getState() == StateChoices.PAID) {
            throw new BusinessLogicException("Cannot cancel already paid reservation");
        }
        flight.deleteSeats(reservation.getSeats());
        flight.reserveSeats(reservationToUpdate.getSeats());
        reservation.setSeats(reservationToUpdate.getSeats());
        reservation.setState(Mapper.getState(reservationToUpdate.getState()));
    }

    public ReservationEntity getReservationById(Long id) throws BusinessLogicException {
        ReservationEntity reservationById = AOSMemoryDB.getReservationById(id);
        if (reservationById == null) {
            throw new BusinessLogicException("Cant find reservation, because reservation with such id doesnt exist.");
        }
        return reservationById;
    }

    public String checkPassword(String check) {
        if (check == null) {
            check = generatePassword();
        }
        return check;
    }

    public String generatePassword() {
        char[] passwordChar = new char[20];
        for (int i = 0; i < passwordChar.length; i++) {
            byte b = (byte) (Math.random() * 122);
            while (b < 65 || (b > 90 && b < 97) || b > 122) {
                b = (byte) (Math.random() * 122);
            }
            passwordChar[i] = (char) b;
        }
        String pass = new String(passwordChar);
        return pass;
    }

    public void removeReservationById(Long id) throws BusinessLogicException {
        ReservationEntity reservationById = getReservationById(id);
        if (reservationById.getState() == StateChoices.PAID) {
            throw new BusinessLogicException("Cannot delete reservation in PAID state.");
        }
        AOSMemoryDB.removeReservationByID(reservationById.getId());
    }

    public void payReservation(ReservationEntity reservation, CreditCard card) throws BusinessLogicException, SystemException {
        if (reservation.getState() == StateChoices.PAID) {
            throw new BusinessLogicException("Cannot pay already paid reservation");
        }
        if (reservation.getState() == StateChoices.CANCELED) {
            throw new BusinessLogicException("Cannot pay canceled reservation");
        }
        if (reservation.getState() == StateChoices.NEW) {
            //pay the reservation - nemusi byt na checkpoint 2
//            bankClient.pay(card.getNumber(), Properties.AIRSERVICE_BANK_ACCOUNT, (long) reservation.getFlight().getPrice(), "Payment for flight name: " + reservation.getFlight().getName());
//            reservation.setState(StateChoices.PAID);
        } else {
            throw new SystemException("Reservation is in unexpected state: " + reservation.getState());
        }
    }
}
