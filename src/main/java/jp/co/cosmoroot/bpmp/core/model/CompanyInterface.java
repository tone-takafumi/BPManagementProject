package jp.co.cosmoroot.bpmp.core.model;

import lombok.Data;

@Data
public class CompanyInterface {
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
}
