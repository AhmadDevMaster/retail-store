package com.retail.store.discount.service;

import com.retail.store.discount.dto.ItemType;
import com.retail.store.discount.dto.PaymentDTO;
import com.retail.store.discount.exception.CustomerTypeNotFoundException;
import com.retail.store.discount.model.Discount;
import com.retail.store.discount.repo.DiscountRulesDBRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscountServiceImpl {

    private final DiscountRulesDBRepository rulesRepository;
    private final ApplicationContext applicationContext;

    private static final String FIXED_CUSTOMER_TYPE = "Threshold";

    public PaymentDTO calculateDiscount(PaymentDTO paymentDTO) throws CustomerTypeNotFoundException {
        String paymentDTOId = paymentDTO.getId();
        log.debug("start Calculating total eligible amount discount, payment Id {}", paymentDTOId);
        double totalAmountWithoutGroceries =paymentDTO.getItems().stream().filter(item -> !ItemType.GROCERIES.name().equals(item.getItemType().name()))
                .mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();

        String customerType = paymentDTO.getCustomer().getCustomerType();
        if (customerType == null || customerType.isBlank()) {
            customerType = FIXED_CUSTOMER_TYPE;
        }
        Discount discount = rulesRepository.findByRuleType(customerType.trim());
        AbstractDiscountService discountService = applicationContext.getBean(discount.getDiscountType() + "DiscountServiceImpl", AbstractDiscountService.class);
        discountService.setDiscount(discount);
        double netPayableAmount = paymentDTO.getTotalAmount() - discountService.calculateDiscount(totalAmountWithoutGroceries);
        paymentDTO.setNetPayableAmount(netPayableAmount);
        return paymentDTO;

    }

}
