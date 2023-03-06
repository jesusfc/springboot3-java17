package com.jesusfc.springboot3java17.controller.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jesusfc.springboot3java17.openApi.v1.api.ICustomer;
import com.jesusfc.springboot3java17.openApi.v1.model.Customer;
import com.jesusfc.springboot3java17.openApi.v1.model.CustomerList;
import com.jesusfc.springboot3java17.openApi.v1.model.CustomerPageList;
import com.jesusfc.springboot3java17.services.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/rest/customer")
public class CustomerRestController implements ICustomer {

    private final UserService userService;
    private final LocaleResolver localeResolver;

    private final MessageSource messageSource;
    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;


    @Override
    public ResponseEntity<Void> createCustomer(ICustomer body) {
        return null;
    }

    @Override
    public ResponseEntity<Void> delCustomerById(UUID customerId) {
        return null;
    }

    @Override
    public ResponseEntity<ICustomer> getCustomerById(UUID customerId) {
        return null;
    }

    @Override
    public ResponseEntity<List<CustomerList>> getCustomerList() {
        return null;
    }

    @Override
    public ResponseEntity<List<CustomerPageList>> getCustomerPageList(Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateCustomerById(UUID customerId, ICustomer body) {
        return null;
    }

    public ResponseEntity<Void> updateCustomerById(@Parameter(in = ParameterIn.PATH, description = "Customer Id", required = true, schema = @Schema()) @PathVariable("customerId") UUID customerId, @Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody Customer body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
