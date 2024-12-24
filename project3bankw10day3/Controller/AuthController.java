package com.example.project3bankw10day3.Controller;

import com.example.project3bankw10day3.Api.ApiResponse;
import com.example.project3bankw10day3.InDTO.CustomerInDTO;
import com.example.project3bankw10day3.InDTO.EmployeeInDTO;
import com.example.project3bankw10day3.Model.MyUser;
import com.example.project3bankw10day3.Service.AuthService;
import com.example.project3bankw10day3.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/get-all-users")
    public ResponseEntity getAllUsers(@AuthenticationPrincipal MyUser user){
        return ResponseEntity.status(200).body(authService.getAllUsers());
    }

    @PutMapping("/activation-by-admin/{account_id}")
    public ResponseEntity activeBankAccountByAdmin(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer account_id) {
        authService.activeBankAccountByAdmin(myUser.getId(),account_id);
        return ResponseEntity.status(200).body(new ApiResponse("Account activated by admin"));
    }
    @PutMapping("/block-account-by-admin/{account_id}")
    public ResponseEntity blockBankAccountByAdmin(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer account_id){
        authService.blockBankAccountByAdmin(account_id);
        return ResponseEntity.status(200).body(new ApiResponse("Account blocked by admin"));
    }
}
