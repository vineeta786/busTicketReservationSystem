package com.busTicketManagementSystem.busReservation.service.inf;

import com.busTicketManagementSystem.busReservation.entity.Reservation;
import com.busTicketManagementSystem.busReservation.model.GeneralMetaDataResponse;
import com.busTicketManagementSystem.busReservation.model.UserReservationRequest;

public interface ReservationService {
    GeneralMetaDataResponse reserveBus(UserReservationRequest reservationModel);
}
