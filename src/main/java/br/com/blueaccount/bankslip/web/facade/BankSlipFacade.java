package br.com.blueaccount.bankslip.web.facade;

import br.com.blueaccount.bankslip.domain.model.BankSlip;
import br.com.blueaccount.bankslip.domain.model.constants.StatusEnumType;
import br.com.blueaccount.bankslip.exception.ServiceException;
import br.com.blueaccount.bankslip.services.BankSlipService;
import br.com.blueaccount.bankslip.web.dto.BankSlipDTO;
import br.com.blueaccount.bankslip.web.dto.BankSlipResponse;
import br.com.blueaccount.bankslip.web.dto.StatusRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class BankSlipFacade {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public BankSlipService bankSlipService;

    public ResponseEntity<BankSlipDTO> create(BankSlipDTO dto){
        logger.info("Creating bankSplit");

        validateRequest(dto);
        BankSlip bankSlip = bankSlipService.create(toEntity(dto));

        if (bankSlip == null){
            logger.error("create: Entity didn't created.");
            throw new ServiceException("Entity didn't created.", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        verifyDate(bankSlip.getDueDate());
        return buildSuccessMessage(bankSlip, HttpStatus.CREATED);
    }

    public ResponseEntity<BankSlipDTO> update(String id, StatusRequest request) {
        logger.info("update: Updating bankSplit with status: `{}`", request.getStatus());
        ResponseEntity<BankSlipResponse> response;

        validateParameter(request, "Status Request");
        validateParameter(id, "id");

        BankSlip bankSlip = bankSlipService.updateStatus(id, StatusEnumType.valueOf(request.getStatus()));

        if (bankSlip == null) {
            logger.error("Bankslip not found with the specified id: {}", id);
            throw new ServiceException("Bankslip not found with the specified id", HttpStatus.NOT_FOUND);
        }


        return buildSuccessMessage(bankSlip, HttpStatus.OK);
    }

    public ResponseEntity<BankSlipDTO> findOne(String id){
        logger.info("findOne: Getting register: `{}`", id);
        validateParameter(id, "id");
        BankSlip bankSlip = bankSlipService.find(id);

        if (bankSlip == null) {
            logger.error("Bankslip not found with the specified id: {}", id);
            throw new ServiceException("Bankslip not found with the specified id", HttpStatus.NOT_FOUND);
        }

        return buildSuccessMessage(bankSlip, HttpStatus.OK);

    }

    public ResponseEntity<List<BankSlipDTO>> findAll(){
        logger.info("findAll: Getting all register");
        return new ResponseEntity<>(bankSlipService
                .findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    private BankSlip toEntity(BankSlipDTO dto) {
        BankSlip bankSlip;

        try {

            bankSlip = new BankSlip();
            String code = dto.getCustomer() + dto.getTotalInCents() + dto.getDueDate();
            bankSlip.setId(UUID.nameUUIDFromBytes(code.getBytes()).toString());
            bankSlip.setCustomer(dto.getCustomer());

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(dto.getDueDate());
            bankSlip.setDueDate(date);

            bankSlip.setTotalInCents(dto.getTotalInCents());
            bankSlip.setStatus(StatusEnumType.valueOf(dto.getStatus()));

        } catch (ParseException ex){
            logger.error("Due date was sent with wrong format");
            throw new ServiceException("Due date was sent with wrong format", HttpStatus.BAD_REQUEST);
        } catch (Exception ex){
            logger.error("Constant isi");
            throw new ServiceException("Due date was sent with wrong format", HttpStatus.BAD_REQUEST);
        }

        return bankSlip;
    }

    private BankSlipDTO toDto(BankSlip bankSlip){
        BankSlipDTO dto = new BankSlipDTO();

        dto.setId(bankSlip.getId());
        dto.setCustomer(bankSlip.getCustomer());
        dto.setDueDate(new SimpleDateFormat("yyyy-MM-dd").format(bankSlip.getDueDate()));
        dto.setTotalInCents(bankSlip.getTotalInCents());
        dto.setStatus(bankSlip.getStatus().getName());
        Double fine = dto.getTotalInCents().doubleValue() * verifyDate(bankSlip.getDueDate());
        dto.setFine(fine);
        return dto;
    }

    private ResponseEntity<BankSlipDTO> buildSuccessMessage(BankSlip bankSlip, HttpStatus code){
        logger.info("buildSuccessMessage: Creating success message");
        ResponseEntity<BankSlipDTO> result = new ResponseEntity<>(toDto(bankSlip), code);
        logger.info("buildSuccessMessage: Banksplit created with success.");
        return result;
    }

    private void validateRequest(BankSlipDTO dto) throws ServiceException{
        logger.info("Validating request: {} ", dto);

        if (dto == null){
            logger.error("Body is empty or null");
            throw new ServiceException("Body is empty or null", HttpStatus.BAD_REQUEST);
        }

        validateParameter(dto.getDueDate(), "Due date");
        validateParameter(dto.getTotalInCents(), "Total in cents");
        validateParameter(dto.getCustomer(), "Customer");

        try {
            StatusEnumType.valueOf(dto.getStatus());

        } catch (Exception ex){
            logger.error("Status {} don't exist.", dto.getStatus());
            throw new ServiceException(String.format("status %s don't exist.", dto.getStatus()), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    private void validateParameter(Object props, String propName){
        logger.info("Validating parameter: {} ", propName);
        if (Objects.isNull(props) || StringUtils.isEmpty(props.toString())){
            logger.error("{} is empty or null", propName);
            throw new ServiceException(String.format("%s is empty or null", propName), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    private double verifyDate(Date date){
        logger.info("Calculating fine");
        Date current = new Date();
        Calendar c = Calendar.getInstance();

        if (current.after(date)){
            Long days = (c.getTime().getTime() - date.getTime()) / (1000*60*60*24);

            if (days > 10){
                return 0.01 * days;
            }

            return 0.005 * days;
        }

        return 0;
    }



}
