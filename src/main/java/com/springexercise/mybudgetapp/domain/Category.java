package com.springexercise.mybudgetapp.domain;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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

    private Double initialAmount;

    private Double currentAmount;

    private String categoryName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<Expense> expenseList = new ArrayList<>();

    @Column(updatable = false)
    @CreationTimestamp
    private OffsetDateTime createdDate;

    public Double updateCurrentAmount() {
        for (int i = 0; i < this.expenseList.size(); i++) {
            if (this.expenseList.get(i) != null) {
                this.currentAmount -= this.expenseList.get(i).getExpensePrice();
            }
        }
        return this.currentAmount;
    }
}
