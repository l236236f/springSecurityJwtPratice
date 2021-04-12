package ssj.system.entity.info.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ssj.system.entity.User;
import ssj.system.validator.FullName;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {
  @NotBlank
  private String userName;
  @NotBlank
  private String password;
  @FullName
  @NotBlank
  private String fullName;


  public User toUser() {
    return User.builder().fullName(this.getFullName())
        .userName(this.getUserName())
        .enabled(true).build();
  }
}
