package egovframework.servicename.web.user.repository;

import egovframework.servicename.web.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<User, Long> {
}
