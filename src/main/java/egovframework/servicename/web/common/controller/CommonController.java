package egovframework.servicename.web.common.controller;


import egovframework.servicename.web.common.dto.CommonCodeDto;
import egovframework.servicename.web.common.dto.CommonCodeGroupDto;
import egovframework.servicename.web.common.dto.CommonCodeGroupSearchCondition;
import egovframework.servicename.web.common.service.CommonCodeGroupService;
import egovframework.servicename.web.common.service.CommonCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common")
public class CommonController {

    private final CommonCodeGroupService commonCodeGroupService;
    private final CommonCodeService commonCodeService;

    // ---------------------------------------------------
    // 📌 [1] 공통 코드 그룹 관련
    // ---------------------------------------------------

    /** 코드 그룹 리스트 조회 (검색 + 페이징) */
    @GetMapping("/groups")
    public ResponseEntity<Page<CommonCodeGroupDto>> searchGroups(
            CommonCodeGroupSearchCondition condition,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return ResponseEntity.ok(commonCodeGroupService.search(condition, pageable));
    }

    /** 코드 그룹 단건 조회 */
    @GetMapping("/groups/{id}")
    public ResponseEntity<CommonCodeGroupDto> getGroup(@PathVariable Long id) {
        return ResponseEntity.ok(commonCodeGroupService.getGroup(id));
    }

    /** 코드 그룹 생성 */
    @PostMapping("/groups")
    public ResponseEntity<Long> createGroup(@RequestBody @Valid CommonCodeGroupDto dto) {
        Long id = commonCodeGroupService.create(dto);
        return ResponseEntity.ok(id);
    }

    /** 코드 그룹 수정 */
    @PutMapping("/groups/{id}")
    public ResponseEntity<Void> updateGroup(@PathVariable Long id, @RequestBody @Valid CommonCodeGroupDto dto) {
        commonCodeGroupService.update(id, dto);
        return ResponseEntity.noContent().build();
    }

    /** 코드 그룹 삭제 */
    @DeleteMapping("/groups/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        commonCodeGroupService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ---------------------------------------------------
    // 📌 [2] 공통 코드 관련
    // ---------------------------------------------------

    /** 그룹 ID로 코드 목록 조회 */
    @GetMapping("/codes")
    public ResponseEntity<List<CommonCodeDto>> getCodesByGroup(@RequestParam Long groupId) {
        return ResponseEntity.ok(commonCodeService.getCodesByGroupId(groupId));
    }

    /** 코드 단건 조회 */
    @GetMapping("/codes/{id}")
    public ResponseEntity<CommonCodeDto> getCode(@PathVariable Long id) {
        return ResponseEntity.ok(commonCodeService.getCode(id));
    }

    /** 코드 생성 */
    @PostMapping("/codes")
    public ResponseEntity<Long> createCode(@RequestBody @Valid CommonCodeDto dto) {
        Long id = commonCodeService.create(dto);
        return ResponseEntity.ok(id);
    }

    /** 코드 수정 */
    @PutMapping("/codes/{id}")
    public ResponseEntity<Void> updateCode(@PathVariable Long id, @RequestBody @Valid CommonCodeDto dto) {
        commonCodeService.update(id, dto);
        return ResponseEntity.noContent().build();
    }

    /** 코드 삭제 (isActive = false 처리) */
    @DeleteMapping("/codes/{id}")
    public ResponseEntity<Void> deleteCode(@PathVariable Long id) {
        commonCodeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
