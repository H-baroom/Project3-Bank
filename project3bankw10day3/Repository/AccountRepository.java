package com.example.project3bankw10day3.Repository;

import com.example.project3bankw10day3.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findAccountById(Integer id);

    List<Account> findAllByCustomerId(Integer id);
}
