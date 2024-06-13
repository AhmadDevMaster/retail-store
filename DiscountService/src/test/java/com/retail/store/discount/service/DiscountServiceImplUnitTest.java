package com.retail.store.discount.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.retail.store.discount.dto.PaymentDTO;
import com.retail.store.discount.dto.CustomerDTO;
import com.retail.store.discount.dto.ItemDTO;
import com.retail.store.discount.dto.ItemType;
import com.retail.store.discount.model.Discount;
import com.retail.store.discount.repo.DiscountRulesDBRepository;

@SpringBootTest
class DiscountServiceImplUnitTest {

    @Mock
    DiscountRulesDBRepository discountRepository;

    @Autowired
    ApplicationContext applicationContext;

    @InjectMocks
    DiscountServiceImpl discountServiceImpl;

    @BeforeEach
    public void before() {
        discountServiceImpl = new DiscountServiceImpl(discountRepository, applicationContext);
    }

    @Test
    void givenCustomer_whenCustomerTypeEmployee_thenEmployeeDiscount30()  {

        PaymentDTO bill = PaymentDTO.builder().id("5").totalAmount(1821)
                .customer(CustomerDTO.builder().customerId(4).customerType("Employee").build())
                .items(Stream
                        .of(ItemDTO.builder().itemType(ItemType.WIRELESS).price(660.5).quantity(2).build(),
                                ItemDTO.builder().itemType(ItemType.GROCERIES).price(500).quantity(1).build())
                        .collect(Collectors.toList()))
                .build();

        Discount rule = Discount.builder().ruleId(new ObjectId("6505b2b18a475910eb9b559a"))
                .discountType("Percentage").ruleType("Employee").threshold(0).discountValue(0.3).build();

        when(discountRepository.findByRuleType(bill.getCustomer().getCustomerType())).thenReturn(rule);

        PaymentDTO result = discountServiceImpl.calculateDiscount(bill);

        assertEquals(1424.7, result.getNetPayableAmount());

        verify(discountRepository, times(1)).findByRuleType(bill.getCustomer().getCustomerType());
    }

    @Test
    void givenOnlyGroceries_whenCustomerTypeAffiliate_thenNoDiscount()  {

        PaymentDTO bill = PaymentDTO.builder().id("1").totalAmount(1821)
                .customer(CustomerDTO.builder().customerId(5).customerType("Affiliate").build())
                .items(Stream
                        .of(ItemDTO.builder().itemType(ItemType.GROCERIES).price(660.5).quantity(2).build(),
                                ItemDTO.builder().itemType(ItemType.GROCERIES).price(500).quantity(1).build())
                        .collect(Collectors.toList()))
                .build();

        Discount rule = Discount.builder().ruleId(new ObjectId("666790ab0dae4ada2ba26a16"))
                .discountType("Percentage").ruleType("Employee").threshold(0).discountValue(0.1).build();


        when(discountRepository.findByRuleType(bill.getCustomer().getCustomerType())).thenReturn(rule);

        PaymentDTO result = discountServiceImpl.calculateDiscount(bill);

        assertEquals(1821, result.getNetPayableAmount());

        verify(discountRepository, times(1)).findByRuleType(bill.getCustomer().getCustomerType());
    }

    @Test
    void givenCustomer_whenCustomerTypeAffiliate_then10Discount()  {

        PaymentDTO bill = PaymentDTO.builder().id("2").totalAmount(1371)
                .customer(CustomerDTO.builder().customerId(6).customerType("Affiliate").build())
                .items(Stream
                        .of(ItemDTO.builder().itemType(ItemType.WIRELESS).price(660.5).quantity(2).build(),
                                ItemDTO.builder().itemType(ItemType.CLOTHES).price(50).quantity(1).build())
                        .collect(Collectors.toList()))
                .build();

        Discount rule = Discount.builder().ruleId(new ObjectId("666790ab0dae4ada2ba26a16"))
                .discountType("Percentage").ruleType("Employee").threshold(0).discountValue(0.1).build();


        when(discountRepository.findByRuleType(bill.getCustomer().getCustomerType())).thenReturn(rule);

        PaymentDTO result = discountServiceImpl.calculateDiscount(bill);

        assertEquals(1233.9, result.getNetPayableAmount());

        verify(discountRepository, times(1)).findByRuleType(bill.getCustomer().getCustomerType());
    }

    @Test
    void givenCustomer_whenCustomerTypeLoyal_then5Discount()  {

        PaymentDTO bill = PaymentDTO.builder().id("3").totalAmount(352)
                .customer(CustomerDTO.builder().customerId(7).customerType("Loyal").build())
                .items(Stream
                        .of(ItemDTO.builder().itemType(ItemType.WIRELESS).price(230).quantity(1).build(),
                                ItemDTO.builder().itemType(ItemType.CLOTHES).price(12).quantity(6).build(),
                                ItemDTO.builder().itemType(ItemType.GROCERIES).price(5).quantity(10).build())
                        .collect(Collectors.toList()))
                .build();

        Discount rule = Discount.builder().ruleId(new ObjectId("666790ab0dae4ada2ba26a17"))
                .discountType("Percentage").ruleType("Employee").threshold(0).discountValue(0.05).build();

        when(discountRepository.findByRuleType(bill.getCustomer().getCustomerType())).thenReturn(rule);

        PaymentDTO result = discountServiceImpl.calculateDiscount(bill);

        assertEquals(336.9, result.getNetPayableAmount());

        verify(discountRepository, times(1)).findByRuleType(bill.getCustomer().getCustomerType());
    }

    @Test
    void givenCustomer_whenCustomerTypeFixed_thenFixedDiscount()  {

        PaymentDTO bill = PaymentDTO.builder().id("4").totalAmount(352).customer(CustomerDTO.builder().customerType("Fixed").build())
                .items(Stream
                        .of(ItemDTO.builder().itemType(ItemType.WIRELESS).price(230).quantity(1).build(),
                                ItemDTO.builder().itemType(ItemType.CLOTHES).price(12).quantity(6).build(),
                                ItemDTO.builder().itemType(ItemType.GROCERIES).price(5).quantity(10).build())
                        .collect(Collectors.toList()))
                .build();

        Discount rule = Discount.builder().ruleId(new ObjectId("6505b2f18a475910eb9b559c"))
                .discountType("Fixed").ruleType("Threshold").threshold(100).discountValue(5).build();


        when(discountRepository.findByRuleType("Fixed")).thenReturn(rule);

        PaymentDTO result = discountServiceImpl.calculateDiscount(bill);

        assertEquals(337, result.getNetPayableAmount());

        verify(discountRepository, times(1)).findByRuleType("Fixed");
    }
}
