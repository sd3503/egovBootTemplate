package egovframework.servicename.web.user.repository;


import egovframework.servicename.web.user.domain.Users;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
    @Query("SELECT u FROM Users u LEFT JOIN FETCH u.roles r LEFT JOIN FETCH r.role WHERE u.username = :username")
    Optional<Users> findByUsernameWithRoles(@Param("username") String username);
}
