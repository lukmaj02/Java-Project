package com.Projekt.Bankomat.DtoModels;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String address;

    @Override
    public String toString() {
        return
                firstName + "," +
                lastName + "," +
                email + "," +
                password + "," +
                phoneNumber + "," +
                dateOfBirth + "," +
                address + ",";
    }
}
