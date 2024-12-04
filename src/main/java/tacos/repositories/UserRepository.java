package tacos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tacos.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);
}
