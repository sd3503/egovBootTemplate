package egovframework.servicename.web.common.domain;

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
public class CommonCodeGroup extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_code", nullable = false, unique = true, length = 50)
    private String groupCode;

    @Column(name = "group_name", nullable = false, length = 100)
    private String groupName;

    public static CommonCodeGroup fromDto(CommonCodeGroupDto dto, Long createdBy) {
        CommonCodeGroup group = CommonCodeGroup.builder()
                .groupCode(dto.getGroupCode().toUpperCase())
                .groupName(dto.getGroupName())
                .build();
        group.setCreatedBy(createdBy);
        return group;
    }
    public void updateFromDto(CommonCodeGroupDto dto, Long updatedBy) {
        this.groupName = dto.getGroupName();
        this.updatedBy = updatedBy;
    }

}
