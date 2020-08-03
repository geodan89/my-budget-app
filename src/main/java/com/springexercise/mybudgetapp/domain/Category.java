package com.springexercise.mybudgetapp.domain;


import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double categoryAmount;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<Expense> expenseList = new ArrayList<>();

    private OffsetDateTime createdDate;

    public Double updateCategoryAmount() {
        for (int i = 0; i < expenseList.size(); i++) {
            if (expenseList.get(i) != null) {
                categoryAmount -= expenseList.get(i).getPrice();
            }
        }
        return categoryAmount;
    }

}
