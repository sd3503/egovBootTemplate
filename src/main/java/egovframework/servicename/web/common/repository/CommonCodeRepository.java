package egovframework.servicename.web.common.repository;

import egovframework.servicename.web.common.domain.CommonCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommonCodeRepository extends JpaRepository<CommonCode, Long>, CommonCodeRepositoryCustom {

    List<CommonCode> findByGroupId(Long groupId);

    boolean existsByGroupIdAndCode(Long groupId, String code);
}
