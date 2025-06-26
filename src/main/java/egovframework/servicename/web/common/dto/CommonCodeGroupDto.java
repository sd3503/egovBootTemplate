package egovframework.servicename.web.common.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CommonCodeGroupDto {

    @NotBlank
    private Long id;

    @NotBlank(message = "그룹코드는 필수입니다.")
    @Size(max = 50, message = "그룹코드는 50자 이하로 입력하세요.")
    private String groupCode;

    @NotBlank(message = "그룹명은 필수입니다.")
    @Size(max = 100, message = "그룹명은 100자 이하로 입력하세요.")
    private String groupName;

    private Boolean isActive;

    @QueryProjection
    public CommonCodeGroupDto(Long id, String groupCode, String groupName, Boolean isActive) {
        this.id = id;
        this.groupCode = groupCode;
        this.groupName = groupName;
        this.isActive = isActive;
    }
    public CommonCodeGroupDto(String groupCode, String groupName) {
        this.groupCode = groupCode;
        this.groupName = groupName;
    }
}