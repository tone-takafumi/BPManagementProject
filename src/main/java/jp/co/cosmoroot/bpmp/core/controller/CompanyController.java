package jp.co.cosmoroot.bpmp.core.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jp.co.cosmoroot.bpmp.core.model.Company;
import jp.co.cosmoroot.bpmp.core.service.CompanyService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CompanyController {

    @GetMapping("/company")
    @ResponseBody
    public List<Company> getCompanyList() {
        CompanyService companyService = new CompanyService();
        List<Company> result = companyService.viewCompanyList();

        return result;
    }
}
