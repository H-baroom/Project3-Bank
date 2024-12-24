package com.example.project3bankw10day3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "accountNumber not valid")
    @Pattern(regexp = "^\\d{4}-\\d{4}-\\d{4}-\\d{4}$", message = "Account number must follow the format XXXX-XXXX-XXXX-XXXX.")
    @Column(columnDefinition = "varchar(20) not null")
    private String accountNumber;

    @Positive(message = "balance not valid")
    @Column(columnDefinition = "Double not null")
    private Double balance;


    @Column(columnDefinition = "Boolean default false")
    private Boolean isActive = false;


    @ManyToOne
    @JsonIgnore
    private Customer customer;
}
