package br.com.blueaccount.bankslip.web.controller;

import br.com.blueaccount.bankslip.web.dto.BankSlipDTO;
import br.com.blueaccount.bankslip.web.dto.BankSlipResponse;
import br.com.blueaccount.bankslip.web.dto.StatusRequest;
import br.com.blueaccount.bankslip.web.facade.BankSlipFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest")
public class BankSlipController {

    @Autowired
    public BankSlipFacade facade;

    @PostMapping("/bankslips")
    public ResponseEntity<BankSlipDTO> create(@RequestBody BankSlipDTO dto){
        return facade.create(dto);
    }

    @GetMapping("/bankslips/{id}")
    public ResponseEntity<BankSlipDTO> findById(@PathVariable("id") String id){
        return facade.findOne(id);
    }

    @GetMapping("/bankslips")
    public ResponseEntity<List<BankSlipDTO>> findAll(){
        return facade.findAll();
    }

    @PutMapping("bankslips/{id}")
    public ResponseEntity<BankSlipDTO> update(@PathVariable("id") String id,
                                              @RequestBody StatusRequest request){
        return facade.update(id, request);
    }


}
