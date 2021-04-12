package ssj.system.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

  @Data
  @NoArgsConstructor
  @Entity
  @Table(name = "user_role")
  public class UserRole extends AbstractAuditBase implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private Role role;

    public UserRole(User user, Role role) {
      this.user = user;
      this.role = role;
    }
}
