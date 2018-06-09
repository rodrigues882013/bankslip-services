package br.com.blueaccount.bankslip.services;

import br.com.blueaccount.bankslip.domain.model.BankSlip;
import br.com.blueaccount.bankslip.domain.repository.BankSlipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankSlipService {

    @Autowired
    public BankSlipRepository bankSlipRepository;

    public BankSlip create(BankSlip bankSlip){
        return bankSlipRepository.save(bankSlip);
    }

    public BankSlip updateStatus(String id, String status) {
        BankSlip entity = find(id);

        if (entity == null)
            return null;

        entity.setStatus(status);
        return bankSlipRepository.save(entity);
    }

    public BankSlip find(String id){
        Optional<BankSlip> canBeBankSlipe = bankSlipRepository.findById(id);
        return canBeBankSlipe.orElse(null);
    }

    public List<BankSlip> findAll(){
        return (List<BankSlip>) bankSlipRepository.findAll();
    }
}
