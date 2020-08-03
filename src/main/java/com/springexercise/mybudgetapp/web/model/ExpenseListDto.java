package com.springexercise.mybudgetapp.web.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ExpenseListDto {
    private List<ExpenseDto> expenseDtoList = new ArrayList<>();
}
