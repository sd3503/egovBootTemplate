package egovframework.servicename.web.common.repository;

import egovframework.servicename.web.common.domain.CommonCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommonCodeRepository extends JpaRepository<CommonCode, Long>, CommonCodeRepositoryCustom {

    Optional<CommonCode> findByGroup_GroupCodeAndCode(String groupCode, String code);
}
