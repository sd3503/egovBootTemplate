package egovframework.servicename.web.common.service;

import egovframework.servicename.web.common.domain.CommonCode;
import egovframework.servicename.web.common.domain.CommonCodeGroup;
import egovframework.servicename.web.common.dto.CommonCodeDto;
import egovframework.servicename.web.common.repository.CommonCodeGroupRepository;
import egovframework.servicename.web.common.repository.CommonCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class CommonCodeService {
    private final CommonCodeRepository commonCodeRepository;
    private final CommonCodeGroupRepository commonCodeGroupRepository;

    /**
     * 코드 그룹 ID로 전체 코드 목록 조회
     */
    @Transactional(readOnly = true)
    public List<CommonCodeDto> getCodesByGroupId(Long groupId) {
        return commonCodeRepository.findByGroupId(groupId);
    }

    /**
     * 단일 코드 조회
     */
    @Transactional(readOnly = true)
    public CommonCodeDto getCode(Long id) {
        CommonCode code = commonCodeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 코드가 존재하지 않습니다. id=" + id));

        return new CommonCodeDto(
                code.getGroup().getId(),
                code.getCode(),
                code.getCodeName(),
                code.getSortOrder(),
                code.getIsActive()
        );
    }

    /**
     * 코드 생성
     */
    @Transactional
    public Long create(CommonCodeDto dto) {
        CommonCodeGroup group = commonCodeGroupRepository.findById(dto.getGroupId())
                .orElseThrow(() -> new IllegalArgumentException("해당 그룹이 존재하지 않습니다. id=" + dto.getGroupId()));

        CommonCode code = CommonCode.fromDto(dto, group, 1L);//temporary
        return commonCodeRepository.save(code).getId();
    }

    /**
     * 코드 수정
     */
    @Transactional
    public void update(Long id, CommonCodeDto dto) {
        CommonCode code = commonCodeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 코드가 존재하지 않습니다. id=" + id));

        code.updateFromDto(dto, 1L);//temporary
    }

    /**
     * 코드 삭제
     */
    @Transactional
    public void delete(Long id) {
        CommonCode code = commonCodeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 코드가 존재하지 않습니다. id=" + id));

        code.updateDelete(1L);//temporary
    }
}