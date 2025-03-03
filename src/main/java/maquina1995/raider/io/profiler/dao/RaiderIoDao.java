package maquina1995.raider.io.profiler.dao;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import maquina1995.raider.io.profiler.dto.Profile;

@Component
public class RaiderIoDao {

	private final WebClient webClient;

	/**
	 * Creacion del Webclient primigenio para llamar al api
	 * 
	 * @return {@link WebClient}
	 */
	public RaiderIoDao() {
		webClient = WebClient.builder()
				.baseUrl("https://raider.io/api/v1/characters")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}

	/**
	 * Llamada al api
	 * 
	 * @param regionTextField
	 * @param reinoTextField
	 * @param nombreTextField
	 * @return
	 */
	public Profile callRaiderIoApi(String region, String reino, String nombre) {

		return webClient.get()
				.uri("/profile?region=" + region + "&realm=" + reino + "&name=" + nombre + "&fields=raid_progression")
				.retrieve()
				.bodyToMono(Profile.class)
				.block();
	}

}
