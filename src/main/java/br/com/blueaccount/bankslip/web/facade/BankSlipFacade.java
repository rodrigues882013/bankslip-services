package br.com.blueaccount.bankslip.web.facade;

import br.com.blueaccount.bankslip.domain.model.BankSlip;
import br.com.blueaccount.bankslip.domain.model.StatusEnumType;
import br.com.blueaccount.bankslip.exception.ServiceException;
import br.com.blueaccount.bankslip.services.BankSlipService;
import br.com.blueaccount.bankslip.web.dto.BankSlipDTO;
import br.com.blueaccount.bankslip.web.dto.BankSlipResponse;
import br.com.blueaccount.bankslip.web.dto.StatusRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class BankSlipFacade {

    @Autowired
    public BankSlipService bankSlipService;

    public ResponseEntity<BankSlipResponse> create(BankSlipDTO dto){
        ResponseEntity<BankSlipResponse> response = null;

        try {
            validateRequest(dto);
            BankSlip bankSlip = bankSlipService.create(toEntity(dto));

            if (bankSlip != null)
                response = buildSuccessMessage(bankSlip, HttpStatus.CREATED);

        } catch (ServiceException ex){
            response = buildErrorMessage(ex.getReason(), ex.getCode());
        }

        return response;
    }

    public ResponseEntity<BankSlipResponse> update(String id, StatusRequest request) {
        ResponseEntity<BankSlipResponse> response;

        try {
            validateParameter(request, "Status Request");
            validateParameter(id, "id");

            BankSlip bankSlip = bankSlipService.updateStatus(id, request.getStatus());

            if (bankSlip != null) {
                response = buildSuccessMessage(bankSlip, HttpStatus.OK);

            } else {
                response = buildErrorMessage("Bankslip not found with the specified id", HttpStatus.NOT_FOUND);
            }

        } catch (ServiceException ex){
            response = buildErrorMessage(ex.getReason(), ex.getCode());
        }

        return response;
    }

    public ResponseEntity<BankSlipResponse> findOne(String id){
        ResponseEntity<BankSlipResponse> response;

        try {
            validateParameter(id, "id");
            BankSlip bankSlip = bankSlipService.find(id);

            if (bankSlip != null) {
                response = buildSuccessMessage(bankSlip, HttpStatus.OK);

            } else {
                response = buildErrorMessage("Bankslip not found with the specified id", HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException ex){
            response = buildErrorMessage(ex.getReason(), ex.getCode());
        }

        return response;
    }

    public ResponseEntity<List<BankSlipDTO>> findAll(){
        return new ResponseEntity<>(bankSlipService
                .findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    private BankSlip toEntity(BankSlipDTO dto){
        BankSlip bankSlip = new BankSlip();
        String code = dto.getCustomer() + dto.getTotalInCents() + dto.getDueDate();

        bankSlip.setId(UUID.nameUUIDFromBytes(code.getBytes()).toString());
        bankSlip.setCustomer(dto.getCustomer());
        bankSlip.setDueDate(dto.getDueDate());
        bankSlip.setTotalInCents(dto.getTotalInCents());
        bankSlip.setStatus(StatusEnumType.PENDING.getName());

        return bankSlip;
    }

    private BankSlipDTO toDto(BankSlip bankSlip){
        BankSlipDTO dto = new BankSlipDTO();

        dto.setId(bankSlip.getId());
        dto.setCustomer(bankSlip.getCustomer());
        dto.setDueDate(bankSlip.getDueDate());
        dto.setTotalInCents(bankSlip.getTotalInCents());
        dto.setStatus(bankSlip.getStatus());

        return dto;
    }

    private ResponseEntity<BankSlipResponse> buildSuccessMessage(BankSlip bankSlip, HttpStatus code){
        BankSlipResponse bankSlipResponse = new BankSlipResponse(code, toDto(bankSlip));
        return new ResponseEntity<>(bankSlipResponse, code);

    }

    private ResponseEntity<BankSlipResponse> buildErrorMessage(String reason, HttpStatus code){
        BankSlipResponse bankSlipResponse = new BankSlipResponse(code, reason, null);
        return new ResponseEntity<>(bankSlipResponse, code);
    }

    private void validateRequest(BankSlipDTO dto) throws ServiceException{

        if (dto == null){
            throw new ServiceException("Body is empty or null", HttpStatus.BAD_REQUEST);
        }

        validateParameter(dto.getDueDate(), "Due date");
        validateParameter(dto.getTotalInCents(), "Total in cents");
        validateParameter(dto.getCustomer(), "Customer");
        validateParameter(dto.getStatus(), "Status");

    }

    private void validateParameter(Object props, String propName){
        if (Objects.isNull(props) || StringUtils.isEmpty(props.toString())){
            throw new ServiceException(String.format("%s is empty or null", propName), HttpStatus.BAD_REQUEST);
        }
    }



}
