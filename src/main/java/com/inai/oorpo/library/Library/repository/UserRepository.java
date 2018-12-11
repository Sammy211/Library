package com.inai.oorpo.library.Library.repository;

import com.inai.oorpo.library.Library.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
