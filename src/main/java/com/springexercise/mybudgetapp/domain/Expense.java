package com.springexercise.mybudgetapp.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double expensePrice;

    @NotNull
    private String expenseName;

    @ManyToOne
    private Category category;

    @Column(updatable = false)
    @CreationTimestamp()
    private OffsetDateTime createdDate;

}
