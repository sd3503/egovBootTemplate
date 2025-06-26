package egovframework.servicename.web.user.controller;

import egovframework.servicename.web.common.domain.CommonCode;
import egovframework.servicename.web.common.repository.CommonCodeRepository;
import egovframework.servicename.web.user.domain.Users;
import egovframework.servicename.web.user.dto.UserSignupRequest;
import egovframework.servicename.web.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CommonCodeRepository commonCodeRepository;

    @PostMapping("/signup")
    @Transactional
    public String signup(@RequestBody UserSignupRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return "이미 존재하는 사용자입니다.";
        }

        CommonCode role = commonCodeRepository.findByGroup_GroupCodeAndCode("ROLE", "ROLE_USER")
                .orElseThrow(() -> new IllegalStateException("ROLE_USER 코드가 없습니다."));

        Users user = Users.createWithRole(request, role, passwordEncoder);
        userRepository.save(user);

        return "회원가입 성공";

    }
}
