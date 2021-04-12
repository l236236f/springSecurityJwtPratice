package ssj.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ssj.system.entity.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

}
