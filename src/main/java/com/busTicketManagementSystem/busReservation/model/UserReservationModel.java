package com.busTicketManagementSystem.busReservation.model;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserReservationModel {

    private String userName;
    private String busNumber;
    private String sourceLocation;
    private String destinationLocation;
    private Integer seatsBooked;
    private Integer price;
    private Integer totalPrice;
    private String bookingDate;


}
