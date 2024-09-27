package jp.co.cosmoroot.bpmp.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.cosmoroot.bpmp.core.dto.Login;
import jp.co.cosmoroot.bpmp.core.entity.User;
import jp.co.cosmoroot.bpmp.core.repository.UserRepository;
import jp.co.cosmoroot.bpmp.core.share.JwtTokenProvider;

/**
 * @author cosmoroot
 * 
 * ログイン認証Service
 */
@Service
@Transactional
public class LoginService {

    // パスワード暗号化に必要なエンコーダ
    private final PasswordEncoder passwordEncoder;

    // ユーザー情報を取得するためのリポジトリ
    @Autowired
    private UserRepository userRepository;

    public LoginService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * トークン生成
     * 
     * @param login ログインデータ
     * @return usernameから生成したトークン
     */
    public String createToken(Login login) {

        String token = "";

        // ユーザ取得
        User user = userRepository.findByUsername(login.getUserName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // ログイン認証
        if (this.passwordEncoder.matches(login.getPassword(), user.getPassword())) {
            // トークン生成
            JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
            token = jwtTokenProvider.createToken(user.getUsername());
        }
        return token;
    }
}
