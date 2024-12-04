package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tacos.entities.User;
import tacos.repositories.UserRepository;

public class UserRepositoryUserDetailService {

    @Service
    public class UserRepositoryUserDetailsService implements UserDetailsService {

        @Autowired
        private final UserRepository userRepo;

        public UserRepositoryUserDetailsService(UserRepository userRepo) {
            this.userRepo = userRepo;
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = userRepo.findByUsername(username);
            if (user != null) {
                return user;
            }
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }
    }
}
