package com.busTicketManagementSystem.busReservation.controller;

import com.busTicketManagementSystem.busReservation.model.GeneralMetaDataResponse;
import com.busTicketManagementSystem.busReservation.model.UserReservationRequest;
import com.busTicketManagementSystem.busReservation.service.inf.ReservationService;
import com.busTicketManagementSystem.busReservation.service.inf.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    ReservationService reservationService;

    @Autowired
    UserService userService;

    @PostMapping("/reserve/bus")
    public GeneralMetaDataResponse reserveBus(@RequestBody UserReservationRequest reservationModel) {
        logger.info("Reserving bus based on details: "+ reservationModel);
        GeneralMetaDataResponse response = reservationService.reserveBus(reservationModel);
        return response;
    }

    @GetMapping("/get-reservation/user/{userName}/reservations")
    public GeneralMetaDataResponse getUserReservations(@PathVariable String userName) {
        logger.info("Fetching reservations for user id: "+ userName);
        GeneralMetaDataResponse response = userService.getUserReservations(userName);
        return response;
    }


}
