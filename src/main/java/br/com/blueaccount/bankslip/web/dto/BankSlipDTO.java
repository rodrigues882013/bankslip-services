package br.com.blueaccount.bankslip.web.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BankSlipDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private Date dueDate;
    private BigDecimal totalInCents;
    private String customer;
    private String status;
    private Double fine;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getFine() {
        return fine;
    }

    public void setFine(Double fine) {
        this.fine = fine;
    }
}
