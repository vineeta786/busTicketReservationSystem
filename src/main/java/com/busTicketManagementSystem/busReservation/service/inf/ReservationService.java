package com.busTicketManagementSystem.busReservation.service.inf;

import com.busTicketManagementSystem.busReservation.entity.Reservation;
import com.busTicketManagementSystem.busReservation.model.GeneralMetaDataResponse;

public interface ReservationService {
    GeneralMetaDataResponse reserveBus(Reservation reservationModel);
}
