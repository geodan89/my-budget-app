package com.springexercise.mybudgetapp.repository.security;

import com.springexercise.mybudgetapp.domain.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
