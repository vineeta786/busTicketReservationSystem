package com.busTicketManagementSystem.busReservation.service.inf;

import com.busTicketManagementSystem.busReservation.entity.AppUser;
import com.busTicketManagementSystem.busReservation.entity.Reservation;
import com.busTicketManagementSystem.busReservation.model.GeneralMetaDataResponse;

import java.util.List;

public interface UserService {

    GeneralMetaDataResponse createUser(AppUser user);

    GeneralMetaDataResponse getUsers();

    GeneralMetaDataResponse getUserReservations(String userName);
}
