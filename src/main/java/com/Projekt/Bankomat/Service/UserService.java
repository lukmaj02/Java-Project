package com.Projekt.Bankomat.Service;

import com.Projekt.Bankomat.DtoModels.UserDto;
import com.Projekt.Bankomat.Exceptions.BadCredentialsException;
import com.Projekt.Bankomat.Exceptions.BankAccountExistsException;
import com.Projekt.Bankomat.Exceptions.UserExistsException;
import com.Projekt.Bankomat.Exceptions.UserNotFoundException;
import com.Projekt.Bankomat.IService.IAdminService;
import com.Projekt.Bankomat.IService.IUserService;
import com.Projekt.Bankomat.Models.BankAccount;
import com.Projekt.Bankomat.Models.User;
import com.Projekt.Bankomat.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService implements IUserService, IAdminService {
    private final UserRepo userRepo;
    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User getUser(String email){
        return userRepo.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }


    public void editUserInformations(UserDto userDto){
        var uzytkownik = userRepo.findByEmail(userDto.getEmail()).orElseThrow(UserNotFoundException::new);
        uzytkownik.setFirstName(userDto.getFirstName());
        uzytkownik.setLastName(userDto.getLastName());
        uzytkownik.setAddress(userDto.getAddress());
        uzytkownik.setCity(userDto.getCity());
        uzytkownik.setPhoneNumber(userDto.getPhoneNumber());
        uzytkownik.setMaritalStatus(userDto.getMaritalStatus());
        userRepo.save(uzytkownik);
    }
    public Set<BankAccount> getUserBankAccounts(String email){
        var uzytkownik = userRepo.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return uzytkownik.getBankAccount();
    }

    public void registerUser(UserDto registraionRequest){
        if(userRepo.existsByEmail(registraionRequest.getEmail()) || userRepo.existsByPhoneNumber(registraionRequest.getPhoneNumber())){
            throw new UserExistsException(registraionRequest.getEmail(), registraionRequest.getPhoneNumber());
        }
        var uzytkownik = User.builder()
                .userId(UUID.randomUUID().toString())
                .address(registraionRequest.getAddress())
                .dateOfBirth(registraionRequest.getDateOfBirth())
                .firstName(registraionRequest.getFirstName())
                .lastName(registraionRequest.getLastName())
                .password(registraionRequest.getPassword())
                .email(registraionRequest.getEmail())
                .phoneNumber(registraionRequest.getPhoneNumber())
                .city(registraionRequest.getCity())
                .role(registraionRequest.getRole())
                .gender(registraionRequest.getGender())
                .maritalStatus(registraionRequest.getMaritalStatus())
                .build();
        userRepo.save(uzytkownik);
    }
    public void deleteUser(String email) {
        var uzytkownik = userRepo.findByEmail(email).orElseThrow(UserNotFoundException::new);
        if(!uzytkownik.getBankAccount().isEmpty()){
            throw new BankAccountExistsException();
        }
        userRepo.delete(uzytkownik);
    }

    @Override
    public User login(String email, String password) {
        return userRepo.findByEmailAndPassword(email, password)
                .orElseThrow(BadCredentialsException::new);
    }
}
