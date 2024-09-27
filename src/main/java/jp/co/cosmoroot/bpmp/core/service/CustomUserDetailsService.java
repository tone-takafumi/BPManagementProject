package jp.co.cosmoroot.bpmp.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.co.cosmoroot.bpmp.core.entity.User;
import jp.co.cosmoroot.bpmp.core.repository.UserRepository;
import jp.co.cosmoroot.bpmp.core.share.CustomUserDetails;

/**
 * @author cosmoroot
 *
 * ユーザ情報取得サービス
 */
@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    // ユーザー情報を取得するためのリポジトリ
    @Autowired
    private UserRepository userRepository;

    /**
     * ユーザ情報の取得
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // ユーザーをデータベースから取得
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // UserDetails を返す
        return new CustomUserDetails(user);
    }
}