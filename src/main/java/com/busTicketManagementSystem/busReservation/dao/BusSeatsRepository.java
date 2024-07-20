package com.busTicketManagementSystem.busReservation.dao;

import com.busTicketManagementSystem.busReservation.entity.BusSeats;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BusSeatsRepository extends JpaRepository<BusSeats, Integer> {

    @Query("SELECT BS FROM BusSeats BS WHERE BS.busNumber = :busNumber AND BS.date = :date")
    List<BusSeats> findSeatsByBus(String busNumber, String date);

    @Query("SELECT BS.seatNumber FROM BusSeats BS WHERE BS.isBooked = false " +
            "AND BS.busNumber = :busNumber")
    List<String> findUnbookedSeats(String busNumber);

    @Modifying
    @Transactional
    @Query("Update BusSeats SET isBooked = true WHERE busNumber = :busNumber " +
            "AND seatNumber = :seatNumber AND isBooked != true")
    int updateBookingStatus(String busNumber, String seatNumber);

}
