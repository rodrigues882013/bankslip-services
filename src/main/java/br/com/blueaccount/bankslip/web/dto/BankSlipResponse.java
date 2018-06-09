package br.com.blueaccount.bankslip.web.dto;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

import java.io.Serializable;

public class BankSlipResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private HttpStatus code;
    private String message;
    private BankSlipDTO bankSlipDTO;

    public BankSlipResponse() {
    }

    public BankSlipResponse(HttpStatus code, String message, BankSlipDTO bankSlipDTO) {
        this.message = message;
        this.code = code;
        this.bankSlipDTO = bankSlipDTO;

    }

    public BankSlipResponse(HttpStatus code, BankSlipDTO bankSlipDTO) {
        this.code = code;
        this.bankSlipDTO = bankSlipDTO;
    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BankSlipDTO getBankSlipDTO() {
        return bankSlipDTO;
    }

    public void setBankSlipDTO(BankSlipDTO bankSlipDTO) {
        this.bankSlipDTO = bankSlipDTO;
    }
}
