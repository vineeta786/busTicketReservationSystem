package com.busTicketManagementSystem.busReservation.model;

import lombok.*;

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

}
