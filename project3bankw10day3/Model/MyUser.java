package com.example.project3bankw10day3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MyUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "username not valid")
    @Size(min = 4,max = 10,message = " Length must be between 4 and 10 characters.")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String username;

    @NotEmpty(message = "password not valid")
    @Column(columnDefinition = "varchar(150) not null")
    private String password;

    @NotEmpty(message = "name not valid")
    @Size(min = 2,max = 20,message = " Length must be between 4 and 10 characters.")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotEmpty(message = "email not valid")
    @Email(message = "Must be a valid email format")
    @Column(columnDefinition = "varchar(150) not null unique")
    private String email;

    @NotEmpty(message = "role not valid")
    @Pattern(regexp = "^(CUSTOMER|ADMIN|EMPLOYEE)")
    @Column(columnDefinition = "varchar(150) not null")
    private String role;




    @OneToOne
    @PrimaryKeyJoinColumn
    private Customer customer;


    @OneToOne
    @PrimaryKeyJoinColumn
    private Employee employee;

    public MyUser(@NotEmpty(message = "username not valid") @Size(min = 4,max = 10,message = " Length must be between 4 and 10 characters.") String username, String hashPassword, @NotEmpty(message = "name not valid") @Size(min = 2,max = 20,message = " Length must be between 4 and 10 characters.") String name, @NotEmpty(message = "email not valid") @Email(message = "Must be a valid email format") String email, String role) {
        this.username = username;
        this.password = hashPassword;
        this.name = name;
        this.email = email;
        this.role = role;
    }
    public MyUser(@NotEmpty(message = "username not valid") @Size(min = 4,max = 10,message = " Length must be between 4 and 10 characters.") String username, String hashPassword, @NotEmpty(message = "name not valid") @Size(min = 2,max = 20,message = " Length must be between 4 and 10 characters.") String name, @NotEmpty(message = "email not valid") @Email(message = "Must be a valid email format") String email) {
        this.username = username;
        this.password = hashPassword;
        this.name = name;
        this.email = email;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
