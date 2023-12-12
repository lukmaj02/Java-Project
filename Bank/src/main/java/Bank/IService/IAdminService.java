package Bank.IService;

import Bank.Models.User;

import java.util.List;

public interface IAdminService {
    User getUser(String email);
    List<User> getAllUsers();
}
