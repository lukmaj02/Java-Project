package com.Projekt.Bankomat.Service;

import com.Projekt.Bankomat.DtoModels.UserDto;
import com.Projekt.Bankomat.Models.BankAccount;
import com.Projekt.Bankomat.Models.User;

import java.util.Set;

public interface IUserService {
    void editUserInformations(UserDto userDto);
    Set<BankAccount> getUserBankAccounts(String email);
    void registerUser(UserDto userDto);
    User login(String email, String password);
    void deleteUser(String email);
}
