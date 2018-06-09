package br.com.blueaccount.bankslip.web.controller;

import br.com.blueaccount.bankslip.domain.model.BankSlip;
import br.com.blueaccount.bankslip.domain.repository.BankSlipRepository;
import br.com.blueaccount.bankslip.web.dto.BankSlipDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Date;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.get;

@RunWith(SpringRunner.class)
@WebMvcTest(BankSlipController.class)
//@TestPropertySource(locations="classpath:test.properties")
public class BankSlipControllerTest {

    private MockMvc mvc;

    @Autowired
    private BankSlipRepository bankSlipRepository;

    @MockBean
    private BankSlipController bankSlipController;



    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(bankSlipController).build();
    }

    @Test
    public void testFindAll() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/rest/bankslips"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testFindById() throws Exception {
        BankSlip bankSlip = new BankSlip();
        bankSlip.setStatus("PENDING");
        bankSlip.setTotalInCents(new BigDecimal(1000));
        bankSlip.setCustomer("Teste");
        bankSlip.setDueDate(new Date());

        BankSlip s = bankSlipRepository.save(bankSlip);

        mvc.perform(
                MockMvcRequestBuilders.get(String.format("/rest/bankslips/%s", s.getId())))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testCreate() throws Exception {
        String mock = "{\"dueDate\": \"2018-06-08\",\"totalInCents\": \"10000\",\"customer\": \"Teste dois\",\"status\": \"PENDING\" }";

        mvc.perform(MockMvcRequestBuilders
                .post("/rest/bankslips").contentType(MediaType.APPLICATION_JSON).content(mock))
                .andExpect(MockMvcResultMatchers.model().attributeExists("dueDate"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

}
