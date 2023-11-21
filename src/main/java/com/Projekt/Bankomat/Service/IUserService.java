package com.Projekt.Bankomat.Service;

import com.Projekt.Bankomat.DtoModels.RegistrarionRequest;
import com.Projekt.Bankomat.DtoModels.UserDto;
import com.Projekt.Bankomat.Models.BankAccount;
import com.Projekt.Bankomat.Models.User;

import java.util.List;
import java.util.Set;

public interface IUserService {
    User getUser(String email);
    List<User> getAllUsers();
    void editUserInformations(String email, UserDto userDto);
    Set<BankAccount> getUserBankAccounts(String email);
    void registerUser(RegistrarionRequest registrarionRequest);
    void deleteUser(String email);
}
