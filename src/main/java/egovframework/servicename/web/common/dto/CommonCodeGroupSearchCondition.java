package egovframework.servicename.web.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommonCodeGroupSearchCondition {
    private String groupName;
    private String groupCode;
}