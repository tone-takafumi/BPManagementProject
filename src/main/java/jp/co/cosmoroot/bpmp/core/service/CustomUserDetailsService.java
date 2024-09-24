package jp.co.cosmoroot.bpmp.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.cosmoroot.bpmp.core.entity.User;
import jp.co.cosmoroot.bpmp.core.repository.UserRepository;
import jp.co.cosmoroot.bpmp.core.share.CustomUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; // ユーザー情報を取得するためのリポジトリ

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // ユーザーをデータベースから取得
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // UserDetails を返す
        return new CustomUserDetails(user);
    }
}