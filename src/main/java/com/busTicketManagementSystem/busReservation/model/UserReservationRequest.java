package com.busTicketManagementSystem.busReservation.model;

import com.busTicketManagementSystem.busReservation.entity.BusSeats;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserReservationRequest {

    @NotNull(message = "Bus number is mandatory.")
    private String busNumber;

    @NotBlank(message = "User name is mandatory.")
    private String userName;

    @NotNull(message = "Reservation date is mandatory.")
    private String reservationDate;

    private Integer seatsToBeBooked = 1;

    private List<String> preferredSeats;

    private Boolean isBookingToBeDoneIfPrefferedSeatsUnavailable;
}
