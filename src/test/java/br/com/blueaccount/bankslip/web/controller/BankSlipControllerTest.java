package br.com.blueaccount.bankslip.web.controller;

import br.com.blueaccount.bankslip.BankSlipServiceApplication;
import br.com.blueaccount.bankslip.domain.model.BankSlip;
import br.com.blueaccount.bankslip.web.dto.BankSlipDTO;
import br.com.blueaccount.bankslip.web.dto.BankSlipResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.core.Is.is;




@RunWith(SpringRunner.class)
@WebMvcTest(BankSlipController.class)
@ContextConfiguration(classes = {BankSlipServiceApplication.class})
public class BankSlipControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BankSlipController controller;

    @Test
    public void getBankSlipById() throws Exception {
        BankSlip bill = new BankSlip();
        bill.setId("Teste");

        BankSlipDTO billDto = new BankSlipDTO();
        billDto.setId("Teste");

        ResponseEntity<BankSlipResponse> response =
                new ResponseEntity<>(new BankSlipResponse("Ok", billDto), HttpStatus.OK);

        given(controller.findById(bill.getId())).willReturn(response);

        mvc.perform(get("/rest/bankslips/" + bill.getId())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("bankSlip.id", is(bill.getId())));

    }

}
