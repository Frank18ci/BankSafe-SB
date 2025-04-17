package com.bank.apicambiomoneda.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversionBaseToTargetDTO {
	private String base_code;
	private String target_code;
	private Double value;
}
