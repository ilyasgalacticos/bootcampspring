package kz.bootcamp.springboot.springBootcamp.services;

import kz.bootcamp.springboot.springBootcamp.entities.Roles;
import kz.bootcamp.springboot.springBootcamp.entities.Users;
import kz.bootcamp.springboot.springBootcamp.repositories.RoleRepository;
import kz.bootcamp.springboot.springBootcamp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Users user = userRepository.findByEmail(s);

        if(user!=null){
            return user;
        }else{
            throw new UsernameNotFoundException("USER NOT FOUND");
        }

    }

    @Override
    public Users getUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Users addUser(Users user) {

        Users checkUser = userRepository.findByEmail(user.getEmail());
        if(checkUser==null){
            Roles userRole = roleRepository.findByRole("ROLE_USER");
            ArrayList<Roles> roles = new ArrayList<>();
            roles.add(userRole);
            user.setRoles(roles);
            return userRepository.save(user);
        }

        return null;
    }

    @Override
    public Users updateUser(Users user) {
        return userRepository.save(user);
    }
}
