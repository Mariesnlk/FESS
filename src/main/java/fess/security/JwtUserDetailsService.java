package fess.security;

import fess.security.jwt.JwtUser;
import fess.security.jwt.JwtUserFactory;
import fess.service.interfaces.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    //    User user = userService.findByEmail(email);

//        if (user == null) {
//            throw new UsernameNotFoundException("User with email: " + email + " not found");
//        }

     //   JwtUser jwtUser = JwtUserFactory.create(user);
        JwtUser jwtUser = JwtUserFactory.create();
        return jwtUser;
    }
}
