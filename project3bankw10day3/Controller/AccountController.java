package com.example.project3bankw10day3.Controller;

import com.example.project3bankw10day3.Api.ApiResponse;
import com.example.project3bankw10day3.InDTO.AccountInDTO;
import com.example.project3bankw10day3.Model.Account;
import com.example.project3bankw10day3.Model.MyUser;
import com.example.project3bankw10day3.Service.AccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/get")
    public ResponseEntity getMyAccounts(@AuthenticationPrincipal MyUser myUser) {
        accountService.getMyAccounts(myUser.getId());
        return ResponseEntity.status(200).body(accountService.getMyAccounts(myUser.getId()));
    }
    @PostMapping("/add")
    public ResponseEntity addAccount(@AuthenticationPrincipal MyUser myUser,@Valid @RequestBody Account account) {
        accountService.addAccount(myUser.getId(), account);
        return ResponseEntity.status(200).body(new ApiResponse("Account added"));
    }

    @PutMapping("/update/{account_id}")
    public ResponseEntity updateMyAccounts(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer account_id,@RequestBody @Valid AccountInDTO accountInDTO) {
        accountService.updateAccount(myUser.getId(), account_id, accountInDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Account updated"));
    }

    @DeleteMapping("/delete/{account_id}")
    public ResponseEntity deleteAccount(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer account_id) {
        accountService.deleteAccount(myUser.getId(), account_id);
        return ResponseEntity.status(200).body(new ApiResponse("Account deleted"));
    }

}
