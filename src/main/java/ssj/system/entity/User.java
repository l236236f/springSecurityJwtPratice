package ssj.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ssj.system.entity.info.representation.UserRepresentation;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author harveylo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
@EqualsAndHashCode(callSuper=false)
public class User extends AbstractAuditBase{
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  private String id;
  @Column(nullable = false)
  private String userName;
  @Column(nullable = false)
  private String fullName;
  @Column(nullable = false)
  private String password;
  @Column(columnDefinition = "tinyint(1) default 1")
  private Boolean enabled;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<UserRole> userRoles = new ArrayList<>();

  public List<SimpleGrantedAuthority> getRoles() {
    List<Role> roles = userRoles.stream().map(UserRole::getRole).collect(Collectors.toList());
    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
    return authorities;
  }

  public UserRepresentation toUserRepresentation() {
    return UserRepresentation.builder()
        .fullName(this.fullName)
        .id(this.id)
        .userName(this.userName).build();
  }

}
