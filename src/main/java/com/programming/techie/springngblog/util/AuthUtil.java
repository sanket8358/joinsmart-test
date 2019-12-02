package com.programming.techie.springngblog.util;

import com.google.gson.JsonObject;
import com.programming.techie.springngblog.dto.LoginRequest;
import com.programming.techie.springngblog.dto.RegisterRequest;
import com.programming.techie.springngblog.model.User;
import com.programming.techie.springngblog.repository.UserRepository;
import com.programming.techie.springngblog.service.AuthService;
import com.programming.techie.springngblog.service.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthUtil {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    public boolean isEmailAlreadyRegistered(String email){
        Optional<User> byEmail = userRepository.findByEmail(email);
        return byEmail.isPresent();
    }

    public ResponseEntity checkLinkedInLoginAndSignup(JsonObject userData){
        RegisterRequest user =new RegisterRequest();
        user.setEmail(userData.getAsJsonArray("elements").get(0).getAsJsonObject().getAsJsonObject("handle~").get("emailAddress").getAsString());
        if(isEmailAlreadyRegistered(user.getEmail())) {
            LoginRequest req=new LoginRequest();
            req.setEmail(user.getEmail());
            req.setPassword(userData.get("id").getAsString());
            AuthenticationResponse login = authService.login(req);
            return new ResponseEntity(login, HttpStatus.OK);
        }
        else{
            user.setPassword(userData.get("id").toString());
            user.setLastName(userData.get("localizedLastName").getAsString());
            user.setFirstName(userData.get("localizedFirstName").getAsString());
            return new ResponseEntity(authService.signup(user),HttpStatus.OK);
        }

    }
}
