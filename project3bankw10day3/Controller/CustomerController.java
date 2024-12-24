package com.example.project3bankw10day3.Controller;

import com.example.project3bankw10day3.Api.ApiException;
import com.example.project3bankw10day3.Api.ApiResponse;
import com.example.project3bankw10day3.InDTO.CustomerInDTO;
import com.example.project3bankw10day3.Model.Customer;
import com.example.project3bankw10day3.Model.MyUser;
import com.example.project3bankw10day3.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/get")
        public ResponseEntity getMyCustomer(@AuthenticationPrincipal MyUser user) {
        return ResponseEntity.status(200).body(customerService.getMyCustomer(user.getId()));
    }

    @PostMapping("/register-customer")
    public ResponseEntity registerCustomer(@RequestBody @Valid CustomerInDTO customerInDTO){
        customerService.registerCustomer(customerInDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Customer registered"));
    }

    @PutMapping("/update")
    public ResponseEntity updateMyCustomer(@AuthenticationPrincipal MyUser user,@RequestBody @Valid Customer customer){
        customerService.updateMyCustomer(user.getId(),customer);
        return ResponseEntity.status(200).body(new ApiResponse("Customer updated"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteMyCustomer(@AuthenticationPrincipal MyUser user){
        customerService.deleteMyCustomer(user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Customer deleted"));
    }


    @PutMapping("/deposit/{account_id}/{amount}")
    public ResponseEntity depositMoney(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer account_id,@PathVariable Double amount){
        customerService.depositMoney(myUser.getId(),account_id,amount);
        return ResponseEntity.status(200).body(new ApiResponse("Account deposited"));
    }

    @PutMapping("/withdraw/{account_id}/{amount}")
    public ResponseEntity withdrawMoney(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer account_id,@PathVariable Double amount){
        customerService.withdrawMoney(myUser.getId(),account_id,amount);
        return ResponseEntity.status(200).body(new ApiResponse("Account withdrawn"));
    }

    @PutMapping("/transfer-funds/{sourceAccount_id}/{destinationAccount_id}/{amount}")
    public ResponseEntity transferFundsBetweenMyAccount(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer sourceAccount_id,@PathVariable Integer destinationAccount_id,@PathVariable Double amount) {
        customerService.transferFundsBetweenMyAccount(myUser.getId(),sourceAccount_id,destinationAccount_id,amount);
        return ResponseEntity.status(200).body(new ApiResponse("Account transferred"));
    }

    @PutMapping("/block-my-account/{account_id}")
    public ResponseEntity blockMyAccount(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer account_id){
        customerService.blockMyAccount(myUser.getId(),account_id);
        return ResponseEntity.status(200).body(new ApiResponse("Account blocked"));
    }

}
