/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.romero.userservice.common;

import com.romero.userservice.dto.InvoiceResponse;
import com.romero.userservice.entities.Invoice;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author sotobotero
 */
@Mapper(componentModel = "spring")
public interface InvoiceResponseMapper {

    @Mappings({
            @Mapping(source = "customerId", target = "customer"),
            @Mapping(source = "id", target = "invoiceId")})
    InvoiceResponse InvoiceToInvoiceRespose(Invoice source);


    List<InvoiceResponse> InvoiceListToInvoiceResposeList(List<Invoice> source);

    @InheritInverseConfiguration
    Invoice InvoiceResponseToInvoice(InvoiceResponse srr);

    @InheritInverseConfiguration
    List<Invoice> InvoiceResponseToInvoiceList(List<InvoiceResponse> source);

}
