package com.inai.oorpo.library.Library.repository;

import com.inai.oorpo.library.Library.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
