package br.com.blueaccount.bankslip.services;

import br.com.blueaccount.bankslip.domain.model.BankSlip;
import br.com.blueaccount.bankslip.domain.repository.BankSlipRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankSlipService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public BankSlipRepository bankSlipRepository;

    public BankSlip create(BankSlip bankSlip){
        logger.info("create: Creating banksplit");
        return bankSlipRepository.save(bankSlip);
    }

    public BankSlip updateStatus(String id, String status) {
        logger.info("updateStatus: Update banksplit with status: `{}`", status);
        BankSlip entity = find(id);

        if (entity == null)
            return null;

        entity.setStatus(status);
        return bankSlipRepository.save(entity);
    }

    public BankSlip find(String id){
        logger.info("find: Find banksplit with id: `{}`", id);
        Optional<BankSlip> canBeBankSlipe = bankSlipRepository.findById(id);
        return canBeBankSlipe.orElse(null);
    }

    public List<BankSlip> findAll(){
        logger.info("findAll: Find all banksplit");
        return (List<BankSlip>) bankSlipRepository.findAll();
    }
}
