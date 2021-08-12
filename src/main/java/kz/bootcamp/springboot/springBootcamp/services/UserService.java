package kz.bootcamp.springboot.springBootcamp.services;

import kz.bootcamp.springboot.springBootcamp.entities.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    Users getUser(String email);
    Users addUser(Users user);
    Users updateUser(Users user);

}
