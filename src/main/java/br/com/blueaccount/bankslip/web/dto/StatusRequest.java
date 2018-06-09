package br.com.blueaccount.bankslip.web.dto;

import java.io.Serializable;

public class StatusRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String status;

    public StatusRequest() {
    }

    public StatusRequest(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
