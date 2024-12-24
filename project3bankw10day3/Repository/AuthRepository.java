package com.example.project3bankw10day3.Repository;

import com.example.project3bankw10day3.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<MyUser, Integer> {
    MyUser findMyUserByUsername(String username);
    MyUser findMyUserById(Integer id);
}
