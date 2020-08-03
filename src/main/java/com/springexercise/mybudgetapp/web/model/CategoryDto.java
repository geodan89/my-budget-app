package com.springexercise.mybudgetapp.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.springexercise.mybudgetapp.domain.Expense;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

    @JsonProperty("categoryId")
    @Null
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Positive
    private Double categoryAmount;

    @NotBlank
    private String categoryName;

    private List<Expense> expenseList = new ArrayList<>();

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private OffsetDateTime createdDate;
}
