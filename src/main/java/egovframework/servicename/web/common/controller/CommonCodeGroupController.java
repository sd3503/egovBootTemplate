package egovframework.servicename.web.common.controller;


import egovframework.servicename.web.common.dto.CommonCodeGroupDto;
import egovframework.servicename.web.common.service.CommonCodeGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/code-groups")
@RequiredArgsConstructor
public class CommonCodeGroupController {

    private final CommonCodeGroupService codeService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CommonCodeGroupDto dto) {
        codeService.createGroup(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CommonCodeGroupDto>> list() {
        return ResponseEntity.ok(codeService.getAllGroups());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonCodeGroupDto> detail(@PathVariable Long id) {
        return ResponseEntity.ok(codeService.getGroup(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CommonCodeGroupDto dto) {
        codeService.updateGroup(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        codeService.deleteGroup(id);
        return ResponseEntity.noContent().build();
    }
}
