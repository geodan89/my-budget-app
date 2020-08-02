package com.springexercise.mybudgetapp.web.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class ExpenseListDto {
    private List<ExpenseDto> expenseDtoList = new ArrayList<>();
}
