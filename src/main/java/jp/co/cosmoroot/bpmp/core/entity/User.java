package jp.co.cosmoroot.bpmp.core.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @Column
    private Integer id;

    @Column(length = 50)
    private String username; // ユーザー名を主キーとして使用

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean enabled;

    // その他のフィールドやメソッド...

    @OneToMany(mappedBy = "user")
    private Set<Authorities> authorities;

}