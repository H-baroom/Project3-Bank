package com.example.project3bankw10day3.OutDTO;

import com.example.project3bankw10day3.Model.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountOutDTO {

    private String accountNumber;

    private Double balance;

    private Boolean isActive ;

}
