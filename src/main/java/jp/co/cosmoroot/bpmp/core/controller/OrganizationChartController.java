package jp.co.cosmoroot.bpmp.core.controller;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jp.co.cosmoroot.bpmp.core.dto.OrganizationChartDTO;
import jp.co.cosmoroot.bpmp.core.service.OrganizationChartService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
@Controller
public class OrganizationChartController {

    private static final Logger logger = LogManager.getLogger(CompanyController.class);

    @Autowired
    private OrganizationChartService organizationChartService;

    @GetMapping("/viewOrganizationChart")
    @ResponseBody
    public ResponseEntity<List<OrganizationChartDTO>> viewOrganizationChart() throws SQLException {
        logger.info("START");
        List<OrganizationChartDTO> result = organizationChartService.organizationChartViewAll();
        logger.info("END");
        return ResponseEntity.ok(result);
    }

}
