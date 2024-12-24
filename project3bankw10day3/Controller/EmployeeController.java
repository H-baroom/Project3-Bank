package com.example.project3bankw10day3.Controller;

import com.example.project3bankw10day3.Api.ApiResponse;
import com.example.project3bankw10day3.InDTO.EmployeeInDTO;
import com.example.project3bankw10day3.Model.Employee;
import com.example.project3bankw10day3.Model.MyUser;
import com.example.project3bankw10day3.Repository.EmployeeRepository;
import com.example.project3bankw10day3.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/get")
    public ResponseEntity getMyEmployee(@AuthenticationPrincipal MyUser user) {
        return ResponseEntity.status(200).body(employeeService.getMyEmployee(user.getId()));
    }

    @PostMapping("/register-employee")
    public ResponseEntity registerEmployee(@RequestBody @Valid EmployeeInDTO employeeInDTO){
        employeeService.registerEmployee(employeeInDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Employee registered"));
    }

    @PutMapping("/update")
    public ResponseEntity updateEmployee(@AuthenticationPrincipal MyUser user,@RequestBody @Valid Employee employee) {
        employeeService.updateMyEmployee(user.getId(), employee);
        return ResponseEntity.status(200).body(new ApiResponse("Employee updated"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteEmployee(@AuthenticationPrincipal MyUser user) {
        employeeService.deleteMyEmployee(user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Employee deleted"));
    }

    @PutMapping("/activation-by-employee/{account_id}")
    public ResponseEntity activeBankAccountByEmployee(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer account_id) {
        employeeService.activeBankAccountByEmployee(myUser.getId(),account_id);
        return ResponseEntity.status(200).body(new ApiResponse("Account activated by employee"));
    }
}
