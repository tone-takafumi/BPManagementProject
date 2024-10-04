package jp.co.cosmoroot.bpmp.core.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jp.co.cosmoroot.bpmp.core.dto.MemberDTO;
import jp.co.cosmoroot.bpmp.core.service.MemberService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
@Controller
public class MemberController {
    private static final Logger logger = LogManager.getLogger(CompanyController.class);

    @Autowired
    private MemberService memberService;

    @GetMapping("/member")
    @ResponseBody
    public ResponseEntity<MemberDTO> viewMemberList(@RequestParam("id") String id) throws SQLException {
        logger.info("START");
        MemberDTO result = memberService.viewMemberDetail(id);
        logger.info("END");
        return ResponseEntity.ok(result);
    }

    @PostMapping("/member")
    @ResponseBody
    public ResponseEntity<MemberDTO> viewMemberList(@RequestBody MemberDTO memberDTO) throws SQLException {
        logger.info("START");
        MemberDTO result = memberService.createMember(memberDTO);
        logger.info("END");
        return ResponseEntity.ok(result);
    }

    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<MemberDTO> crateMember(@RequestParam("file") MultipartFile file,
            @RequestParam("id") String id) throws SQLException, IOException {
        logger.info("START");
        MemberDTO result = memberService.uploadPhoto(id, file);
        logger.info("END");
        return ResponseEntity.ok(result);
    }

}
