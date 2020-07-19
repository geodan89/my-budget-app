package com.springexercise.mybudgetapp.web.model;

import com.springexercise.mybudgetapp.domain.Expense;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

    @Null
    private Long id;

    @Positive
    private Double categoryAmount;

    @NotBlank
    private String name;

    private List<Expense> expenseList = new ArrayList<>();
}
