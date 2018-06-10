package br.com.blueaccount.bankslip.web.dto;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

import java.io.Serializable;

public class BankSlipErrorResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private HttpStatus httpStatus;
    private String message;
    private Integer httpCode;

    public BankSlipErrorResponse() {
    }

    public BankSlipErrorResponse(HttpStatus code, String message) {
        this.message = message;
        this.httpStatus = code;
        this.httpCode = code.value();

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.httpCode = httpStatus.value();
    }

    public Integer getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }
}
