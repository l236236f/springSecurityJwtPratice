package ssj.security.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * @author harveylo
 * @description 登入請求
 */
@Data
@AllArgsConstructor
public class LoginRequest {
  private String username;
  private String password;
  private Boolean rememberMe;
}
