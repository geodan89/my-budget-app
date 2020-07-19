package com.springexercise.mybudgetapp.repository;

import com.springexercise.mybudgetapp.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
