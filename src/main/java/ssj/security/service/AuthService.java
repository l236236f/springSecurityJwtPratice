package ssj.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import ssj.security.common.utils.CurrentUserUtils;
import ssj.security.common.utils.JwtTokenUtils;
import ssj.security.controller.dto.LoginRequest;
import ssj.security.entity.JwtUser;
import ssj.system.entity.User;
import ssj.system.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author harveylo
 **/
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthService {
  private final UserService userService;
  private final StringRedisTemplate stringRedisTemplate;
  private final CurrentUserUtils currentUserUtils;
  public String createToken(LoginRequest loginRequest) {
    User user = userService.find(loginRequest.getUsername());
    if (!userService.check(loginRequest.getPassword(), user.getPassword())) {
      throw new BadCredentialsException("The user name or password is not correct.");
    }
    JwtUser jwtUser = new JwtUser(user);
    if (!jwtUser.isEnabled()) {
      throw new BadCredentialsException("User is forbidden to login");
    }
    List<String> authorities = jwtUser.getAuthorities()
        .stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.toList());
    String token = JwtTokenUtils.createToken(user.getUserName(), user.getId().toString(), authorities, loginRequest.getRememberMe());

   try {
     stringRedisTemplate.opsForValue().set(user.getId().toString(), token);
   }catch (Exception e){
     log.error("Redis 設定失敗 請檢查 Redis 設定");
   }
    return token;
  }
  public void removeToken() {
    stringRedisTemplate.delete(currentUserUtils.getCurrentUser().getId().toString());
  }
}
