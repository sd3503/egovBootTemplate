package egovframework.servicename.web.common.repository;

import egovframework.servicename.web.common.dto.CommonCodeDto;
import egovframework.servicename.web.common.dto.CommonCodeGroupDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommonCodeRepositoryCustom {
    List<CommonCodeDto> findCodeDtosByGroupId(Long groupId);
    List<CommonCodeGroupDto> findAllCodeGroupDtos();
}