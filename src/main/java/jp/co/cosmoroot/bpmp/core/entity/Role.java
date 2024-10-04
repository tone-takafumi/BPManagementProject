package jp.co.cosmoroot.bpmp.core.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name = "role")
@NamedEntityGraph(name = "Role.member",
        attributeNodes = @NamedAttributeNode("members"))
@Data
public class Role {
    // 部署id
    @Id
    @Column(name = "role_id", columnDefinition = "CHAR(10)")
    private String roleID;

    // 部署名
    @Column(name = "role_name", columnDefinition = "VARCHAR(50)", nullable = false)
    private String roleName;

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

    // 人員リスト
    @OneToMany(mappedBy = "role")
    private List<Member> members = new ArrayList<Member>();
}
