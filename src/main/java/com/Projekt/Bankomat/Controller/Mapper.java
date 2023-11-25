package com.Projekt.Bankomat.Controller;

import com.Projekt.Bankomat.DtoModels.TransactionDto;
import com.Projekt.Bankomat.DtoModels.UserDto;
import com.Projekt.Bankomat.Enums.CurrencyType;
import com.Projekt.Bankomat.Enums.TransactionType;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class Mapper {
    private Mapper(){}

    public static String[] toLogin(String data) {
        return data.split(",",2);
    }

    public static UserDto toUserDto(String data){
        var splitedData = data.split(",", 7);
        return UserDto.builder()
                .firstName(splitedData[0])
                .lastName(splitedData[1])
                .email(splitedData[2])
                .password(splitedData[3])
                .phoneNumber(splitedData[4])
                .dateOfBirth(LocalDate.parse(splitedData[5]))
                .address(splitedData[6])
                .build();
    }

    public static TransactionDto toTransactionDto(String data){
        var splitedData = data.split(",",6);
        return TransactionDto.builder()
                .fromAccountNr(splitedData[0])
                .toAccountNr(splitedData[1])
                .amount(new BigDecimal(splitedData[2]))
                .title(splitedData[3])
                .currencyType(CurrencyType.valueOf(splitedData[4]))
                .transactionType(TransactionType.valueOf(splitedData[5]))
                .build();
    }
}
