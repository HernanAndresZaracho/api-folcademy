package eich.com.exampleapi.Security;

import lombok.Data;

@Data
public class AuthCredentials {
    private String email;
    private String password;
    private String username;
}
