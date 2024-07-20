package com.busTicketManagementSystem.busReservation.dao;

import com.busTicketManagementSystem.busReservation.entity.AppUser;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {

    @Query("SELECT U FROM AppUser U WHERE U.userName = :userName")
    List<AppUser> findByUserName(String userName);

    @Query("SELECT u FROM AppUser u WHERE u.emailId = :emailId")
    List<AppUser> findUsersByEmailId(@Param("emailId") String emailId);

    @Query("SELECT u FROM AppUser u WHERE u.mobileNumber = :mobileNumber")
    List<AppUser> findByMobileNumber(String mobileNumber);

}
