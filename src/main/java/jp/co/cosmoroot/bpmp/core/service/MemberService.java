package jp.co.cosmoroot.bpmp.core.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jp.co.cosmoroot.bpmp.core.dto.MemberDTO;
import jp.co.cosmoroot.bpmp.core.entity.Member;
import jp.co.cosmoroot.bpmp.core.entity.Role;
import jp.co.cosmoroot.bpmp.core.repository.MemberRepository;

@Service
@Transactional
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public MemberDTO viewMemberDetail(String memberID) {
        MemberDTO memberEntity = memberRepository.findByMemberID(memberID)
                .map(this::mapToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));
        return memberEntity;
    }

    public MemberDTO createMember(MemberDTO memberDTO) {
        Member entity = mapToEntity(memberDTO);
        return mapToDTO(memberRepository.save(entity));
    }

    public MemberDTO uploadPhoto(String memberId, MultipartFile file) throws IOException {
        Optional<Member> existingMemberOpt = memberRepository.findByMemberID(memberId);

        if (existingMemberOpt.isPresent()) {
            Member exisitingMember = existingMemberOpt.get();
            exisitingMember.setPhoto(file.getBytes());

            return mapToDTO(memberRepository.save(exisitingMember));
        } else {
            throw new EntityNotFoundException("Member not found");
        }
    }

    private MemberDTO mapToDTO(Member member) {
        MemberDTO dto = new MemberDTO();
        dto.setMemberID(member.getMemberID());
        dto.setRoleName(member.getRole().getRoleName());
        dto.setMemberNameSei(member.getMemberNameSei());
        dto.setMemberNameMei(member.getMemberNameMei());
        dto.setEmailAddress(member.getEmailAddress());
        dto.setGender(member.getGender());
        dto.setEntryDate(member.getEntryDate());
        dto.setBirthDate(member.getBirthDate());
        dto.setAge(member.getAge());
        dto.setCompanyPhoneNumber(member.getCompanyPhoneNumber());
        dto.setSelfIntroduction(member.getSelfIntroduction());
        dto.setPhoto(member.getPhoto());
        dto.setUpdDate(member.getUpdDate());
        return dto;
    }

    private Member mapToEntity(MemberDTO member) {
        Member entity = new Member();
        entity.setMemberID(member.getMemberID());
        Role role = new Role();
        role.setRoleID(member.getRoleID());
        entity.setRole(role);
        entity.setMemberNameSei(member.getMemberNameSei());
        entity.setMemberNameMei(member.getMemberNameMei());
        entity.setEmailAddress(member.getEmailAddress());
        entity.setGender(member.getGender());
        entity.setEntryDate(member.getEntryDate());
        entity.setBirthDate(member.getBirthDate());
        entity.setAge(member.getAge());
        entity.setCompanyPhoneNumber(member.getCompanyPhoneNumber());
        entity.setSelfIntroduction(member.getSelfIntroduction());
        entity.setPhoto(member.getPhoto());
        entity.setUpdDate(member.getUpdDate());
        return entity;
    }
}
