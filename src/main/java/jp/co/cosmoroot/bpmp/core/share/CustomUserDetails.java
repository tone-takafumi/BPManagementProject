package jp.co.cosmoroot.bpmp.core.share;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jp.co.cosmoroot.bpmp.core.entity.User;

/**
 * @author cosmoroot
 *
 * 
 */
public class CustomUserDetails implements UserDetails {

    // ユーザー情報のエンティティ
    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    /**
     * 権限取得(エンティティからGrantedAuthority型へキャスト)
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toList());
    }

    /**
     * ユーザーのパスワードを返す
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * ユーザー名を返す
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * アカウントが期限切れでないかを返す
     */
    @Override
    public boolean isAccountNonExpired() {
        return user.isAccountNonExpired();
    }

    /**
     * アカウントがロックされていないかを返す
     */
    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }

    /**
     * 認証情報が期限切れでないかを返す
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return user.isCredentialsNonExpired();
    }

    /**
     * アカウントが有効かどうかを返す
     */
    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
