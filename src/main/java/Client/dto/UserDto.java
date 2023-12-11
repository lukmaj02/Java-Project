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
    private String email;
    private String password;
    private String phoneNumber;
    private String dateOfBirth;
    private String address;
    private String city;
    private String gender;
    private String maritalStatus;

    public static UserDto mapper(String data){
        var splitedData = data.split(",");
        return UserDto.builder()
                .firstname(splitedData[0])
                .lastname(splitedData[1])
                .email(splitedData[2])
                .password(splitedData[3])
                .phoneNumber(splitedData[4])
                .dateOfBirth(splitedData[5])
                .address(splitedData[6])
                .city(splitedData[7])
                .gender(splitedData[8])
                .maritalStatus(splitedData[9])
                .build();
    }
}
