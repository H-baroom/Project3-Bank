package com.example.project3bankw10day3.InDTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class AccountInDTO {
    @Positive(message = "balance not valid")
    private Double balance;
}
