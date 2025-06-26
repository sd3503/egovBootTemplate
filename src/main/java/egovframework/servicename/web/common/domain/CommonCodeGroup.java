package egovframework.servicename.web.common.domain;

import egovframework.servicename.utils.auditableEntity.CommonAuditableEntity;
import egovframework.servicename.web.common.dto.CommonCodeGroupDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "common_code_group")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonCodeGroup extends CommonAuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_code", nullable = false, unique = true, length = 50)
    private String groupCode;

    @Column(name = "group_name", nullable = false, length = 100)
    private String groupName;

    public CommonCodeGroup(Long id, String groupCode, String groupName, boolean isActive) {
        this.id = id;
        this.groupCode = groupCode;
        this.groupName = groupName;
        this.setIsActive(isActive);
    }

    public static CommonCodeGroup fromDto(CommonCodeGroupDto dto, Long createdBy) {
        CommonCodeGroup group = CommonCodeGroup.builder()
                .groupCode(dto.getGroupCode())
                .groupName(dto.getGroupName())
                .build();
        group.setCreatedBy(createdBy);
        return group;
    }
    public void updateFromDto(CommonCodeGroupDto dto, Long updatedBy) {
        this.groupCode = dto.getGroupCode();
        this.groupName = dto.getGroupName();
        this.updatedBy = updatedBy;
    }
    public void updateDelete(Long updatedBy) {
        this.updatedBy = updatedBy;
    }



}
