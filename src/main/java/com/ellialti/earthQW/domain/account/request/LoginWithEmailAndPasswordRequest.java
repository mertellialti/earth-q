package com.ellialti.earthQW.domain.account.request;

import com.ellialti.earthQW.domain.Request;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginWithEmailAndPasswordRequest extends Request {
    String email;
    String password;

    public LoginWithEmailAndPasswordRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LoginWithEmailAndPasswordRequest() {
    }
}
