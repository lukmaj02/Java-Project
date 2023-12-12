package Bank.DtoModels;

import Bank.Enums.Gender;
import Bank.Enums.MaritalStatus;
import Bank.Enums.Role;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String address;
    private String city;
    private Gender gender;
    private MaritalStatus maritalStatus;
    private Role role;

    @Override
    public String toString() {
        return
                firstName + "," +
                lastName + "," +
                email + "," +
                password + "," +
                phoneNumber + "," +
                dateOfBirth + "," +
                address + "," +
                city + "," +
                gender.toString() + "," +
                maritalStatus.toString() + ",";
    }
}
