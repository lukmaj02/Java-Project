package com.Projekt.Bankomat.Controllers;


import com.Projekt.Bankomat.DtoModels.RegistrarionRequest;
import com.Projekt.Bankomat.Models.KontoBankowe;
import com.Projekt.Bankomat.Models.Uzytkownik;
import com.Projekt.Bankomat.Service.IUzytkownikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

@RestController
@RequestMapping("/server")
public class UzytkownikController {

    private final IUzytkownikService _service;

    @Autowired
    public UzytkownikController(IUzytkownikService service) {
        _service = service;
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public Optional<Uzytkownik> GetUzytkownik(@RequestParam String email)
    {
        var user =  _service.wyswietlDaneUzytkownika(email);
        return user;
    }
    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public Optional<List<Uzytkownik>> GetAllUzytkownik()
    {
        var users =  _service.wyswietlDaneWszytskichUzytkownikow();
        return users;
    }
    @RequestMapping(value = "/getAllBankAccounts",method = RequestMethod.GET)
    public Optional<Set<KontoBankowe>> GetAllkontaBankoweUzytkownika(@RequestParam String email)
    {
        var konta =  _service.wyswietlKontaBankoweUzytkownika(email);
        return Optional.ofNullable(konta);
    }

    @PostMapping(value = "/registerUser")
    public void GetAllkontaBankoweUzytkownika(@RequestBody RegistrarionRequest request)
    {
        _service.zarejestrujUzytkownika(request);
    }

    //fix this method

    @DeleteMapping("/deleteUser/{email}")
    @ResponseBody
    public void DeleteUser(@PathVariable String email)
    {
        _service.usunUzytkownika(email);
    }

}
