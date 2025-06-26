package egovframework.servicename.web.common.repository;

import egovframework.servicename.web.common.dto.CommonCodeDto;

import java.util.List;

public interface CommonCodeRepositoryCustom {
    List<CommonCodeDto> findByGroupId(Long groupId);
}