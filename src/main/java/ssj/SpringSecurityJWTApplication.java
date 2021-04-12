package ssj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ssj.system.entity.Role;
import ssj.system.entity.User;
import ssj.system.entity.UserRole;
import ssj.system.enums.RoleType;
import ssj.system.repository.RoleRepository;
import ssj.system.repository.UserRepository;
import ssj.system.repository.UserRoleRepository;

/**
 * @author harveylo
 */
@SpringBootApplication
public class SpringSecurityJWTApplication implements CommandLineRunner {

  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UserRoleRepository userRoleRepository;

  public static void main(java.lang.String[] args) {
    SpringApplication.run(SpringSecurityJWTApplication.class, args);
  }

  @Override
  public void run(java.lang.String... args) {
    //初始化角色
    for (RoleType roleType : RoleType.values()) {
      roleRepository.save(new Role(roleType.getName(), roleType.getDescription()));
    }
    //初始化一个 admin 用戶
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    User user = User.builder().enabled(true).fullName("admin").userName("root").password(bCryptPasswordEncoder.encode("root")).build();
    userRepository.save(user);
    Role role = roleRepository.findByName(RoleType.ADMIN.getName()).get();
    userRoleRepository.save(new UserRole(user, role));
  }
}