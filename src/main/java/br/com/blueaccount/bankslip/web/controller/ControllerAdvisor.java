package br.com.blueaccount.bankslip.web.controller;

import br.com.blueaccount.bankslip.exception.ServiceException;
import br.com.blueaccount.bankslip.web.dto.BankSlipResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(value = ServiceException.class)
    public ResponseEntity<?> handleServicexception(ServiceException serviceException, HttpServletRequest request) {

        BankSlipResponse response;
        response = new BankSlipResponse(serviceException.getCode(), serviceException.getLocalizedMessage());

        return new ResponseEntity<>(response, serviceException.getCode());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleException(Exception serviceException, HttpServletRequest request) {

        BankSlipResponse response;
        response = new BankSlipResponse(HttpStatus.BAD_REQUEST, serviceException.getLocalizedMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
