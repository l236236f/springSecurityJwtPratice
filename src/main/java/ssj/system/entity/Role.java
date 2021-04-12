package ssj.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author harveylo
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "role")
@EqualsAndHashCode(callSuper=false)
public class Role extends AbstractAuditBase {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  private String id;
  private String name;
  private String description;

  @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<UserRole> userRoles = new ArrayList<>();

  public Role(String name, String description) {
    this.name = name;
    this.description = description;
  }
}


