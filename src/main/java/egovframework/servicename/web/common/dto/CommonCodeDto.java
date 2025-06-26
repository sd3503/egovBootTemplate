package egovframework.servicename.web.common.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CommonCodeDto {
    @NotNull(message = "그룹 ID는 필수입니다.")
    private Long groupId;

    @NotBlank(message = "코드는 필수입니다.")
    @Size(max = 50, message = "코드는 50자 이하로 입력하세요.")
    private String code;

    @NotBlank(message = "코드명은 필수입니다.")
    @Size(max = 100, message = "코드명은 100자 이하로 입력하세요.")
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