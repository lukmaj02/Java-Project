package Client.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String firstname;
    private String lastname;
    private String address;
    private String city;
    private String email;
    private String dateOfBirth;
    private String phoneNumber;
    private String password;
    private String gender;
    private String maritalStatus;

    public static UserDto mapper(String data){
        var splitedData = data.split(",");
        return UserDto.builder()
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
                .build();
    }
}
