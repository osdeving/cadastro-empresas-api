package br.com.broadfactor.cadempresas.configuration.app;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ApplicationConfig {
    private final String webServicePath;
    private final String webServiceHost;

    public ApplicationConfig(
            @Value("${cadempresa.ws.empresa.host}") String webServiceUrl,
            @Value("${cadempresa.ws.empresa.path}") String webServicePath) {
        this.webServiceHost = webServiceUrl;
        this.webServicePath = webServicePath;
    }
}
