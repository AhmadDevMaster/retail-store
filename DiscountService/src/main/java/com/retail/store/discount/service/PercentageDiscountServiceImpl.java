package com.retail.store.discount.service;

import org.springframework.stereotype.Component;

@Component("PercentageDiscountServiceImpl")
public class PercentageDiscountServiceImpl extends AbstractDiscountService {

	@Override
	public double calculateDiscount(double totalAmount) {
		return totalAmount * getDiscount().getDiscountValue();
	}

}
