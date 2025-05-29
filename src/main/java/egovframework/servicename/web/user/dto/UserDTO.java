package egovframework.servicename.web.user.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {
    private int id;
    private String loginId;
    private String password;
    private String name;
    private int adminRule;
    private int status;
    private String lastLoginDatetime;
    private String createdAt;
    private String updatedAt;



}
