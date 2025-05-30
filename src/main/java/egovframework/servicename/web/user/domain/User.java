package egovframework.servicename.web.user.domain;

import egovframework.servicename.web.common.domain.AuditableEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter //@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User extends AuditableEntity {
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> roles = new ArrayList<>();  // 권한 (예: ADMIN, USER)

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private UserStatus status;  // 상태 (예: ACTIVE, INACTIVE)
}
