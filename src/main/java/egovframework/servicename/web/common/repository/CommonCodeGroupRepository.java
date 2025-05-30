package egovframework.servicename.web.common.repository;

import egovframework.servicename.web.common.domain.CommonCodeGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommonCodeGroupRepository extends JpaRepository<CommonCodeGroup, Long> {
    Optional<CommonCodeGroup> findByGroupCode(String groupCode);

    boolean existsByGroupCode(String groupCode);
}
