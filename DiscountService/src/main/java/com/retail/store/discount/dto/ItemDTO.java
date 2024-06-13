package com.retail.store.discount.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

	private long itemId;
	private ItemType itemType;
	private double price;
	private int quantity;

}
