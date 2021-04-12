package ssj.system.enums;

import lombok.Getter;

@Getter
public enum RoleType {
  USER("USER", "用戶"),
  TEMP_USER("TEMP_USER", "臨時用戶"),
  MANAGER("MANAGER", "管理者"),
  ADMIN("ADMIN", "管理員權限");
  private final String name;
  private final String description;

  RoleType(java.lang.String name, java.lang.String description) {
    this.name = name;
    this.description = description;
  }
}
