package jp.co.cosmoroot.bpmp.core.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import jp.co.cosmoroot.bpmp.core.dto.OrganizationChartDTO;
import jp.co.cosmoroot.bpmp.core.service.OrganizationChartService;

class OrganizationChartControllerTest extends OrganizationChartController {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private OrganizationChartService organizationChartService;

    @InjectMocks
    private OrganizationChartController organizationChartController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(organizationChartController).build();
    }

    @Test
    void testViewOrganizationChart() {
        OrganizationChartDTO organizationChartDTO = new OrganizationChartDTO();
    }

}
