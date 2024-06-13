package com.retail.store.discount.service;

import com.retail.store.discount.model.Discount;

import lombok.Data;

@Data
public abstract class AbstractDiscountService {

	private Discount discount;

	public abstract double calculateDiscount(double totalAmount);

}
