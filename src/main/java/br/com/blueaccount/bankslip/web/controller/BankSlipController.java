package br.com.blueaccount.bankslip.web.controller;

import br.com.blueaccount.bankslip.web.dto.BankSlipDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest")
public class BankSlipController {

    @PostMapping("/bankslips")
    public ResponseEntity<?> create(){
        return null;
    }

    @GetMapping("/bankslips/{id}")
    public ResponseEntity<BankSlipDTO> findById(@PathVariable("id") Long id){
        return null;
    }

    @GetMapping("/bankslips")
    public ResponseEntity<List<Object>> findAll(){
        return null;
    }

    @PutMapping("bankslips/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id){
        return null;
    }


}
