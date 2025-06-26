package egovframework.servicename.web.common.repository;

import egovframework.servicename.web.common.dto.CommonCodeGroupDto;
import egovframework.servicename.web.common.dto.CommonCodeGroupSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommonCodeGroupRepositoryCustom {
    Page<CommonCodeGroupDto> search(CommonCodeGroupSearchCondition condition, Pageable pageable);
}
