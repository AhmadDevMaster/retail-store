package com.retail.store.discount.service;

import org.springframework.stereotype.Component;

@Component("FixedDiscountServiceImpl")
public class FixedDiscountServiceImpl extends AbstractDiscountService {

	@Override
	public double calculateDiscount(double totalAmount) {
		if (totalAmount >= getDiscount().getThreshold()) {
			return (float)Math.floor(totalAmount / getDiscount().getThreshold()) * getDiscount().getDiscountValue();
		}
		return totalAmount;
	}

}
