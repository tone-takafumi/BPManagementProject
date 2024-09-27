package jp.co.cosmoroot.bpmp.core.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

/**
 * @author cosmoroot
 *
 * ユーザエンティティ
 */
@Entity
@NamedEntityGraph(name = "User.authorities",
        attributeNodes = @NamedAttributeNode("authorities"))
@Table(name = "users")
@Data
public class User {

    // id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ユーザ名
    @Column(name = "username", columnDefinition = "VARCHAR(50)", nullable = false, unique = true)
    private String username;

    // パスワード
    @Column(name = "password", columnDefinition = "VARCHAR(100)", nullable = false, unique = true)
    private String password;

    // Eメール
    @Column(name = "email", columnDefinition = "VARCHAR(100)", nullable = false, unique = true)
    private String email;

    // 利用可能アカウントフラグ
    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    // アカウント有効期限フラグ
    @Column(name = "account_non_expired", nullable = false)
    private boolean accountNonExpired = true;

    // アカウントロックフラグ
    @Column(name = "account_non_locked", nullable = false)
    private boolean accountNonLocked = true;

    // 認証期限切れフラグ
    @Column(name = "credentials_non_expired", nullable = false)
    private boolean credentialsNonExpired = true;

    // 更新日時
    @Column(name = "upd_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date updDate;

    // 更新ユーザ
    @Column(name = "upd_apl", length = 200, nullable = false)
    private String updApl;

    // 登録日時
    @Column(name = "reg_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date regDate;

    // 登録ユーザ
    @Column(name = "reg_apl", length = 200, nullable = false)
    private String regApl;

    // バージョン
    @Column(name = "version", precision = 8, nullable = false)
    private int version;

    // 権限情報一覧
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Authorities> authorities = new ArrayList<Authorities>();
}