package jp.co.cosmoroot.bpmp.core.share;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.cosmoroot.bpmp.core.entity.Authorities;
import jp.co.cosmoroot.bpmp.core.entity.User;

public class JsonCreator {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Test
    public void loginCreate() throws JsonProcessingException {
        // モック用のデータ作成
        // ユーザ情報
        User user = new User();
        Date sqlDate = new Date(System.currentTimeMillis());
        user.setId(1L);
        user.setUsername("admin");
        String encodedPassword = passwordEncoder.encode("password");
        user.setPassword(encodedPassword);
        user.setEmail("t_tone@cosmoroot.co.jp");
        user.setUpdDate(sqlDate);
        user.setUpdApl("admin");
        user.setRegDate(sqlDate);
        user.setRegApl("admin");
        user.setVersion(1);

        // 権限情報
        List<Authorities> authorities = new ArrayList<Authorities>();
        Authorities authority = new Authorities();
        authority.setId(1L);
        authority.setAuthority("ROLE_ADMIN");
        authorities.add(authority);

        user.setAuthorities(authorities);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);

        // JSONを出力
        System.out.println(json);
    }
}
