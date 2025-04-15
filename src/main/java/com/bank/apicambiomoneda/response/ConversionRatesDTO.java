package com.bank.apicambiomoneda.response;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversionRatesDTO {
	private String base_code;
	private Map<String, Double> conversion_rates;
}
