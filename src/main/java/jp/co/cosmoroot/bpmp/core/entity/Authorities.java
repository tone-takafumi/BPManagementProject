package jp.co.cosmoroot.bpmp.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author cosmoroot
 *
 * 権限エンティティ
 */
@Entity
@Table(name = "authorities")
@Data
public class Authorities {
    // id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 権限
    @Column(name = "authority", nullable = false)
    private String authority;

    // ユーザID
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user; // User エンティティとのリレーション
}
