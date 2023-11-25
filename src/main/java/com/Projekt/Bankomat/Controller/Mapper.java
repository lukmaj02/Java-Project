package com.Projekt.Bankomat.Controller;

import com.Projekt.Bankomat.DtoModels.RegistrarionRequest;

import java.time.LocalDate;

public class Mapper {
    private Mapper(){}

    public static String[] toLogin(String data) {
        return data.split(",",2);
    }

    public static RegistrarionRequest toRegister(String data){
        var splitedData = data.split(",", 7);
        return RegistrarionRequest.builder()
                .firstName(splitedData[0])
                .lastName(splitedData[1])
                .email(splitedData[2])
                .password(splitedData[3])
                .phoneNumber(splitedData[4])
                .dateOfBirth(LocalDate.parse(splitedData[5]))
                .address(splitedData[6])
                .build();
    }


}
