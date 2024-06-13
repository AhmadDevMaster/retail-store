package com.retail.store.discount.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@Document(collection = "discounts")
@EqualsAndHashCode
public class Discount {

	private ObjectId ruleId;
	private String ruleType;
	private String discountType;
	private double threshold;
	private double discountValue;

}
