package ssj.security.common.utils;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ssj.system.entity.User;
import ssj.system.service.UserService;

/**
 * @author harveylo
 * @description 獲取當前請求的用戶
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CurrentUserUtils {

  private final UserService userService;

  public User getCurrentUser() {
    return userService.find(getCurrentUserName());
  }

  private String getCurrentUserName() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.getPrincipal() != null) {
      return (String) authentication.getPrincipal();
    }
    return null;
  }
}
