package jp.co.cosmoroot.bpmp.core.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jp.co.cosmoroot.bpmp.core.model.Company;
import jp.co.cosmoroot.bpmp.core.service.CompanyService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CompanyController {

    @GetMapping("/companyList")
    @ResponseBody
    public List<Company> getCompanyList() throws SQLException {
        CompanyService companyService = new CompanyService();
        List<Company> result = companyService.viewCompanyList();
        return result;
    }

    @GetMapping("/companyMaxId")
    @ResponseBody
    public String createMaxId() throws SQLException {
        CompanyService companyService = new CompanyService();
        String result = companyService.createCompanyId();
        return result;
    }

    @PostMapping("/company")
    @ResponseBody
    public void postCompany(@RequestBody Company company) throws SQLException {
        CompanyService companyService = new CompanyService();
        companyService.addCompany(company);
        return;
    }
}
