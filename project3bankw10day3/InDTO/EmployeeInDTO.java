package com.example.project3bankw10day3.InDTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class EmployeeInDTO {

    @NotEmpty(message = "username not valid")
    @Size(min = 4,max = 10,message = " Length must be between 4 and 10 characters.")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String username;

    @NotEmpty(message = "password not valid")
    @Column(columnDefinition = "varchar(150) not null")
    private String password;

    @NotEmpty(message = "position not valid")
    @Column(columnDefinition = "varchar(25) not null")
    private String position;

    @Positive(message = "salary not valid")
    @Column(columnDefinition = "Double not null")
    private Double salary;

    @NotEmpty(message = "name not valid")
    @Size(min = 2,max = 20,message = " Length must be between 4 and 10 characters.")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotEmpty(message = "email not valid")
    @Email(message = "Must be a valid email format")
    @Column(columnDefinition = "varchar(150) not null unique")
    private String email;

}
