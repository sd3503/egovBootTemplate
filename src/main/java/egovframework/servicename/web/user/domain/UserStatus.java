package egovframework.servicename.web.user.domain;

public enum UserStatus {
    ACTIVE("활성화됨"),
    INACTIVE("비활성화됨");

    private final String description;

    UserStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
