package com.example.project3bankw10day3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    private Integer id;

    @NotEmpty(message = "phoneNumber not valid")
    @Size(max = 10)
    @Pattern(regexp = "^05\\d{8}$", message = "Phone number must start with 05 and be 10 digits")
    @Column(columnDefinition = "varchar(10) unique")
    private String phoneNumber;

    @OneToOne
    @MapsId
    @JsonIgnore
    private MyUser user;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer" )
    private Set<Account> accounts;


    public Customer(Integer id, String phoneNumber, MyUser user) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.user = user;
    }
}
