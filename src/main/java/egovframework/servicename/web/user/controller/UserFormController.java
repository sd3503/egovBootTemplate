package egovframework.servicename.web.user.controller;

import egovframework.servicename.web.common.domain.CommonCode;
import egovframework.servicename.web.common.repository.CommonCodeRepository;
import egovframework.servicename.web.user.domain.Users;
import egovframework.servicename.web.user.dto.UserSignupRequest;
import egovframework.servicename.web.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/form/users")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserFormController {


    private final UserRepository userRepository;
    private final CommonCodeRepository commonCodeRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("signupRequest", new UserSignupRequest());
        return "thymeleaf/signupForm";  // 폼 화면
    }

    @PostMapping("/signup")
    @Transactional
    public String signup(@ModelAttribute("signupRequest") UserSignupRequest request, Model model) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            model.addAttribute("error", "이미 존재하는 사용자입니다.");
            return "thymeleaf/signupForm";
        }

        CommonCode role = commonCodeRepository.findByGroup_GroupCodeAndCode("ROLE", "ROLE_USER")
                .orElseThrow(() -> new IllegalStateException("ROLE_USER 코드가 없습니다."));

        Users user = Users.createWithRole(request, role, passwordEncoder);
        userRepository.save(user);

        return "redirect:/login"; // 로그인 페이지로 리다이렉트
//        return "redirect:/form/users/login"; // 로그인 페이지로 리다이렉트
    }

    @GetMapping("/login")
    public String loginPage() {
        return "thymeleaf/login"; // 로그인 폼 템플릿
    }

    @GetMapping("/home")
    public String mainAfterLogin() {
        return "thymeleaf/home"; // 로그인 후 메인
    }
}
