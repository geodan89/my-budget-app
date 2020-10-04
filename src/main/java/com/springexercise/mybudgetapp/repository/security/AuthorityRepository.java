package com.springexercise.mybudgetapp.repository.security;

import com.springexercise.mybudgetapp.domain.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
