package com.Projekt.Bankomat.Models;

import com.Projekt.Bankomat.Enums.Gender;
import com.Projekt.Bankomat.Enums.MaritalStatus;
import com.Projekt.Bankomat.Enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "_USER")
public class User {
    @Id
    @UuidGenerator
    @Column(name = "userId", nullable = false)
    private String userId;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "dateOfBirth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "phoneNumber", nullable = false, length = 9)
    private String phoneNumber;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "address",nullable = false)
    private String address;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "maritalStatus", nullable = false)
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<BankAccount> bankAccount;

    @Override
    public String toString() {
        return firstName +","+
                lastName + "," +
                email + "," +
                password + "," +
                dateOfBirth + "," +
                gender.toString() + "," +
                phoneNumber + "," +
                address + "," +
                city + "," +
                maritalStatus.toString() + ",";
    }

}
