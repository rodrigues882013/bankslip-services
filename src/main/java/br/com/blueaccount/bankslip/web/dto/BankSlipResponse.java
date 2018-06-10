package br.com.blueaccount.bankslip.web.dto;

import java.io.Serializable;

public class BankSlipResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String message;
    private BankSlipDTO bankSlip;

    public BankSlipResponse() {
    }

    public BankSlipResponse(String message, BankSlipDTO bankSlip) {
        this.message = message;
        this.bankSlip = bankSlip;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BankSlipDTO getBankSlip() {
        return bankSlip;
    }

    public void setBankSlip(BankSlipDTO bankSlip) {
        this.bankSlip = bankSlip;
    }
}
