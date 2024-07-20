package com.busTicketManagementSystem.busReservation.dao;

import com.busTicketManagementSystem.busReservation.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus, Integer> {

    @Query("select b from Bus b where b.source = :sourceLocation and " +
            "b.destination = :destinationLocation and b.date = :date")
    List<Bus> findBySourceDestinationDate(String sourceLocation, String destinationLocation, String date);

    @Query("select b from Bus b where b.busNumber = :busNumber AND b.date = :date")
    List<Bus> findBusByBusNumberAndDate(String busNumber, String date);

    @Query("select b from Bus b where b.busNumber = :busNumber")
    List<Bus> findBusByBusNumber(String busNumber);

    @Query("SELECT b from Bus b")
    List<Bus> fetchAll();
}
