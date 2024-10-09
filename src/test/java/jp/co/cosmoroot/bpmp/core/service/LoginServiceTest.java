package jp.co.cosmoroot.bpmp.core.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.cosmoroot.bpmp.core.dto.Login;
import jp.co.cosmoroot.bpmp.core.entity.User;
import jp.co.cosmoroot.bpmp.core.repository.UserRepository;
import jp.co.cosmoroot.bpmp.core.share.JwtTokenProvider;

class LoginServiceTest extends LoginService {

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private LoginService loginService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.jwtTokenProvider = new JwtTokenProvider();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void loginSucceed() throws StreamReadException, DatabindException, IOException {
        String username = "admin";
        String password = "password";

        // パスワード暗号化に必要なエンコーダ
        String passwordEncode = passwordEncoder.encode(password);

        // リポジトリリクエスト
        Login loginDto = new Login();
        loginDto.setUserName(username);
        loginDto.setPassword(password);

        // リポジトリレスポンス
        Map<String, User> jsonData = objectMapper.readValue(
                new File("src/test/resources/entity/loginData.json"),
                new TypeReference<Map<String, User>>() {
                });
        User mockUser = new User();
        mockUser.setUsername(username);
        mockUser.setPassword(passwordEncode);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));

        // 実行
        String token = loginService.createToken(loginDto);

        String user = jwtTokenProvider.getUsernameFromJWT(token);
        assertEquals(user, mockUser.getUsername());
    }

    @Test
    void loginPasswordFailedByPassword() {
        String username = "admin";
        String password = "password";

        // パスワード暗号化に必要なエンコーダ
        String passwordEncode = passwordEncoder.encode(password);

        // リポジトリリクエスト
        Login loginDto = new Login();
        loginDto.setUserName(username);
        loginDto.setPassword(password);

        // リポジトリレスポンス
        User mockUser = new User();
        mockUser.setUsername(username);
        mockUser.setPassword(passwordEncode);
        when(userRepository.findByUsername(username)).thenThrow(new UsernameNotFoundException("User not found"));

        // 実行&検証
        Exception exception = assertThrows(UsernameNotFoundException.class, () -> {
            loginService.createToken(loginDto);
        });

        assertEquals("User not found", exception.getMessage());
    }

}
