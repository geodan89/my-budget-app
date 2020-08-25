package com.springexercise.mybudgetapp.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
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
    private String id;

    @JsonProperty("initialAmount")
    private Double initialAmount;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("currentAmount")
    private Double currentAmount;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @NotBlank
    @NotNull
    private String categoryName;

    private List<ExpenseDto> expenseList = new ArrayList<>();

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private OffsetDateTime createdDate;
}
