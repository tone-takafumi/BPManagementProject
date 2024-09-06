package jp.co.cosmoroot.bpmp.core.model;

import java.util.Date;

import lombok.Data;

@Data
public class Company {
    // 会社ID
    private String companyID;
    // 会社名
    private String companyName;
    // 会社概要
    private String companyInformation;
    // 住所
    private String address;
    // URL
    private String url;
    // 更新日時
    private Date updateDate;
    // 更新aAPL
    private String updateAPL;
    // 登録日時
    private Date registrationDate;
    // 登録APL
    private String registrationAPL;
    // バージョン
    private int version;
}
