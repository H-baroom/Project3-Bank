package com.example.project3bankw10day3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
public class Employee {
    @Id
    private Integer id;

    @NotEmpty(message = "position not valid")
    @Column(columnDefinition = "varchar(25) not null")
    private String position;

    @Positive(message = "salary not valid")
    @Column(columnDefinition = "Double not null")
    private Double salary;


    @OneToOne
    @MapsId
    @JsonIgnore
    private MyUser user;
}
