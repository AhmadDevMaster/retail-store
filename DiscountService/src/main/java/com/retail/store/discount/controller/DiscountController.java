package com.retail.store.discount.controller;


import com.retail.store.discount.dto.PaymentDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail.store.discount.service.DiscountServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/bills/discount")
@Slf4j
@RequiredArgsConstructor
public class DiscountController {

    private final DiscountServiceImpl discountServiceImpl;

    @PostMapping("/calculate")
    public ResponseEntity<PaymentDTO> discount(@Valid @RequestBody PaymentDTO paymentDTO) {
        log.info("Start Calculation process for Payment id {}", paymentDTO.getId());
        paymentDTO = discountServiceImpl.calculateDiscount(paymentDTO);
        log.info("Finish calculation process for Payment id {}", paymentDTO.getId());
        return new ResponseEntity<>(paymentDTO, HttpStatus.OK);

    }

}
