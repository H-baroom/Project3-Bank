package com.example.project3bankw10day3.InDTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class CustomerInDTO {

    private Integer customerId;

    @NotEmpty(message = "phoneNumber not valid")
    @Size(max = 10)
    @Pattern(regexp = "^05\\d{8}$", message = "Phone number must start with 05 and be 10 digits")
    @Column(columnDefinition = "varchar(10) unique")
    private String phoneNumber;

    @NotEmpty(message = "username not valid")
    @Size(min = 4,max = 10,message = " Length must be between 4 and 10 characters.")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String username;

    @NotEmpty(message = "password not valid")
    private String password;

    @NotEmpty(message = "name not valid")
    @Size(min = 2,max = 20,message = " Length must be between 4 and 10 characters.")
    private String name;

    @NotEmpty(message = "email not valid")
    @Email(message = "Must be a valid email format")
    private String email;


}
