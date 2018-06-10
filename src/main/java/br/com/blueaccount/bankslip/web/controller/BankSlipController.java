package br.com.blueaccount.bankslip.web.controller;

import br.com.blueaccount.bankslip.web.dto.BankSlipDTO;
import br.com.blueaccount.bankslip.web.dto.BankSlipResponse;
import br.com.blueaccount.bankslip.web.dto.StatusRequest;
import br.com.blueaccount.bankslip.web.facade.BankSlipFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest")
public class BankSlipController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public BankSlipFacade facade;

    @PostMapping(value = "/bankslips")
    public ResponseEntity<BankSlipResponse> create(@RequestBody BankSlipDTO dto){
        return facade.create(dto);
    }

    @GetMapping("/bankslips/{id}")
    public ResponseEntity<BankSlipResponse> findById(@PathVariable("id") String id){
        return facade.findOne(id);
    }

    @GetMapping("/bankslips")
    public ResponseEntity<List<BankSlipDTO>> findAll(){
        return facade.findAll();
    }

    @PutMapping("bankslips/{id}")
    public ResponseEntity<BankSlipResponse> update(@PathVariable("id") String id,
                                              @RequestBody StatusRequest request){
        return facade.update(id, request);
    }


}
