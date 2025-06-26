package egovframework.servicename.web.common.repository;

import egovframework.servicename.web.common.domain.CommonCodeGroup;
import egovframework.servicename.web.common.dto.CommonCodeDto;
import egovframework.servicename.web.common.dto.CommonCodeGroupDto;
import egovframework.servicename.web.common.dto.CommonCodeGroupSearchCondition;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional()
@Slf4j
@ActiveProfiles("local")
class CommonCodeGroupRepositoryTest {


    @Autowired
    private CommonCodeGroupRepository commonCodeGroupRepository;

    @Autowired
    private CommonCodeRepositoryImpl commonCodeRepositoryImpl;

    @Test
    public void commonCodeGroupCreate() {
        CommonCodeGroup group = new CommonCodeGroup(null, "groupCode", "groupName", true);

        CommonCodeGroup saved = commonCodeGroupRepository.save(group);
        CommonCodeGroup found = commonCodeGroupRepository.findById(saved.getId()).orElse(null);

        assertThat(found).isNotNull();
        assertThat(found.getGroupCode()).isEqualTo("groupCode");
        assertThat(found.getGroupName()).isEqualTo("groupName");
        assertThat(found.getIsActive()).isTrue();
    }

    @Test
    public void commonCodeGroupRead() {
        CommonCodeGroup saved = commonCodeGroupRepository.save(
                new CommonCodeGroup(null, "readCode", "readName", true));

        CommonCodeGroup found = commonCodeGroupRepository.findById(saved.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getGroupCode()).isEqualTo("readCode");
    }

    @Test
    public void commonCodeGroupUpdate() {
        CommonCodeGroup saved = commonCodeGroupRepository.save(
                new CommonCodeGroup(null, "updateCode", "updateName", true));

        saved.setGroupName("updatedName");
        saved.setIsActive(false);

        CommonCodeGroup updated = commonCodeGroupRepository.save(saved);

        assertThat(updated.getGroupName()).isEqualTo("updatedName");
        assertThat(updated.getIsActive()).isFalse();
    }

    @Test
    public void commonCodeGroupDelete() {
        CommonCodeGroup saved = commonCodeGroupRepository.save(
                new CommonCodeGroup(null, "deleteCode", "deleteName", true));

        Long id = saved.getId();
        commonCodeGroupRepository.deleteById(id);

        CommonCodeGroup deleted = commonCodeGroupRepository.findById(id).orElse(null);
        assertThat(deleted).isNull();
    }

    @Test
    public void findCommonCodesByGroupId() {
        CommonCodeGroup group = new CommonCodeGroup(null, "groupForQuery", "Group for Query", true);
        CommonCodeGroup savedGroup = commonCodeGroupRepository.save(group);

        List<CommonCodeDto> codes = commonCodeRepositoryImpl.findByGroupId(savedGroup.getId());

        assertThat(codes).isNotNull();
        assertThat(codes).isInstanceOf(List.class);
    }

    @Test
    public void findByIdWithNonExistingIdShouldReturnEmpty() {
        Optional<CommonCodeGroup> result = commonCodeGroupRepository.findById(-999L);
        assertThat(result).isEmpty();
    }

    @Test
    public void saveWithDuplicateGroupCodeShouldFail() {
        CommonCodeGroup first = new CommonCodeGroup(null, "dupCode", "Name1", true);
        commonCodeGroupRepository.save(first);

        CommonCodeGroup duplicate = new CommonCodeGroup(null, "dupCode", "Name2", true);
        assertThatThrownBy(() -> commonCodeGroupRepository.saveAndFlush(duplicate))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    public void saveWithNullGroupCodeShouldFail() {
        CommonCodeGroup invalid = new CommonCodeGroup(null, null, "NullCode", true);
        assertThatThrownBy(() -> commonCodeGroupRepository.saveAndFlush(invalid))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    public void bulkInsertPerformanceTest() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            CommonCodeGroup group = new CommonCodeGroup(null, "bulkCode" + i, "bulkName" + i, true);
            commonCodeGroupRepository.save(group);
        }
        List<CommonCodeGroup> all = commonCodeGroupRepository.findAll();
        long end = System.currentTimeMillis();

        System.out.println("Bulk Insert 소요 시간(ms): " + (end - start));
        Assertions.assertThat(all.size()).isGreaterThanOrEqualTo(1000);
    }

    @Test
    public void commonCodeGroupSearchTest() throws Exception {
        // given

        CommonCodeGroupSearchCondition condition = new CommonCodeGroupSearchCondition("searchKeyWord", null);
        Pageable pageable = PageRequest.of(0, 10);
        // when
        for (int i = 0; i < 30; i++) {
            CommonCodeGroupDto commonCodeGroupDto = new CommonCodeGroupDto(null, "searchKeyWordCode" + i, "searchKeyWordName" + i, true);
            CommonCodeGroup commonCodeGroup = CommonCodeGroup.fromDto(commonCodeGroupDto, 1L);
            commonCodeGroupRepository.save(commonCodeGroup);
        }
        // then
        Page<CommonCodeGroupDto> search = commonCodeGroupRepository.search(condition, pageable);

        assertThat(search.getTotalElements()).isEqualTo(30);
        assertThat(search.getTotalPages()).isEqualTo(3);
        assertThat(search.getContent().get(0).getGroupCode()).isEqualTo("searchKeyWordCode0");
        assertThat(search.getContent().get(0).getGroupName()).isEqualTo("searchKeyWordName0");
        assertThat(search.getContent().get(0).getIsActive()).isTrue();
    }

    @Test
    public void queryDslPagingAndSortingTest() {
        for (int i = 0; i < 25; i++) {
            CommonCodeGroup group = new CommonCodeGroup(null, "code" + i, "name" + i, true);
            commonCodeGroupRepository.save(group);
        }

        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "groupCode"));
        Page<CommonCodeGroup> page = commonCodeGroupRepository.findAll(pageable);

        Assertions.assertThat(page.getContent().size()).isEqualTo(10);
        Assertions.assertThat(page.getTotalElements()).isEqualTo(26);
        Assertions.assertThat(page.getContent().get(1).getGroupCode()).isEqualTo("code9");
    }

}