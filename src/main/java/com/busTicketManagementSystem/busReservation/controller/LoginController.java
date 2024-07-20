package com.busTicketManagementSystem.busReservation.controller;

import com.busTicketManagementSystem.busReservation.entity.AppUser;
import com.busTicketManagementSystem.busReservation.model.GeneralMetaDataResponse;
import com.busTicketManagementSystem.busReservation.service.inf.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService loginService;

    @PostMapping("/create/user")
    public GeneralMetaDataResponse createUser(@Valid @RequestBody AppUser user) {
        logger.info("Creating user: {}", user);
        GeneralMetaDataResponse response = loginService.createUser(user);
        return response;
    }

    @GetMapping("/get/users")
    public GeneralMetaDataResponse getUsers() {
        logger.info("Getting users.");
        GeneralMetaDataResponse response = loginService.getUsers();
        return response;
    }


}
