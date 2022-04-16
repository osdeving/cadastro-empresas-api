package br.com.broadfactor.cadempresas.configuration.client;

import br.com.broadfactor.cadempresas.configuration.app.ApplicationConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class WebClientConfig {

	private static final String HEADER_CACHE_CONTROL = "cache-control";
	private static final String HEADER_CACHE_CONTROL_VALUE = "false";
	
	private final ApplicationConfig appConfig;

	public WebClientConfig(ApplicationConfig appConfig) {
		this.appConfig = appConfig;
	}

	@Bean
	public WebClient createWebClient()  {
		return WebClient
				.builder()
				.uriBuilderFactory(getDefaultUriBuilderFactory())
				.defaultHeader(HEADER_CACHE_CONTROL, HEADER_CACHE_CONTROL_VALUE)
				.baseUrl(appConfig.getWebServiceHost())
				.build();
	}

	private DefaultUriBuilderFactory getDefaultUriBuilderFactory(){
		DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(appConfig.getWebServiceHost());
		factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);
		return factory;
	}
}
