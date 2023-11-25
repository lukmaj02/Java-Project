package com.Projekt.Bankomat.Service;

import com.Projekt.Bankomat.Exceptions.BankAccountNotFoundException;
import com.Projekt.Bankomat.Models.User;

import java.util.List;

public interface IAdminService extends IUserService{
    User getUser(String email);
    List<User> getAllUsers();
}
