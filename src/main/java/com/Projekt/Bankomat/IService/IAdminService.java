package com.Projekt.Bankomat.IService;

import com.Projekt.Bankomat.Models.User;

import java.util.List;

public interface IAdminService {
    User getUser(String email);
    List<User> getAllUsers();
}
