package egovframework.servicename.web.common.service;

import egovframework.servicename.web.common.domain.CommonCode;
import egovframework.servicename.web.common.domain.CommonCodeGroup;
import egovframework.servicename.web.common.dto.CommonCodeDto;
import egovframework.servicename.web.common.dto.CommonCodeGroupDto;
import egovframework.servicename.web.common.repository.CommonCodeGroupRepository;
import egovframework.servicename.web.common.repository.CommonCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommonCodeGroupService {


    private final CommonCodeGroupRepository codeGroupRepo;
    private final CommonCodeRepository codeRepo;

    // ===== 코드 그룹 =====

    public void createGroup(CommonCodeGroupDto dto) {
        String groupCode = dto.getGroupCode().toUpperCase();
        if (codeGroupRepo.existsByGroupCode(groupCode)) {
            throw new IllegalArgumentException("이미 존재하는 코드 그룹입니다: " + groupCode);
        }
        CommonCodeGroup entity = CommonCodeGroup.fromDto(dto, getCurrentUserId());
        codeGroupRepo.save(entity);
    }

    public List<CommonCodeGroupDto> getAllGroups() {
        return null;
    }

    public CommonCodeGroupDto getGroup(Long id) {
        return null;
    }

    public void updateGroup(Long id, CommonCodeGroupDto dto) {
        CommonCodeGroup entity = codeGroupRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("코드 그룹을 찾을 수 없습니다."));
        entity.updateFromDto(dto, getCurrentUserId());
    }

    public void deleteGroup(Long id) {
        if (!codeGroupRepo.existsById(id)) {
            throw new IllegalArgumentException("삭제할 코드 그룹이 존재하지 않습니다.");
        }
        codeGroupRepo.deleteById(id);
    }

    // ===== 상세 코드 =====

    public void createCode(CommonCodeDto dto) {
        String code = dto.getCode().toUpperCase();
        CommonCodeGroup group = codeGroupRepo.findById(dto.getGroupId())
                .orElseThrow(() -> new IllegalArgumentException("코드 그룹이 존재하지 않습니다."));

        if (codeRepo.existsByGroupIdAndCode(dto.getGroupId(), code)) {
            throw new IllegalArgumentException("해당 그룹에 이미 존재하는 코드입니다: " + code);
        }

        CommonCode entity = CommonCode.fromDto(dto, group, getCurrentUserId());
        codeRepo.save(entity);
    }

    public List<CommonCodeDto> getCodesByGroup(Long groupId) {
        return null;
    }

    public CommonCodeDto getCode(Long id) {
        return null;
    }

    public void updateCode(Long id, CommonCodeDto dto) {
        CommonCode entity = codeRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("코드를 찾을 수 없습니다."));

        entity.updateFromDto(dto, getCurrentUserId());
    }

    public void deleteCode(Long id) {
        if (!codeRepo.existsById(id)) {
            throw new IllegalArgumentException("삭제할 코드가 존재하지 않습니다.");
        }
        codeRepo.deleteById(id);
    }


    // ===== 사용자 아이디 임시 처리 =====

    private Long getCurrentUserId() {
        // 추후 Spring Security 연동으로 대체 가능
        return 1L;
    }
}

