package egovframework.servicename.web.common.domain;

import egovframework.servicename.web.common.dto.CommonCodeDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "common_code",
        uniqueConstraints = @UniqueConstraint(columnNames = {"group_id", "code"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonCode extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String code;

    @Column(name = "code_name", nullable = false, length = 100)
    private String codeName;

    @Column(name = "sort_order")
    private Integer sortOrder = 0;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private CommonCodeGroup group;

    // ✅ 정적 팩토리 메서드
    public static CommonCode fromDto(CommonCodeDto dto, CommonCodeGroup group, Long createdBy) {
        CommonCode code = new CommonCode();
        code.setGroup(group);
        code.setCode(dto.getCode().toUpperCase());
        code.setCodeName(dto.getCodeName());
        code.setSortOrder(dto.getSortOrder() != null ? dto.getSortOrder() : 0);
        code.setIsActive(dto.getIsActive() != null ? dto.getIsActive() : true);
        code.setCreatedBy(createdBy);
        return code;
    }

    // ✅ 업데이트 메서드
    public void updateFromDto(CommonCodeDto dto, Long updatedBy) {
        this.codeName = dto.getCodeName();
        this.sortOrder = dto.getSortOrder() != null ? dto.getSortOrder() : this.sortOrder;
        this.isActive = dto.getIsActive() != null ? dto.getIsActive() : this.isActive;
        this.updatedBy = updatedBy;
    }
}
