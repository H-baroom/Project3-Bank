package com.example.project3bankw10day3.OutDTO;

import com.example.project3bankw10day3.Model.Customer;
import com.example.project3bankw10day3.Model.Employee;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserOutDTO {
    private String username;

    private String name;

    private String email;

    private String role;

}
