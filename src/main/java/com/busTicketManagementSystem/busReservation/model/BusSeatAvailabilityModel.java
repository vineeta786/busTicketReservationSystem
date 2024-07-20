package com.busTicketManagementSystem.busReservation.model;

import com.busTicketManagementSystem.busReservation.entity.BusSeats;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BusSeatAvailabilityModel {

    private String busNumber;
    private String source;
    private String destination;
    private Integer seatsAvailable;
    private Integer price;
    private String date;
    private List<BusSeats> busSeats;

}
