package br.com.broadfactor.cadempresas.service.impl;

import br.com.broadfactor.cadempresas.configuration.app.ApplicationConfig;
import br.com.broadfactor.cadempresas.dto.EmpresaDto;
import br.com.broadfactor.cadempresas.exceptions.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class RequestClientService {
	private final ApplicationConfig appConfig;
	private final WebClient client;

	public Mono<EmpresaDto> getEmpresaByCnpj(String cnpj) {
		Map<String, String> context = MDC.getCopyOfContextMap();
		log.info("### Sending request for WS - getEmpresaByCnpj");

		return client.get()
				.uri(appConfig.getWebServicePath(), cnpj)
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError, response -> handleException(response, context))
				.onStatus(HttpStatus::is5xxServerError, response -> handleException(response, context))
				.bodyToMono(EmpresaDto.class)
				.doOnEach(t -> setContext(context));
	}

	public Mono<Throwable> handleException(ClientResponse clientResponse, Map<String, String> context) {
		if (context != null) {
			MDC.setContextMap(context);
		}

		log.error("API request error : {}  {}", clientResponse.rawStatusCode(), clientResponse.statusCode().getReasonPhrase());

		if(clientResponse.statusCode().equals(HttpStatus.TOO_MANY_REQUESTS)) {
			return Mono.error(new TooManyRequestsException("Too many requests from client"));
		} else if (clientResponse.statusCode().equals(HttpStatus.BAD_REQUEST)) {
			return Mono.error(new InvalidArgumentException("Client specified an invalid argument, request body or query param"));
		} else if (clientResponse.statusCode().equals(HttpStatus.BAD_REQUEST)) {
			return Mono.error(new InvalidArgumentException("Client specified an invalid argument, request body or query param"));
		} else if (clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
			return Mono.error(new NotFoundException("A specified resource is not found"));
		} else if(clientResponse.statusCode().equals(HttpStatus.UNAUTHORIZED)) {
			return Mono.error(new UnauthenticatedException("Authetication error"));
		} else if(clientResponse.statusCode().equals(HttpStatus.FORBIDDEN)) {
			return Mono.error(new PermissionDeniedException("Forbidden access"));
		} else if(clientResponse.statusCode().equals(HttpStatus.GATEWAY_TIMEOUT)) {
			return Mono.error(new GatewayTimeoutException("Request timeout exceeded. Try it later"));
		} else {
			return Mono.error(new InternalException(String.format("API request error : %s  %s",
					clientResponse.rawStatusCode(), clientResponse.statusCode().getReasonPhrase())));
		}
	}

	private void setContext(Map<String, String> context) {
		if (context != null) {
			MDC.setContextMap(context);
		}
	}
}
