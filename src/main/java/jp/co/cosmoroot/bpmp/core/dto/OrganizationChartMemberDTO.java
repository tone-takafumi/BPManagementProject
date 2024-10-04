package jp.co.cosmoroot.bpmp.core.dto;

import lombok.Data;

@Data
public class OrganizationChartMemberDTO {
    // 社員id
    private String memberID;

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

    // 顔写真
    private byte[] photo;
}
