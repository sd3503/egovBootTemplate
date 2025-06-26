package egovframework.servicename.web.user.dto;

import lombok.Data;

@Data
public class UserSignupRequest {
    private String username;
    private String password;
    private String name;
    private String email;
}
