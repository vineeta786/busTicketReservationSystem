package com.busTicketManagementSystem.busReservation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "User Name is mandatory.")
    private String userName;

    @NotBlank(message = "Password is mandatory.")
    // Consider storing the password in an encrypted format
    private String password;

    @NotNull(message = "Sex data is mandatory.")
    private Sex sex;

    @NotNull(message = "Mobile number is mandatory.")
    @Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number format.")
    private String mobileNumber;

    @NotBlank(message = "Email id is mandatory.")
    @Email(message = "Invalid email format.")
    private String emailId;

    public enum Sex {
        Male, Female, Other
    }

}
