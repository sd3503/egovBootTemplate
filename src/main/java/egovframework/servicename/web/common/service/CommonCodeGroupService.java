package egovframework.servicename.web.common.service;

import egovframework.servicename.web.common.domain.CommonCodeGroup;
import egovframework.servicename.web.common.dto.CommonCodeGroupDto;
import egovframework.servicename.web.common.dto.CommonCodeGroupSearchCondition;
import egovframework.servicename.web.common.repository.CommonCodeGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommonCodeGroupService {

    private final CommonCodeGroupRepository commonCodeGroupRepository;

    /**
     * 그룹 목록 조회 (검색 + 페이징)
     */
    @Transactional(readOnly = true)
    public Page<CommonCodeGroupDto> search(CommonCodeGroupSearchCondition condition, Pageable pageable) {
        return commonCodeGroupRepository.search(condition, pageable);
    }

    /**
     * 단일 그룹 조회
     */
    @Transactional(readOnly = true)
    public CommonCodeGroupDto getGroup(Long id) {
        CommonCodeGroup group = commonCodeGroupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 코드 그룹이 존재하지 않습니다. id=" + id));

        return new CommonCodeGroupDto(group.getGroupCode(), group.getGroupName());
    }

    /**
     * 그룹 생성
     */
    public Long create(CommonCodeGroupDto dto) {
        CommonCodeGroup group = CommonCodeGroup.fromDto(dto, 1L);//temporary
        return commonCodeGroupRepository.save(group).getId();
    }

    /**
     * 그룹 수정
     */
    public void update(Long id, CommonCodeGroupDto dto) {
        CommonCodeGroup group = commonCodeGroupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 코드 그룹이 존재하지 않습니다. id=" + id));

        group.updateFromDto(dto, 1L);//temporary
    }

    /**
     * 그룹 삭제 (물리 삭제)
     */
    public void delete(Long id) {
        CommonCodeGroup group = commonCodeGroupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 코드 그룹이 존재하지 않습니다. id=" + id));

        commonCodeGroupRepository.delete(group);
    }

}

