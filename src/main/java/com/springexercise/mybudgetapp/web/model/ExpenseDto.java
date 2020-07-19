package com.springexercise.mybudgetapp.web.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseDto {

    @Null
    private Long id;

    @Positive
    private Double price;

    @NotBlank
    private String name;
}
