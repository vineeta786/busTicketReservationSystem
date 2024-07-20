package com.busTicketManagementSystem.busReservation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class BusSeats {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Bus Number is mandatory.")
    private String busNumber;

    @NotBlank(message = "Seat number is mandatory")
    private String seatNumber;

    private String seatType = SeatType.SEATER.toString();

    @NotNull(message = "Price is mandatory")
    private Integer price;

    private String userId;

    private Boolean isBooked = false;

    private String date;

    public enum SeatType {
        SLEEPER, SEATER
    }
}
