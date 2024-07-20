package com.busTicketManagementSystem.busReservation.dao;

import com.busTicketManagementSystem.busReservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query("SELECT r FROM Reservation r WHERE r.userName = :userName")
    List<Reservation> findByUserName(String userName);

}
