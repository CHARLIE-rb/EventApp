/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.romero.userservice.controller;


import com.romero.userservice.common.InvoiceRequestMapper;
import com.romero.userservice.common.InvoiceResponseMapper;
import com.romero.userservice.dto.InvoiceRequest;
import com.romero.userservice.dto.InvoiceResponse;
import com.romero.userservice.entities.Invoice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import com.romero.userservice.respository.InvoiceRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


import java.util.Optional;

/**
 * @author sotobotero
 */
@Tag(name = "Billing API", description = "This APi serve all functionality for management Invoices")
@RestController
@RequestMapping("/billing")
public class InvoiceRestController {

    @Autowired
    InvoiceRepository billingRepository;

    @Autowired
    InvoiceRequestMapper irm;

    @Autowired
    InvoiceResponseMapper irspm;

    @Operation(description = "Return all invoices bundled into Response", summary = "Return 204 if no data found")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @GetMapping()
    public List<InvoiceResponse> list() {
        List<Invoice> findAll = billingRepository.findAll();
        List<InvoiceResponse> InvoiceListToInvoiceResposeList = irspm.InvoiceListToInvoiceResposeList(findAll);
        return InvoiceListToInvoiceResposeList;
    }

    @GetMapping("/{id}")
    public InvoiceResponse get(@PathVariable String id) {
        Optional<Invoice> findById = billingRepository.findById(Long.valueOf(id));
        InvoiceResponse InvoiceToInvoiceRespose = irspm.InvoiceToInvoiceRespose(findById.get());
        return InvoiceToInvoiceRespose;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody InvoiceRequest input) {
        Invoice save = null;
        Optional<Invoice> findById = billingRepository.findById(Long.valueOf(id));
        Invoice get = findById.get();
        if (get != null) {
            get.setAmount(input.getAmount());
            get.setDetail(input.getDetail());
            get.setCustomerId(input.getCustomer());
            get.setNumber(input.getNumber());
            save = billingRepository.save(get);
        }
        InvoiceResponse InvoiceToInvoiceRespose = irspm.InvoiceToInvoiceRespose(save);
        return ResponseEntity.ok(InvoiceToInvoiceRespose);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody InvoiceRequest input) {
        Invoice InvoiceRequestToInvoice = irm.InvoiceRequestToInvoice(input);
        Invoice save = billingRepository.save(InvoiceRequestToInvoice);
        InvoiceResponse InvoiceToInvoiceRespose = irspm.InvoiceToInvoiceRespose(save);
        return ResponseEntity.ok(InvoiceToInvoiceRespose);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Invoice save = null;
        Optional<Invoice> findById = billingRepository.findById(Long.valueOf(id));
        Invoice get = findById.get();
        if (get != null) {
            billingRepository.delete(get);
        }
        return ResponseEntity.ok().build();
    }
}
