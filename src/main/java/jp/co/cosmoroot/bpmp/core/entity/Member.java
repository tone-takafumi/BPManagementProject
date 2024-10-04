package jp.co.cosmoroot.bpmp.core.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@NamedEntityGraph(name = "Member.department",
        attributeNodes = @NamedAttributeNode("parentDepartment"))
@NamedEntityGraph(name = "Member.role",
        attributeNodes = @NamedAttributeNode("role"))
@Table(name = "member")
@Data
public class Member {
    // 社員id
    @Id
    @Column(name = "member_id", columnDefinition = "CHAR(10)")
    private String memberID;

    // 部署ID（外部キー）
    @ManyToOne
    @JoinColumn(name = "department_id", columnDefinition = "CHAR(10)")
    private Department parentDepartment;

    // 部署ID（外部キー）
    @ManyToOne
    @JoinColumn(name = "role_id", columnDefinition = "CHAR(10)")
    private Role role;

    // 社員名(性)
    @Column(name = "member_name_sei", columnDefinition = "VARCHAR(50)", nullable = false)
    private String memberNameSei;

    // 社員名(名)
    @Column(name = "member_name_mei", columnDefinition = "VARCHAR(50)", nullable = false)
    private String memberNameMei;

    // 社員名(性カナ)
    @Column(name = "member_name_sei_kana", columnDefinition = "VARCHAR(50)", nullable = false)
    private String memberNameSeiKana;

    // 社員名(名カナ)
    @Column(name = "member_name_mei_kana", columnDefinition = "VARCHAR(50)", nullable = false)
    private String memberNameMeiKana;

    // メールアドレス
    @Column(name = "email_address", columnDefinition = "VARCHAR(255)", nullable = false)
    private String emailAddress;

    // 性別
    @Column(name = "gender", columnDefinition = "CHAR(1)", nullable = false)
    private String gender;

    // 入社年月日
    @Column(name = "entry_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date entryDate;

    // 生年月日
    @Column(name = "birth_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    // 年齢
    @Column(name = "age", precision = 3, nullable = false)
    private int age;

    // 会社用の電話番号
    @Column(name = "company_phone_number", columnDefinition = "VARCHAR(15)", nullable = false)
    private String companyPhoneNumber;

    // 自己紹介
    @Column(name = "self_introduction", columnDefinition = "VARCHAR(1800)", nullable = false)
    private String selfIntroduction;

    // 顔写真
    @Lob
    @Column(name = "photo")
    private byte[] photo;

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
}
