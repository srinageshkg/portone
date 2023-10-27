package com.dcp.portone.controller;

import com.dcp.portone.model.EmployeeModel;
import com.dcp.portone.model.OrgnizationModel;
import com.dcp.portone.repository.OrgRepository;
import com.dcp.portone.service.OrgService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class OrgControllerTest {
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();
    @Autowired
    private MockMvc mockMvc;
    @Mock private OrgRepository orgRepository;
    @Mock private OrgService orgService;

    @InjectMocks private OrgController orgController;
    EmployeeModel emp1 = new EmployeeModel(101L, "John", 10000L);
    EmployeeModel emp2 = new EmployeeModel(101L, "Jow", 20000L);
    EmployeeModel emp3 = new EmployeeModel(101L, "Matt", 25000L);
    Set<EmployeeModel> empList1 = new HashSet<>(Arrays.asList(emp1,emp2));
    Set<EmployeeModel> empList2 = new HashSet<>(Arrays.asList(emp3));
    OrgnizationModel orgModel1 = new OrgnizationModel(1L,"GGGOrg", "GA", empList1);
    OrgnizationModel orgModel2 = new OrgnizationModel(2L,"NNNOrg", "TX", empList2);

    @BeforeEach
    public void setUp() {
     MockitoAnnotations.openMocks(this);
    }

/*    @Test
    public void getOrgs_success() throws Exception {
        List<OrgnizationModel> records = new ArrayList<>(Arrays.asList(orgModel1, orgModel2));

//        Mockito.when(orgService.getOrgs()).thenReturn(records);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/org")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].orgName", is("Amazon")));
    }*/

/*    @Test
    public void getOrgById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/org/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].orgName", is("Amazon")));

    }*/
}
