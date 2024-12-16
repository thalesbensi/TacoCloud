package tacos.security;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import tacos.entities.User;

@Data
public class RegistrationForm {

    @NotBlank(message = "Username is required")
    private String username;

    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$",
            message = "Must have minimum 8 characters in length, One Uppercase and at least one digit ")
    @NotBlank(message = "Password required")
    private String password;

    private String fullname;

    private String street;

    private String city;

    private String state;

    private String zip;

    private String phone;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username, passwordEncoder.encode(password), fullname,street,city,
                        state,zip,phone);
    }

}
