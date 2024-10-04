package jp.co.cosmoroot.bpmp.core.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@NamedEntityGraph(name = "Department.department",
        attributeNodes = @NamedAttributeNode("parentDepartment"))
@NamedEntityGraph(name = "Department.members",
        attributeNodes = @NamedAttributeNode("parentDepartment"))
@Table(name = "department")
@Data
public class Department {
    // 部署id
    @Id
    @Column(name = "department_id", columnDefinition = "CHAR(10)")
    private String departmentID;

    // 部署名
    @Column(name = "departmnet_name", columnDefinition = "VARCHAR(50)", nullable = false)
    private String departmentName;

    // 部署概要
    @Column(name = "departmnet_information", columnDefinition = "VARCHAR(1800)", nullable = false)
    private String departmentInformation;

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

    // 親部署ID（自己参照外部キー）
    @ManyToOne
    @JoinColumn(name = "parent_department_id")
    private Department parentDepartment;

    // 子部署リスト
    @OneToMany(mappedBy = "parentDepartment")
    private List<Department> childDepartments;

    // 人員リスト
    @OneToMany(mappedBy = "parentDepartment")
    private List<Member> members = new ArrayList<Member>();
}
