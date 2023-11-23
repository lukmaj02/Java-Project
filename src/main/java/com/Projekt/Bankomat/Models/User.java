package com.Projekt.Bankomat.Models;

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

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<BankAccount> bankAccount;

    @Override
    public String toString() {
        return firstName +","+
                lastName + "," +
                email + "," +
                password + "," +
                dateOfBirth + "," +
                phoneNumber + "," +
                address;
    }
}
