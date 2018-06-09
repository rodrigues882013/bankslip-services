package br.com.blueaccount.bankslip.exception;

import org.springframework.http.HttpStatus;

public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private HttpStatus code;
    private String reason;

    public ServiceException(String reason, HttpStatus code){
        super(reason);
        this.code = code;

    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


}
