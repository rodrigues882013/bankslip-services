package br.com.blueaccount.bankslip.domain.repository;

import br.com.blueaccount.bankslip.domain.model.BankSlip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankSlipRepository extends CrudRepository<BankSlip, String> {
}
