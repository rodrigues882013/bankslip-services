package br.com.blueaccount.bankslip.domain.repository;

import br.com.blueaccount.bankslip.domain.model.BankSlip;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface BankSlipRepository extends CrudRepository<BankSlip, String> {
}
