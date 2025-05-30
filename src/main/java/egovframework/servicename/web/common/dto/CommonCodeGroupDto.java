package egovframework.servicename.web.common.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CommonCodeGroupDto {
    @NotBlank
    private String groupCode;

    @NotBlank
    private String groupName;

    private String description;

    @QueryProjection
    public CommonCodeGroupDto(String groupCode, String groupName) {
        this.groupCode = groupCode;
        this.groupName = groupName;
    }
}