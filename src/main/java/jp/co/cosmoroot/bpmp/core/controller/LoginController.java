package jp.co.cosmoroot.bpmp.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.cosmoroot.bpmp.core.dto.Login;
import jp.co.cosmoroot.bpmp.core.service.LoginService;

/**
 * @author cosmoroot
 *
 * Loginコントローラ
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
@Controller
public class LoginController {

    // ログインサービス
    @Autowired
    private LoginService loginService;

    /**
     * ログイン認証API
     * 
     * @param loginRequest ログインデータ
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login loginRequest) {
        String encodedPassword = loginService.createToken(loginRequest);

        return ResponseEntity.ok(encodedPassword);
    }
}