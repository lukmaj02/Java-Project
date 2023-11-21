package com.Projekt.Bankomat.Controllers;


import com.Projekt.Bankomat.DtoModels.RegistrarionRequest;
import com.Projekt.Bankomat.Models.BankAccount;
import com.Projekt.Bankomat.Models.User;
import com.Projekt.Bankomat.Service.IUzytkownikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/server")
public class UzytkownikController {

    private final IUzytkownikService _service;

    @Autowired
    public UzytkownikController(IUzytkownikService service) {
        _service = service;
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public User getUser(@RequestParam String email)
    {
        return _service.getUser(email);
    }
    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public List<User> getAllUsers()
    {
        return _service.getAllUsers();
    }

    @RequestMapping(value = "/getAllBankAccounts",method = RequestMethod.GET)
    public Optional<Set<BankAccount>> getAllUserBankAccounts(@RequestParam String email)
    {
        var konta =  _service.getUserBankAccounts(email);
        return Optional.ofNullable(konta);
    }

    @PostMapping(value = "/registerUser")
    public void registerUser(@RequestBody RegistrarionRequest request)
    {
        _service.registerUser(request);
    }

    //fix this method

    @DeleteMapping("/deleteUser/{email}")
    @ResponseBody
    public void deleteUser (@PathVariable String email)
    {
        _service.deleteUser(email);
    }

}
