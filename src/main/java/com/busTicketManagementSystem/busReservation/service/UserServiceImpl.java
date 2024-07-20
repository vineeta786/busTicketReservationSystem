package com.busTicketManagementSystem.busReservation.service;

import com.busTicketManagementSystem.busReservation.dao.ReservationRepository;
import com.busTicketManagementSystem.busReservation.dao.UserRepository;
import com.busTicketManagementSystem.busReservation.entity.AppUser;
import com.busTicketManagementSystem.busReservation.entity.Reservation;
import com.busTicketManagementSystem.busReservation.model.GeneralMetaDataResponse;
import com.busTicketManagementSystem.busReservation.model.Meta;
import com.busTicketManagementSystem.busReservation.model.UserReservationModel;
import com.busTicketManagementSystem.busReservation.service.inf.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository loginRepository;

    @Autowired
    ReservationRepository reservationRepository;

    public GeneralMetaDataResponse createUser(AppUser user) {
        GeneralMetaDataResponse response = validateUser(user);

        if (!response.getMeta().getSuccess()) {
            return response;
        }

        user.setUserName(user.getUserName().toLowerCase());
        loginRepository.save(user);
        return response;
    }

    private GeneralMetaDataResponse validateUser(AppUser user) {
        GeneralMetaDataResponse response = new GeneralMetaDataResponse();
        Meta meta = new Meta();
        if (loginRepository.findByUserName(user.getUserName()).size() != 0) {
            meta.setSuccess(false);
            meta.setMessageDescription("Username: "+ user.getUserName() + " already exists.");
            response.setMeta(meta);
            return response;
        }

        if (loginRepository.findByMobileNumber(user.getMobileNumber()).size() != 0) {
            meta.setSuccess(false);
            meta.setMessageDescription("Mobile number: "+ user.getMobileNumber() + " already in use. " +
                    "Try logging to your account.");
            response.setMeta(meta);
            return response;
        }

        if (loginRepository.findUsersByEmailId(user.getEmailId()).size() != 0) {
            meta.setSuccess(false);
            meta.setMessageDescription("Email id: "+ user.getEmailId() + " already in use. " +
                    "Try logging to your account.");
            response.setMeta(meta);
            return response;
        }
        response.setMeta(meta);
        return response;
    }

    public GeneralMetaDataResponse getUsers() {
        List<AppUser> users = loginRepository.findAll();
        users = modifyUserData(users);
        GeneralMetaDataResponse response = new GeneralMetaDataResponse();
        Meta meta = new Meta();
        response.setMeta(meta);
        response.setData(users);
        return response;
    }
    
    public List<AppUser> modifyUserData(List<AppUser> users) {
        List<AppUser> updatedUsers = new ArrayList<>();
        for (AppUser user: users) {
            user.setPassword("********");
            user.setMobileNumber(maskedMobileNumber(user.getMobileNumber()));
            user.setEmailId(maskedEmailId(user.getEmailId()));
            updatedUsers.add(user);
        }
        
        return updatedUsers;
    }

    private String maskedMobileNumber(String mobileNumber) {
        if (mobileNumber == null) {
            return null;
        }
        String mobileStr = String.valueOf(mobileNumber);
        int length = mobileStr.length();
        if (length < 3) {
            return mobileStr;
        }
        logger.info("Mobile: " + mobileNumber);
        logger.info("Masked Mobile: "+ "*".repeat(length - 3) + mobileStr.substring(length - 3));
        return "*".repeat(length - 3) + mobileStr.substring(length - 3);
    }


    public String maskedEmailId(String emailId) {
        if (emailId == null || !emailId.contains("@")) {
            return null;
        }
        String[] parts = emailId.split("@");
        String localPart = parts[0];
        String domainPart = parts[1];

        if (localPart.length() < 2) {
            return emailId;
        }

        logger.info("Mail: "+ emailId);
        logger.info("Masked mail: "+ localPart.charAt(0) + "*".repeat(localPart.length() - 1) + "@" + domainPart);
        return localPart.charAt(0) + "*".repeat(localPart.length() - 1) + "@" + domainPart;
    }

    @Override
    public GeneralMetaDataResponse getUserReservations(String userName) {
        GeneralMetaDataResponse response = new GeneralMetaDataResponse();
        Meta meta = new Meta();

        List<Reservation> reservationsForUser = reservationRepository.findByUserName(userName);
        logger.info("Reservation List: " + reservationsForUser.size());
        if (reservationsForUser.isEmpty()) {
            meta.setMessageDescription("No reservations present.");
            response.setMeta(meta);
            return response;
        }

        List<UserReservationModel> userReservationData = new ArrayList<>();
        for (Reservation reservation: reservationsForUser) {
            UserReservationModel userReservationModel = new UserReservationModel();
            userReservationModel.setUserName(reservation.getUserName());
            userReservationModel.setBusNumber(reservation.getBusNumber());
            userReservationModel.setSourceLocation(reservation.getBus().getSource());
            userReservationModel.setDestinationLocation(reservation.getBus().getDestination());
            userReservationModel.setSeatsBooked(reservation.getSeatsToBeBooked());
            userReservationModel.setPrice(reservation.getBus().getPrice());
            userReservationModel.setBookingDate(reservation.getReservationDate());
            userReservationModel.setTotalPrice(reservation.getBus().getPrice() * reservation.getSeatsToBeBooked());
            userReservationData.add(userReservationModel);
        }

        response.setMeta(meta);
        response.setData(userReservationData);
        return response;
    }



}
