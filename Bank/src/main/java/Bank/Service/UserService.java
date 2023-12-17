package Bank.Service;

import Bank.DtoModels.UserDto;
import Bank.Exceptions.BadCredentialsException;
import Bank.Exceptions.BankAccountExistsException;
import Bank.Exceptions.UserExistsException;
import Bank.Exceptions.UserNotFoundException;
import Bank.IService.IEmployeeService;
import Bank.IService.IUserService;
import Bank.Models.BankAccount;
import Bank.Models.User;
import Bank.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class UserService implements IUserService, IEmployeeService {
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
        if(!Objects.equals(uzytkownik.getPhoneNumber(), userDto.getPhoneNumber())
                && userRepo.existsByPhoneNumber(userDto.getPhoneNumber())) {
            throw new UserExistsException(userDto.getPhoneNumber());
        }
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
        if(userRepo.existsByEmailOrPhoneNumber(registraionRequest.getEmail(), registraionRequest.getPhoneNumber())){
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
