package com.busTicketManagementSystem.busReservation.controller;

import com.busTicketManagementSystem.busReservation.entity.Bus;
import com.busTicketManagementSystem.busReservation.model.GeneralMetaDataResponse;
import com.busTicketManagementSystem.busReservation.service.inf.SetupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BusController {

    Logger logger = LoggerFactory.getLogger(BusController.class);

    @Autowired
    SetupService setupService;

    @PostMapping("/add/bus")
    public GeneralMetaDataResponse addBus(@RequestBody Bus bus) {
        logger.info("Adding bus: "+ bus);
        GeneralMetaDataResponse response = setupService.saveBus(bus);
        return response;
    }

    @GetMapping("/buses/source/{sourceLocation}/destination/{destinationLocation}/date/{date}")
    public GeneralMetaDataResponse getBusesBySourceAndDestination(
            @PathVariable String sourceLocation,
            @PathVariable String destinationLocation,
            @PathVariable String date) {
        logger.info("Getting bus for source: "+ sourceLocation + " destination: "+
                destinationLocation + " on date: "+ date );
        return setupService.findBusesBySourceDestinationAndDate(sourceLocation, destinationLocation, date);
    }

    @GetMapping("/bus/{busNumber}/availableSeats")
    public GeneralMetaDataResponse getAvailableSeats(@PathVariable String busNumber) {
        logger.info("Getting available seats for bus: "+ busNumber );
        return setupService.getAvailableSeatsForBus(busNumber);
    }

}
