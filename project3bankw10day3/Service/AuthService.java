package com.example.project3bankw10day3.Service;

import com.example.project3bankw10day3.Api.ApiException;
import com.example.project3bankw10day3.InDTO.CustomerInDTO;
import com.example.project3bankw10day3.InDTO.EmployeeInDTO;
import com.example.project3bankw10day3.Model.Account;
import com.example.project3bankw10day3.Model.Customer;
import com.example.project3bankw10day3.Model.Employee;
import com.example.project3bankw10day3.Model.MyUser;
import com.example.project3bankw10day3.OutDTO.CustomerOutDTO;
import com.example.project3bankw10day3.OutDTO.MyUserOutDTO;
import com.example.project3bankw10day3.OutDTO.UserOutDTO;
import com.example.project3bankw10day3.Repository.AccountRepository;
import com.example.project3bankw10day3.Repository.AuthRepository;
import com.example.project3bankw10day3.Repository.CustomerRepository;
import com.example.project3bankw10day3.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

    public List<UserOutDTO> getAllUsers(){
        List<MyUser> users = authRepository.findAll();
        List<UserOutDTO> userOutDTO = new ArrayList<>();
        for (MyUser myUser:users){
            userOutDTO.add(new UserOutDTO(myUser.getUsername(),myUser.getName(), myUser.getEmail(), myUser.getRole()));
        }
        return userOutDTO;
    }

    public void activeBankAccountByAdmin(Integer admin_id,Integer account_id) {
        Account account = accountRepository.findAccountById(account_id);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        MyUser myUser = authRepository.findMyUserById(admin_id);
        if (myUser == null) {
            throw new ApiException("User not found");
        }
        account.setIsActive(true);
        accountRepository.save(account);
    }

    public void blockBankAccountByAdmin(Integer account_id){
        Account account = accountRepository.findAccountById(account_id);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        account.setIsActive(false);
        accountRepository.save(account);
    }



}
