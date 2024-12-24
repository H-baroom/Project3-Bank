package com.example.project3bankw10day3.OutDTO;

import com.example.project3bankw10day3.Model.Account;
import com.example.project3bankw10day3.Model.MyUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class CustomerOutDTO {

    private String phoneNumber;

    private MyUserOutDTO user;

    private List<AccountOutDTO> accounts;

}
