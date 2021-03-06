package ssj.system.service;

import com.google.common.collect.ImmutableMap;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssj.system.entity.Role;
import ssj.system.entity.User;
import ssj.system.entity.UserRole;
import ssj.system.entity.info.representation.UserRepresentation;
import ssj.system.entity.info.request.UserRegisterRequest;
import ssj.system.entity.info.request.UserUpdateRequest;
import ssj.system.enums.RoleType;
import ssj.system.exception.RoleNotFoundException;
import ssj.system.exception.UserNameAlreadyExistException;
import ssj.system.exception.UserNameNotFoundException;
import ssj.system.repository.RoleRepository;
import ssj.system.repository.UserRepository;
import ssj.system.repository.UserRoleRepository;

import java.util.Objects;

/**
 * @author harveylo
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

  public static final String USERNAME = "username:";
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final UserRoleRepository userRoleRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Transactional(rollbackFor = Exception.class)
  public void save(UserRegisterRequest userRegisterRequest) {
    ensureUserNameNotExist(userRegisterRequest.getUserName());
    User user = userRegisterRequest.toUser();
    user.setPassword(bCryptPasswordEncoder.encode(userRegisterRequest.getPassword()));
    userRepository.save(user);
    //給用戶榜定角色
    Role studentRole = roleRepository.findByName(RoleType.USER.getName()).orElseThrow(() -> new RoleNotFoundException(ImmutableMap.of("roleName", RoleType.USER.getName())));
    Role managerRole = roleRepository.findByName(RoleType.MANAGER.getName()).orElseThrow(() -> new RoleNotFoundException(ImmutableMap.of("roleName", RoleType.MANAGER.getName())));
    userRoleRepository.save(new UserRole(user, studentRole));
    userRoleRepository.save(new UserRole(user, managerRole));
  }

  public User find(String userName) {
    return userRepository.findByUserName(userName).orElseThrow(() -> new UserNameNotFoundException(ImmutableMap.of(USERNAME, userName)));
  }

  public void update(UserUpdateRequest userUpdateRequest) {
    User user = find(userUpdateRequest.getUserName());
    if (Objects.nonNull(userUpdateRequest.getFullName())) {
      user.setFullName(userUpdateRequest.getFullName());
    }
    if (Objects.nonNull(userUpdateRequest.getPassword())) {
      user.setPassword(bCryptPasswordEncoder.encode(userUpdateRequest.getPassword()));
    }
    if (Objects.nonNull(userUpdateRequest.getEnabled())) {
      user.setEnabled(userUpdateRequest.getEnabled());
    }
    userRepository.save(user);
  }


  public void delete(String userName) {
    if (!userRepository.existsByUserName(userName)) {
      throw new UserNameNotFoundException(ImmutableMap.of(USERNAME, userName));
    }
    userRepository.deleteByUserName(userName);
  }

  public Page<UserRepresentation> getAll(int pageNum, int pageSize) {
    return userRepository.findAll(PageRequest.of(pageNum, pageSize)).map(User::toUserRepresentation);
  }

  public boolean check(String currentPassword, String password) {
    return this.bCryptPasswordEncoder.matches(currentPassword, password);
  }

  private void ensureUserNameNotExist(String userName) {
    boolean exist = userRepository.findByUserName(userName).isPresent();
    if (exist) {
      throw new UserNameAlreadyExistException(ImmutableMap.of(USERNAME, userName));
    }
  }
}
