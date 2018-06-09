package br.com.blueaccount.bankslip.domain.model;

import br.com.blueaccount.bankslip.domain.model.constants.StatusEnumType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "bank_slip")
public class BankSlip {

    @Id
    private String id;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "total_in_cents")
    private BigDecimal totalInCents;

    @Column(name = "customer")
    private String customer;

    @Enumerated(value = EnumType.STRING)
    private StatusEnumType status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getTotalInCents() {
        return totalInCents;
    }

    public void setTotalInCents(BigDecimal totalInCents) {
        this.totalInCents = totalInCents;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public StatusEnumType getStatus() {
        return status;
    }

    public void setStatus(StatusEnumType status) {
        this.status = status;
    }
}
