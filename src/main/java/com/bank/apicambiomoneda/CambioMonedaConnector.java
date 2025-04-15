package com.bank.apicambiomoneda;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.bank.apicambiomoneda.response.ConversionRatesDTO;
@Component
public class CambioMonedaConnector {
	private final String keySecurity = "859e55d584c30c46ecad0338/";
	private final String url = "https://v6.exchangerate-api.com/v6/";
	private final String latest = "latest/";
	public ConversionRatesDTO getConversion(String nombre) {
		WebClient client = WebClient.builder()
				.baseUrl(url + keySecurity + latest + nombre)
			    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			    .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
				.build();
		return client.get().uri(uriBuider -> uriBuider.build(nombre)).retrieve().bodyToMono(ConversionRatesDTO.class).share().block();
		 
	}
}
