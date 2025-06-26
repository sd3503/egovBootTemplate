package egovframework.servicename.web.user.domain;

import egovframework.servicename.utils.auditableEntity.CommonAuditableEntity;
import egovframework.servicename.web.common.domain.CommonCode;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_roles", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "role_id"})
})
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRole extends CommonAuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 사용자 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    // 공통코드 (권한) 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private CommonCode role;  // group_code = 'ROLE'인 값만 사용해야 함

    /**
     * 정적 생성 메서드
     */
    public static UserRole of(Users user, CommonCode role) {
        if (!"ROLE".equals(role.getGroup().getGroupCode())) {
            throw new IllegalArgumentException("UserRole은 ROLE 그룹만 허용됩니다.");
        }

        return UserRole.builder().user(user).role(role).build();
    }

    @Override
    protected void validateEntity() {
        if (!"ROLE".equals(role.getGroup().getGroupCode())) {
            throw new IllegalStateException("UserRole은 ROLE 그룹만 허용됩니다.");
        }
    }
}