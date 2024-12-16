package tacos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import tacos.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);
}




