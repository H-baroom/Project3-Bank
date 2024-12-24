package com.example.project3bankw10day3.Service;

import com.example.project3bankw10day3.Api.ApiException;
import com.example.project3bankw10day3.InDTO.EmployeeInDTO;
import com.example.project3bankw10day3.Model.Account;
import com.example.project3bankw10day3.Model.Employee;
import com.example.project3bankw10day3.Model.MyUser;
import com.example.project3bankw10day3.OutDTO.EmployeeOutDTO;
import com.example.project3bankw10day3.Repository.AccountRepository;
import com.example.project3bankw10day3.Repository.AuthRepository;
import com.example.project3bankw10day3.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final AuthRepository authRepository;
    private final AccountRepository accountRepository;

    public EmployeeOutDTO getMyEmployee(Integer employee_id) {
        Employee employee =employeeRepository.findEmployeeById(employee_id);
        if(employee == null){
            throw new ApiException("Employee not found");
        }
        EmployeeOutDTO employeeOutDTO = new EmployeeOutDTO(employee.getPosition(),employee.getSalary());
        return employeeOutDTO;
    }

    public void registerEmployee(EmployeeInDTO employeeInDTO) {
        String hashPassword = new BCryptPasswordEncoder().encode(employeeInDTO.getPassword());
        MyUser myUser = new MyUser(employeeInDTO.getUsername(),hashPassword,employeeInDTO.getName(),employeeInDTO.getEmail(),"EMPLOYEE");
        Employee employee = new Employee(myUser.getId(),employeeInDTO.getPosition(),employeeInDTO.getSalary(),myUser);
        myUser.setEmployee(employee);
        employeeRepository.save(employee);
    }

    public void updateMyEmployee(Integer employee_id, Employee employee) {
        Employee employee1 =employeeRepository.findEmployeeById(employee_id);
        if(employee1 == null){
            throw new ApiException("Employee not found");
        }
        employee1.setPosition(employee.getPosition());
        employee1.setSalary(employee.getSalary());
        employeeRepository.save(employee1);
    }

    public void deleteMyEmployee(Integer employee_id) {
        Employee employee1 =employeeRepository.findEmployeeById(employee_id);
        if(employee1 == null){
            throw new ApiException("Employee not found");
        }
        MyUser user = authRepository.findMyUserById(employee_id);
        user.setEmployee(null);
        employeeRepository.delete(employee1);
        authRepository.delete(user);
    }

    public void activeBankAccountByEmployee(Integer employee_id,Integer account_id){
        Employee employee = employeeRepository.findEmployeeById(employee_id);
        if (employee == null){
            throw new ApiException("Employee not found");
        }
        Account account = accountRepository.findAccountById(account_id);
        if (account == null){
            throw new ApiException("Account not found");
        }
        account.setIsActive(true);
        accountRepository.save(account);

    }
}
