package egovframework.servicename.web.common.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import egovframework.servicename.web.common.domain.QCommonCode;
import egovframework.servicename.web.common.domain.QCommonCodeGroup;
import egovframework.servicename.web.common.dto.CommonCodeDto;
import egovframework.servicename.web.common.dto.CommonCodeGroupDto;
import egovframework.servicename.web.common.dto.QCommonCodeDto;
import egovframework.servicename.web.common.dto.QCommonCodeGroupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommonCodeRepositoryImpl implements CommonCodeRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<CommonCodeDto> findCodeDtosByGroupId(Long groupId) {
        QCommonCode code = QCommonCode.commonCode;

        return queryFactory
                .select(new QCommonCodeDto(
                        code.group.id,
                        code.code,
                        code.codeName,
                        code.sortOrder,
                        code.isActive
                ))
                .from(code)
                .where(code.group.id.eq(groupId))
                .fetch();
    }

    @Override
    public List<CommonCodeGroupDto> findAllCodeGroupDtos() {
        QCommonCodeGroup group = QCommonCodeGroup.commonCodeGroup;

        return queryFactory
                .select(new QCommonCodeGroupDto(
                        group.groupCode,
                        group.groupName
                ))
                .from(group)
                .fetch();
    }
}
