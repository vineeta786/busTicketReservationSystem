package com.busTicketManagementSystem.busReservation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Bus Number is a mandatory field.")
    private String busNumber;

    @NotBlank(message = "Source of bus is mandatory.")
    private String source;

    @NotBlank(message = "Source departure time can't be empty.")
    private String sourceDepartureTime;

    @NotBlank(message = "Destination of bus is mandatory.")
    private String destination;

    @NotBlank(message = "Destination arrival time is mandatory.")
    private String destinationArrivalTime;

    @NotNull(message = "Capacity of bus is mandatory.")
    private int capacity;

    private int availableSeats;

    @NotBlank(message = "Date is mandatory.")
    private String date;

    private Integer price;

//    @OneToMany(mappedBy = "bus")
//    private List<Reservation> reservations;

    public int setAvailableSeats(int newAvailableSeats) {
        return this.availableSeats = newAvailableSeats;
    }


}
