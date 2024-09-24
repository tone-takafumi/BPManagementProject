package jp.co.cosmoroot.bpmp.core.share;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jp.co.cosmoroot.bpmp.core.entity.User;

public class CustomUserDetails implements UserDetails {

    private User user; // ユーザー情報のエンティティ

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        // ユーザーのパスワードを返す
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        // ユーザー名を返す
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // アカウントが期限切れでないかを返す
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // アカウントがロックされていないかを返す
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 認証情報が期限切れでないかを返す
        return true;
    }

    @Override
    public boolean isEnabled() {
        // アカウントが有効かどうかを返す
        return true;
    }
}