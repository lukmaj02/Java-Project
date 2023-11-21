package com.Projekt.Bankomat.DtoModels;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
private String firstName;
private String lastName;
private String email;
private String password;
private String phoneNumber;
private String address;
}
