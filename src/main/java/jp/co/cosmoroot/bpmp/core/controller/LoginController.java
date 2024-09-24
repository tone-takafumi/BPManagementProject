package jp.co.cosmoroot.bpmp.core.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.cosmoroot.bpmp.core.dto.Login;
import jp.co.cosmoroot.bpmp.core.share.JwtTokenProvider;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login loginRequest) {
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        String token = jwtTokenProvider.createToken(loginRequest.getUserID());
        return ResponseEntity.ok(token);
    }
}