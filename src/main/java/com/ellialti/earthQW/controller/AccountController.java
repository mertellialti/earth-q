package com.ellialti.earthQW.controller;

import com.ellialti.earthQW.domain.Response;
import com.ellialti.earthQW.domain.account.Account;
import com.ellialti.earthQW.domain.account.request.LoginWithEmailAndPasswordRequest;
import com.ellialti.earthQW.domain.earthquake.Earthquake;
import com.ellialti.earthQW.service.AccountService;
import com.ellialti.earthQW.service.EarthquakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:4200")
    public Response Login(@RequestBody LoginWithEmailAndPasswordRequest loginWithEmailAndPasswordRequest) {
        try {
            Date startTime = new Date();
            System.out.println("Action start time " + startTime);
            Response response = accountService.Login(loginWithEmailAndPasswordRequest.getEmail(), loginWithEmailAndPasswordRequest.getPassword());
            Date endTime = new Date();
            System.out.println("Action end time " + endTime);
                return response;
        } catch (Exception e) {
            return new Response(false, "ER:CODE:[191]");
        }
    }

    @PostMapping("/get-all-accounts")
    @CrossOrigin(origins = "http://localhost:4200")
    public Response ListAccounts() {
        try {
            Date startTime = new Date();
            System.out.println("Action start time " + startTime);
            Response response = accountService.ListAccounts();
            Date endTime = new Date();
            System.out.println("Action end time " + endTime);
            return response;
        } catch (Exception e) {
            return null;
        }
    }
}
