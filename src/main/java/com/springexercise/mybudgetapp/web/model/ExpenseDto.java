package com.springexercise.mybudgetapp.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseDto {

    @JsonProperty("expenseId")
    @Null
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Positive
    private Double expensePrice;

    @NotBlank
    private String expenseName;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private OffsetDateTime createdDate;
}
