package com.programming.techie.springngblog.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.programming.techie.springngblog.dto.LoginRequest;
import com.programming.techie.springngblog.dto.RegisterRequest;
import com.programming.techie.springngblog.model.User;
import com.programming.techie.springngblog.service.AuthService;
import com.programming.techie.springngblog.service.AuthenticationResponse;
import com.sun.net.httpserver.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private AuthService authService;



    @PostMapping("/getuserprofile")
    public String getUserProfileFromLinkedIn(@RequestBody Map<String,String> reqBody) {
        String code = reqBody.get("token");
        HttpHeaders headers=headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBearerAuth(code);
        String url="https://api.linkedin.com/v2/me";
        String forObject = restTemplate.getForObject(url, String.class);
        return forObject;
    }

    @PostMapping("/getaccesstoken")
    public String getAccessToken(@RequestBody Map<String,String> reqBody) {
        String code=reqBody.get("code");
        String linkedInAccessTokenUrl="https://www.linkedin.com/oauth/v2/accessToken";
        String body = "grant_type=authorization_code&code=" + code + "&redirect_uri=https://joinsmart.herokuapp.com/api/auth/callback&client_id=81sirvv927wpon&client_secret=t4OpCZ376aWqhOFe";
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> payload =
                new HttpEntity<String>(body, headers);
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(linkedInAccessTokenUrl, payload, String.class);
        String res=stringResponseEntity.getBody();
        String access_token = new Gson().fromJson(res, JsonElement.class).getAsJsonObject().get("access_token").toString();

        return access_token;
    }
    @GetMapping("/callback")
    public String getCallback(@RequestParam("code") String code) {
        return "callback success";
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody RegisterRequest registerRequest) {
        User signup = authService.signup(registerRequest);
        return new ResponseEntity(signup,HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
