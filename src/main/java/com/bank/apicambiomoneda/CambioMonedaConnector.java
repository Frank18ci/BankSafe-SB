package com.bank.apicambiomoneda;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.bank.apicambiomoneda.response.ConversionBaseToTargetDTO;
import com.bank.apicambiomoneda.response.ConversionRatesDTO;
import com.bank.apicambiomoneda.response.ConversionTipoMonedaDTO;
import com.bank.service.TipoMonedaTarjetaService;
@Component
public class CambioMonedaConnector {
	@Autowired
	private TipoMonedaTarjetaService tipoMonedaTarjetaService;
	
	private final String keySecurity = "859e55d584c30c46ecad0338/";
	private final String url = "https://v6.exchangerate-api.com/v6/";
	private final String latest = "latest/";
	public List<ConversionTipoMonedaDTO> getConversion(String nombre) {
		WebClient client = WebClient.builder()
				.baseUrl(url + keySecurity + latest + nombre)
			    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			    .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
				.build();
		ConversionRatesDTO conversionRatesDTO = client.get().uri(uriBuider -> uriBuider.build(nombre)).retrieve().bodyToMono(ConversionRatesDTO.class).share().block();
		
		List<ConversionTipoMonedaDTO> lista = tipoMonedaTarjetaService.list().stream()
										.map(tipoMoneda -> ConversionTipoMonedaDTO.builder()
												.id(tipoMoneda.getId())
												.simbolo(tipoMoneda.getSimbolo())
												.tipo(tipoMoneda.getTipo())
												.valor(conversionRatesDTO.getConversion_rates().get(tipoMoneda.getSimbolo()))
												.build())
										.collect(Collectors.toList());
		return lista;
		 
	}
	public ConversionBaseToTargetDTO getConversion(String base, String target) {
		WebClient client = WebClient.builder()
				.baseUrl(url + keySecurity + latest + base)
			    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			    .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
				.build();
		ConversionRatesDTO conversionRatesDTO = client.get().uri(uriBuider -> uriBuider.build(base)).retrieve().bodyToMono(ConversionRatesDTO.class).share().block();
		
		Set<String> valoresPermitidos = Set.of(target);
		
		Map<String, Double> filtrado = conversionRatesDTO.getConversion_rates().entrySet().stream()
				.filter(entry -> valoresPermitidos.contains(entry.getKey()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		conversionRatesDTO.setConversion_rates(filtrado);
		
		return ConversionBaseToTargetDTO.builder()
				.base_code(base)
				.target_code(target)
				.value(conversionRatesDTO.getConversion_rates().get(target))
				.build();
		 
	}
}
