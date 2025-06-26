package egovframework.servicename.web.common.repository;

import egovframework.servicename.web.common.domain.CommonCodeGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonCodeGroupRepository extends JpaRepository<CommonCodeGroup, Long>, CommonCodeGroupRepositoryCustom {

}
