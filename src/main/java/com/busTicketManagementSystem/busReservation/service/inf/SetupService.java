package com.busTicketManagementSystem.busReservation.service.inf;

import com.busTicketManagementSystem.busReservation.entity.Bus;
import com.busTicketManagementSystem.busReservation.model.GeneralMetaDataResponse;

public interface SetupService {
    
    GeneralMetaDataResponse saveBus(Bus bus);

    GeneralMetaDataResponse findBusesBySourceDestinationAndDate(String sourceLocation, String destinationLocation, String date);

    GeneralMetaDataResponse getAvailableSeatsForBus(String busNumber);

    GeneralMetaDataResponse getAvailableSeatsForBusOnDate(String busNumber, String date);
}
