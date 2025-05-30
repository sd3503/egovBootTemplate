package egovframework.servicename.web.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum RoleType {
    ADMIN("ADMIN", "관리자"),
    USER("USER", "일반 사용자");

    private final String code;
    private final String label;

    public static RoleType fromCode(String code) {
        return Arrays.stream(values())
                .filter(role -> role.code.equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown role code: " + code));
    }
}