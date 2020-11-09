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

    @NotBlank
    @NotNull
    @JsonProperty("categoryName")
    private String categoryName;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("initialAmount")
    private Double initialAmount;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Null
    @JsonProperty("currentAmount")
    private Double currentAmount;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @JsonProperty("createdDate")
    private OffsetDateTime createdDate;

    @JsonProperty("expenseList")
    private List<ExpenseDto> expenseList = new ArrayList<>();

}
