package com.example.project3bankw10day3.Service;

import com.example.project3bankw10day3.Api.ApiException;
import com.example.project3bankw10day3.InDTO.AccountInDTO;
import com.example.project3bankw10day3.Model.Account;
import com.example.project3bankw10day3.Model.Customer;
import com.example.project3bankw10day3.Model.MyUser;
import com.example.project3bankw10day3.OutDTO.AccountOutDTO;
import com.example.project3bankw10day3.Repository.AccountRepository;
import com.example.project3bankw10day3.Repository.AuthRepository;
import com.example.project3bankw10day3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AuthRepository authRepository;


    public List<AccountOutDTO> getMyAccounts(Integer customer_id) {
        List<Account> allMyAccounts = accountRepository.findAllByCustomerId(customer_id);
        if (allMyAccounts.isEmpty()) {
            throw new ApiException("No accounts found");
        }
        List<AccountOutDTO> accountOutDTOS = new ArrayList<>();
        for (Account a:allMyAccounts){
            accountOutDTOS.add(new AccountOutDTO(a.getAccountNumber(),a.getBalance(),a.getIsActive()));
        }
        return accountOutDTOS;
    }


    public void addAccount(Integer customer_id,Account account) {
        Customer customer = customerRepository.findCustomerById(customer_id);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        account.setCustomer(customer);
        accountRepository.save(account);
    }

    public void updateAccount(Integer customer_id, Integer account_id, AccountInDTO accountInDTO) {
        Account account1 = accountRepository.findAccountById(account_id);
        if (account1 == null) {
            throw new ApiException("Account not found");
        }
        Customer customer = customerRepository.findCustomerById(customer_id);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        if (account1.getCustomer().getId() != customer.getId()) {
            throw new ApiException("Customer does not have this account");
        }
        account1.setBalance(accountInDTO.getBalance());
        accountRepository.save(account1);
    }
    public void deleteAccount(Integer customer_id,Integer account_id) {
        Account account = accountRepository.findAccountById(account_id);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        Customer customer = customerRepository.findCustomerById(customer_id);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        if (account.getCustomer().getId() != customer.getId()) {
            throw new ApiException("Customer does not have this account");
        }
        accountRepository.delete(account);
    }

}
