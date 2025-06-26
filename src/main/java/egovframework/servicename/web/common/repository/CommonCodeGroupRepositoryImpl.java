package egovframework.servicename.web.common.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import egovframework.servicename.web.common.domain.QCommonCodeGroup;
import egovframework.servicename.web.common.dto.CommonCodeGroupDto;
import egovframework.servicename.web.common.dto.CommonCodeGroupSearchCondition;
import egovframework.servicename.web.common.dto.QCommonCodeGroupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

import static egovframework.servicename.web.common.domain.QCommonCodeGroup.commonCodeGroup;

@RequiredArgsConstructor
public class CommonCodeGroupRepositoryImpl implements CommonCodeGroupRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    // search 기본형
    @Override
    public Page<CommonCodeGroupDto> search(CommonCodeGroupSearchCondition condition, Pageable pageable) {
        QCommonCodeGroup group = commonCodeGroup;

        List<CommonCodeGroupDto> content = queryFactory
                .select(new QCommonCodeGroupDto(
                        group.id,
                        group.groupCode,
                        group.groupName,
                        group.isActive
                ))
                .from(group)
                .where(
                        GroupNameContains(condition.getGroupName()),
                        GroupCodeContains(condition.getGroupCode())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = Optional.ofNullable(queryFactory
                .select(group.count())
                .from(group)
                .where(
                        GroupNameContains(condition.getGroupName()),
                        GroupCodeContains(condition.getGroupCode())
                )
                .fetchOne()).orElse(0L);


        return PageableExecutionUtils.getPage(content, pageable, () -> total);
    }

    private BooleanExpression GroupNameContains(String groupName) {
        return StringUtils.hasText(groupName) ? commonCodeGroup.groupName.contains(groupName) : null;
    }

    private BooleanExpression GroupCodeContains(String groupCode) {
        return StringUtils.hasText(groupCode) ? commonCodeGroup.groupCode.contains(groupCode) : null;
    }
}
