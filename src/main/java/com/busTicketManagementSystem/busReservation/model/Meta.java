package com.busTicketManagementSystem.busReservation.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Meta {

    private Boolean success = true;
    private final String transactionId = UUID.randomUUID().toString();
    private String messageDescription;
    private String unixTimestamp = String.valueOf(System.currentTimeMillis());
}
