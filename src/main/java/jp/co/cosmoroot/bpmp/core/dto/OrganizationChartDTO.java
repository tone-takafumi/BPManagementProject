package jp.co.cosmoroot.bpmp.core.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * @author cosmoroot
 *
 * 組織図構成画面
 */
@Data
public class OrganizationChartDTO {
    // 部署id
    private String departmentID;

    // 部署名
    private String departmentName;

    // 部署概要
    private String departmentInformation;

    // 子部署リスト
    private List<OrganizationChartDTO> childDepartments;

    // 人員リスト
    private List<OrganizationChartMemberDTO> members = new ArrayList<OrganizationChartMemberDTO>();

    // 部署内人数
    private int memberNum;
}