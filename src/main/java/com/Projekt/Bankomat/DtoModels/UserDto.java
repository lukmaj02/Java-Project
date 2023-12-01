package com.Projekt.Bankomat.DtoModels;

import com.Projekt.Bankomat.Enums.Gender;
import com.Projekt.Bankomat.Enums.MaritalStatus;
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
    private String city;
    private Gender gender;
    private MaritalStatus maritalStatus;

    @Override
    public String toString() {
        return
                firstName + "," +
                lastName + "," +
                email + "," +
                password + "," +
                phoneNumber + "," +
                dateOfBirth + "," +
                address + "," +
                city + "," +
                gender.toString() + "," +
                maritalStatus.toString() + ",";
    }
}
