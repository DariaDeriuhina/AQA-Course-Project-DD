package api.enums;

public enum UserRole {
    USER("app-user"),
    MANAGER("app-manager"),
    ADMIN("app-admin");
    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}