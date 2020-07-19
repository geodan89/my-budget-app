package com.springexercise.mybudgetapp.repository;

import com.springexercise.mybudgetapp.domain.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
