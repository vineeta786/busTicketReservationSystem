package com.busTicketManagementSystem.busReservation.service;

import com.busTicketManagementSystem.busReservation.dao.BusRepository;
import com.busTicketManagementSystem.busReservation.dao.ReservationRepository;
import com.busTicketManagementSystem.busReservation.dao.UserRepository;
import com.busTicketManagementSystem.busReservation.entity.AppUser;
import com.busTicketManagementSystem.busReservation.entity.Bus;
import com.busTicketManagementSystem.busReservation.entity.Reservation;
import com.busTicketManagementSystem.busReservation.model.GeneralMetaDataResponse;
import com.busTicketManagementSystem.busReservation.model.Meta;
import com.busTicketManagementSystem.busReservation.service.inf.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BusRepository busRepository;

    @Autowired
    ReservationRepository reservationRepository;

    Logger logger = LoggerFactory.getLogger(ReservationService.class);

    public GeneralMetaDataResponse reserveBus(Reservation reservation) {
        GeneralMetaDataResponse response = new GeneralMetaDataResponse();
        Meta meta = new Meta();

        List<AppUser> user = userRepository.findByUserName(reservation.getUserName());
        if (user.isEmpty()) {
            meta.setSuccess(false);
            meta.setMessageDescription("User not found!");
            response.setMeta(meta);
            return response;
        }
        AppUser bookingUser = user.get(0);

        List<Bus> bus = busRepository.findBusByBusNumberAndDate(reservation.getBusNumber(),
                reservation.getReservationDate());
        if (bus.isEmpty()) {
            meta.setSuccess(false);
            meta.setMessageDescription("Bus not found!");
            response.setMeta(meta);
            return response;
        }
        Bus bookingBus = bus.get(0);

        Integer numberOfSeatsToBeBooked = reservation.getSeatsToBeBooked();
        logger.info("Seats to be booked: " + numberOfSeatsToBeBooked + " Available seats: "+ bookingBus.getAvailableSeats());
        if (numberOfSeatsToBeBooked <= 0) {
            throw new IllegalArgumentException("Number of seats to be booked must be greater than zero");
        }
        if (numberOfSeatsToBeBooked > bookingBus.getAvailableSeats()) {
            throw new IllegalArgumentException("Not enough seats available on the bus");
        }

        bookingBus.setAvailableSeats(bookingBus.getAvailableSeats() - numberOfSeatsToBeBooked);
        busRepository.save(bookingBus);

        reservation.setUser(bookingUser);
        reservation.setBus(bookingBus);
        reservation.setReservationDate(reservation.getReservationDate());

        reservationRepository.save(reservation);
        meta.setMessageDescription("Reservation completed successfully!");
        response.setMeta(meta);
        return response;
    }
}
