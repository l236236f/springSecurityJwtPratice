package ssj.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ssj.system.entity.info.representation.UserRepresentation;
import ssj.system.entity.info.request.UserRegisterRequest;
import ssj.system.entity.info.request.UserUpdateRequest;
import ssj.system.service.UserService;

import javax.validation.Valid;

/**
 * @author harveylo
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/users")
@Api(tags = "用戶系統")
public class UserController {

  private final UserService userService;

  @PostMapping("/sign-up")
  @ApiOperation("用戶註冊")
  public ResponseEntity<Void> signUp(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
    userService.save(userRegisterRequest);
    return ResponseEntity.ok().build();
  }

  @GetMapping
  @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER','ROLE_ADMIN')")
  @ApiOperation("獲取已分頁的用戶訊息 param= pageNum, pageSize")
  public ResponseEntity<Page<UserRepresentation>> getAllUser(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
    Page<UserRepresentation> allUser = userService.getAll(pageNum, pageSize);
    return ResponseEntity.ok().body(allUser);
  }

  @PutMapping
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  @ApiOperation("更新用户")
  public ResponseEntity<Void> update(@RequestBody @Valid UserUpdateRequest userUpdateRequest) {
    userService.update(userUpdateRequest);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  @ApiOperation("根据用户名删除用户")
  public ResponseEntity<Void> deleteUserByUserName(@RequestParam("username") String username) {
    userService.delete(username);
    return ResponseEntity.ok().build();
  }
}