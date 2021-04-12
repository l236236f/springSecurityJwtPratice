package ssj.system.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ssj.system.entity.Role;

import java.util.Optional;


/**
 * @author harveylo
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(String roleName);
}
