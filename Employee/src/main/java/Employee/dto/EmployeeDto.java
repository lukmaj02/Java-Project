package Employee.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDto {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phoneNumber;
    private String dateOfBirth;
    private String address;
    private String city;
    private String gender;
    private String maritalStatus;
    private String role;

    public static EmployeeDto mapper(String data){
        var splitedData = data.split(",");
        return EmployeeDto.builder()
                .firstname(splitedData[0])
                .lastname(splitedData[1])
                .email(splitedData[2])
                .password(splitedData[3])
                .dateOfBirth(splitedData[4])
                .gender(splitedData[5])
                .phoneNumber(splitedData[6])
                .address(splitedData[7])
                .city(splitedData[8])
                .maritalStatus(splitedData[9])
                .role(splitedData[10])
                .build();
    }
}
