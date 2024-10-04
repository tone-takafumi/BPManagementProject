package jp.co.cosmoroot.bpmp.core.dto;

import java.sql.Date;

import jp.co.cosmoroot.bpmp.core.entity.Department;
import lombok.Data;

@Data
public class MemberDTO {
    // 社員id
    private String memberID;

    // 部署
    private Department parentDepartment;

    // 役職ID
    private String roleID;

    // 役職名
    private String roleName;

    // 社員名(性)
    private String memberNameSei;

    // 社員名(名)
    private String memberNameMei;

    // 社員名(性カナ)
    private String memberNameSeiKana;

    // 社員名(名カナ)
    private String memberNameMeiKana;

    // メールアドレス
    private String emailAddress;

    // 性別
    private String gender;

    // 入社年月日
    private Date entryDate;

    // 生年月日
    private Date birthDate;

    // 年齢
    private int age;

    // 会社用の電話番号
    private String companyPhoneNumber;

    // 自己紹介
    private String selfIntroduction;

    // 顔写真
    private byte[] photo;

    // 更新日時
    private Date updDate;
}
