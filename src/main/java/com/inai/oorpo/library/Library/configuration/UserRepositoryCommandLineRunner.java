package com.inai.oorpo.library.Library.configuration;

import com.inai.oorpo.library.Library.models.Book;
import com.inai.oorpo.library.Library.models.Role;
import com.inai.oorpo.library.Library.models.User;
import com.inai.oorpo.library.Library.repository.BookRepository;
import com.inai.oorpo.library.Library.repository.RoleRepository;
import com.inai.oorpo.library.Library.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserRepositoryCommandLineRunner implements CommandLineRunner{
    private static final Logger log = LoggerFactory.getLogger(UserRepositoryCommandLineRunner.class);

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) {
        log.info("Create users");
        Role role = new Role();
        role.setId(1);
        role.setName("ADMIN");
        roleRepository.save(role);
        roleRepository.save(new Role("USER"));

        User user = new User();
        Set<Role> rl = new HashSet<Role>();
        rl.add(role);

        user.setUsername("admin");
        user.setName("Admin");
        user.setRoles(rl);
        user.setActive(true);
        user.setPassword(bCryptPasswordEncoder.encode("admin123"));

        bookRepository.save(new Book("Белый пароход", "Чынгыз Айтматов", "1998"));
        bookRepository.save(new Book("Плаха", "Чынгыз Айтматов", "1998"));
        bookRepository.save(new Book("Плasdfasdfasdfаха", "Чынгыз Айтматов", "1998"));

        userRepository.save(user);
    }

}
