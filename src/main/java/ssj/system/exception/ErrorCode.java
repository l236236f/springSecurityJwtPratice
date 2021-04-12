package ssj.system.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
  USER_NAME_ALREADY_EXIST(1001, HttpStatus.BAD_REQUEST, "用戶已存在"),
  Role_NOT_FOUND(1002, HttpStatus.NOT_FOUND, "沒有找到該角色"),
  USER_NAME_NOT_FOUND(1002, HttpStatus.NOT_FOUND, "未找到指定用戶"),
  VERIFY_JWT_FAILED(1003, HttpStatus.UNAUTHORIZED, "token驗證失敗"),
  METHOD_ARGUMENT_NOT_VALID(1003, HttpStatus.BAD_REQUEST, "方法參數驗證失敗");
  private final int code;

  private final HttpStatus status;

  private final String message;

  ErrorCode(int code, HttpStatus status, String message) {
    this.code = code;
    this.status = status;
    this.message = message;
  }
}