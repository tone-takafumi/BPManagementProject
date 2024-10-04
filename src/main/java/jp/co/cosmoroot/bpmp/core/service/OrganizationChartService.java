package jp.co.cosmoroot.bpmp.core.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.co.cosmoroot.bpmp.core.dto.OrganizationChartDTO;
import jp.co.cosmoroot.bpmp.core.dto.OrganizationChartMemberDTO;
import jp.co.cosmoroot.bpmp.core.entity.Department;
import jp.co.cosmoroot.bpmp.core.entity.Member;
import jp.co.cosmoroot.bpmp.core.repository.DepartmentRepository;

@Service
@Transactional
public class OrganizationChartService {

    @Autowired
    DepartmentRepository departmentRepository;

    public List<OrganizationChartDTO> organizationChartViewAll() {

        List<Department> departmentList = departmentRepository.findByDepartmentID("0000000001");
        List<OrganizationChartDTO> organizationChartDTO = departmentList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        return organizationChartDTO;
    }

    private OrganizationChartDTO mapToDTO(Department department) {
        OrganizationChartDTO dto = new OrganizationChartDTO();
        dto.setDepartmentID(department.getDepartmentID());
        dto.setDepartmentName(department.getDepartmentName());
        dto.setDepartmentInformation(department.getDepartmentInformation());
        List<OrganizationChartDTO> childOrganization = department.getChildDepartments().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        dto.setChildDepartments(childOrganization);
        List<OrganizationChartMemberDTO> members = department.getMembers().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        dto.setMembers(members);
        dto.setMemberNum(members.size());
        return dto;
    }

    private OrganizationChartMemberDTO mapToDTO(Member member) {
        OrganizationChartMemberDTO dto = new OrganizationChartMemberDTO();
        dto.setMemberID(member.getMemberID());
        dto.setRoleName(member.getRole().getRoleName());
        dto.setMemberNameSei(member.getMemberNameSei());
        dto.setMemberNameMei(member.getMemberNameMei());
        dto.setPhoto(member.getPhoto());
        return dto;
    }
}
