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

    public ResponseEntity<BankSlipDTO> create(BankSlipDTO dto){

        validateRequest(dto);
        BankSlip bankSlip = bankSlipService.create(toEntity(dto));

        if (bankSlip == null){
            throw new ServiceException("Entity don't created.", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return buildSuccessMessage(bankSlip, HttpStatus.CREATED);
    }

    public ResponseEntity<BankSlipDTO> update(String id, StatusRequest request) {
        ResponseEntity<BankSlipResponse> response;


        validateParameter(request, "Status Request");
        validateParameter(id, "id");

        BankSlip bankSlip = bankSlipService.updateStatus(id, request.getStatus());

        if (bankSlip == null) {
            throw new ServiceException("Bankslip not found with the specified id", HttpStatus.NOT_FOUND);
        }


        return buildSuccessMessage(bankSlip, HttpStatus.OK);
    }

    public ResponseEntity<BankSlipDTO> findOne(String id){
        validateParameter(id, "id");
        BankSlip bankSlip = bankSlipService.find(id);

        if (bankSlip == null) {
            throw new ServiceException("Bankslip not found with the specified id", HttpStatus.NOT_FOUND);
        }

        return buildSuccessMessage(bankSlip, HttpStatus.OK);

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

    private ResponseEntity<BankSlipDTO> buildSuccessMessage(BankSlip bankSlip, HttpStatus code){
        return new ResponseEntity<>(toDto(bankSlip), code);

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
            throw new ServiceException(String.format("%s is empty or null", propName), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }



}
