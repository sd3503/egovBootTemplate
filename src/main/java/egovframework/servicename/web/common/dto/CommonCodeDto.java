package egovframework.servicename.web.common.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommonCodeDto {
    private Long groupId;
    private String code;
    private String codeName;
    private Integer sortOrder;
    private Boolean isActive;

    @QueryProjection
    public CommonCodeDto(Long groupId, String code, String codeName, Integer sortOrder, Boolean isActive) {
        this.groupId = groupId;
        this.code = code;
        this.codeName = codeName;
        this.sortOrder = sortOrder;
        this.isActive = isActive;
    }
}