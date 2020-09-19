package com.springexercise.mybudgetapp.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.springexercise.mybudgetapp.domain.Category;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseDto {

    @JsonProperty("expenseId")
    @Null
    private String id;

    @NotBlank
    @NotNull
    @JsonProperty("expenseName")
    private String expenseName;

    @JsonProperty("expensePrice")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Double expensePrice;


    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @JsonProperty("createdDate")
    private OffsetDateTime createdDate;

    @Null
    @JsonIgnore
    private Category category;
}
