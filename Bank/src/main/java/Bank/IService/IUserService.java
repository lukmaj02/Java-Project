package Bank.IService;

import Bank.DtoModels.UserDto;
import Bank.Models.BankAccount;
import Bank.Models.User;

import java.util.Set;

public interface IUserService {
    void editUserInformations(UserDto userDto);
    Set<BankAccount> getUserBankAccounts(String email);
    void registerUser(UserDto userDto);
    User login(String email, String password);
    void deleteUser(String email);
}
