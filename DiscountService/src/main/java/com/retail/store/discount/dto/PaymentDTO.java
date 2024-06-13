package com.retail.store.discount.dto;

import java.util.List;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

	private String id;
	private double totalAmount;
	private double netPayableAmount;
	@NotEmpty
	private List<ItemDTO> items;
	@NotNull
	private CustomerDTO customer;
}
