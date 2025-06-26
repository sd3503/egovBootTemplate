package egovframework.servicename.web.user.domain;

import egovframework.servicename.utils.auditableEntity.CommonAuditableEntity;
import egovframework.servicename.web.common.domain.CommonCode;
import egovframework.servicename.web.user.dto.UserSignupRequest;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Users extends CommonAuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;  // 사용자 ID (로그인 ID)


    @Column(nullable = false, length = 255)
    private String password;  // 암호화된 비밀번호

    @Column(length = 100)
    private String name;      // 사용자 이름

    @Column(length = 100)
    private String email;     // 이메일

    @Setter
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> roles = new ArrayList<>();  // 권한 (예: ADMIN, USER)

/*    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private UserStatus status;  // 상태 (예: ACTIVE, INACTIVE)*/


    public static Users createWithRole(UserSignupRequest request, CommonCode roleCode, PasswordEncoder encoder) {
        if (!"ROLE".equals(roleCode.getGroup().getGroupCode())) {
            throw new IllegalArgumentException("UserRole은 ROLE 그룹만 허용됩니다.");
        }

        Users user = Users.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(encoder.encode(request.getPassword()))
                .name(request.getName())
                .build();


        UserRole role = UserRole.of(user, roleCode);
        user.setRoles(Collections.singletonList(role));

        return user;
    }
}
