package ssj.system.entity.info.representation;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserRepresentation {
  @Builder.Default
  private String userName = null;
  @Builder.Default
  private String fullName = null;
  @Builder.Default
  private String id = null;
}
