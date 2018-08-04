package br.com.blueaccount.bankslip.domain.repository;

import br.com.blueaccount.bankslip.BankSlipServiceApplication;
import br.com.blueaccount.bankslip.domain.model.BankSlip;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@ContextConfiguration(classes = {BankSlipServiceApplication.class})
public class BankSlipRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BankSlipRepository bankSlipRepository;

    @Test
    public void whenFindById() {
        // given
        BankSlip bill = new BankSlip();
        bill.setId("Teste");
        entityManager.persist(bill);
        entityManager.flush();

        // when
        Optional<BankSlip> found = bankSlipRepository.findById(bill.getId());

        assertTrue(found.isPresent());

        found.ifPresent(x -> {
            assertThat(bill.getId()).isEqualTo("Teste");
            assertThat(bill.getId()).isNotEqualTo("Test");
        });
    }

    @Test
    public void whenFindAll(){
        // given
        BankSlip bill = new BankSlip();
        bill.setId("Teste");
        entityManager.persist(bill);
        entityManager.flush();

        bill = new BankSlip();
        bill.setId("Teste111111");
        entityManager.persist(bill);
        entityManager.flush();


        List<BankSlip> it = (List<BankSlip>) bankSlipRepository.findAll();

        assertThat(it.size()).isEqualTo(2);
        assertThat(it.size()).isNotEqualTo(40);
    }
}
