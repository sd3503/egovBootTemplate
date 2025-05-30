package egovframework.servicename.web.common.service;

import egovframework.servicename.web.common.dto.CommonCodeDto;
import egovframework.servicename.web.common.dto.CommonCodeGroupDto;
import egovframework.servicename.web.common.repository.CommonCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommonCodeService {

    private final CommonCodeRepository codeRepo;

    public List<CommonCodeDto> getCodesByGroup(Long groupId) {
        return codeRepo.findCodeDtosByGroupId(groupId);
    }

    public List<CommonCodeGroupDto> getAllCodeGroups() {
        return codeRepo.findAllCodeGroupDtos();
    }
}