package larentina.isecutiry1.dto;

import lombok.Data;

@Data
public class RegisterRequestDto {
    private String username;
    private String password;
    private String fullName;
    private String email;
}