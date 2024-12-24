package com.example.project3bankw10day3.Service;

import com.example.project3bankw10day3.Api.ApiException;
import com.example.project3bankw10day3.InDTO.CustomerInDTO;
import com.example.project3bankw10day3.Model.Account;
import com.example.project3bankw10day3.Model.Customer;
import com.example.project3bankw10day3.Model.MyUser;
import com.example.project3bankw10day3.OutDTO.AccountOutDTO;
import com.example.project3bankw10day3.OutDTO.CustomerOutDTO;
import com.example.project3bankw10day3.OutDTO.MyUserOutDTO;
import com.example.project3bankw10day3.Repository.AccountRepository;
import com.example.project3bankw10day3.Repository.AuthRepository;
import com.example.project3bankw10day3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final AuthRepository authRepository;


    public CustomerOutDTO getMyCustomer(Integer customer_id) {
        Customer customer = customerRepository.findCustomerById(customer_id);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        List<AccountOutDTO> accounts = new ArrayList<>();
        for (Account account:customer.getAccounts()){
            accounts.add(new AccountOutDTO(account.getAccountNumber(), account.getBalance(), account.getIsActive()));
        }
        MyUserOutDTO myUserOutDTO = new MyUserOutDTO(customer.getUser().getUsername(),customer.getUser().getName(),customer.getUser().getEmail());
        CustomerOutDTO customerOutDTO = new CustomerOutDTO(customer.getPhoneNumber(),myUserOutDTO,accounts);
        return customerOutDTO;
    }

    public void registerCustomer(CustomerInDTO customerInDTO) {
        String hashPassword = new BCryptPasswordEncoder().encode(customerInDTO.getPassword());
        MyUser myUser = new MyUser(customerInDTO.getUsername(),hashPassword,customerInDTO.getName(),customerInDTO.getEmail(),"CUSTOMER");
        Customer customer = new Customer(myUser.getId(),customerInDTO.getPhoneNumber(),myUser);
        customer.setPhoneNumber(customerInDTO.getPhoneNumber());
        myUser.setCustomer(customer);
        customerRepository.save(customer);
    }

    public void updateMyCustomer(Integer customer_id,Customer customer){
        Customer customer1 = customerRepository.findCustomerById(customer_id);
        if (customer1 ==null){
            throw new ApiException("Customer not found");
        }
        customer1.setPhoneNumber(customer.getPhoneNumber());
        customerRepository.save(customer1);
    }

    public void deleteMyCustomer(Integer customer_id){
        Customer customer1 = customerRepository.findCustomerById(customer_id);
        if (customer1 == null){
            throw new ApiException("Customer not found");
        }
        MyUser myUser = authRepository.findMyUserById(customer_id);
        myUser.setCustomer(null);
        customerRepository.delete(customer1);
        authRepository.delete(myUser);
    }

    public void depositMoney(Integer customer_id,Integer account_id,Double amount) {
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
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    public void withdrawMoney(Integer customer_id,Integer account_id,Double amount) {
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
        if (account.getBalance() < amount) {
            throw new ApiException("Insufficient balance");
        }
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }

    public void transferFundsBetweenMyAccount(Integer customer_id,Integer sourceAccount_id,Integer destinationAccount_id, Double amount) {
        Account sourceAccount = accountRepository.findAccountById(sourceAccount_id);
        if (sourceAccount == null) {
            throw new ApiException("Source Account not found");
        }
        Customer customer = customerRepository.findCustomerById(customer_id);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        if (sourceAccount.getCustomer().getId() != customer.getId()) {
            throw new ApiException("Customer does not have this account");
        }
        if (sourceAccount.getBalance() < amount) {
            throw new ApiException("Insufficient balance");
        }
        Account destinationAccount = accountRepository.findAccountById(destinationAccount_id);
        if (destinationAccount == null) {
            throw new ApiException("Destination Account_id not found");
        }
        sourceAccount.setBalance(sourceAccount.getBalance() - amount);
        destinationAccount.setBalance(destinationAccount.getBalance() + amount);
        accountRepository.save(sourceAccount);
        accountRepository.save(destinationAccount);
    }

    public void  blockMyAccount(Integer customer_id,Integer account_id) {
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
        account.setIsActive(false);
        accountRepository.save(account);
    }
}
