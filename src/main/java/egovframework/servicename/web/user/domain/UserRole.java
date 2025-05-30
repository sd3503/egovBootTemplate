package egovframework.servicename.web.user.domain;

import egovframework.servicename.web.common.domain.AuditableEntity;
import egovframework.servicename.web.common.domain.CommonCode;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_roles", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "role_id"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRole extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 사용자 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 공통코드 (권한) 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private CommonCode role;  // group_code = 'ROLE'인 값만 사용해야 함

    @Override
    protected void validateEntity() {
        if (!"ROLE".equals(role.getGroup().getGroupCode())) {
            throw new IllegalStateException("UserRole은 ROLE 그룹만 허용됩니다.");
        }
    }
}
