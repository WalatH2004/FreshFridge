package freshfridge.freshfridgebackend.integration.openfoodfacts;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

/**
 * Central place to configure the Open Food Facts HTTP client.
 */
@Configuration
public class OpenFoodFactsConfig {

    /**
     * Open Food Facts recommends sending a descriptive User-Agent.
     * Pas dit aan met jouw echte contact/URL.
     */
    private static final String USER_AGENT =
            "FreshFridge/1.0 (https://localhost; contact: freshfridge@example.com)";

    @Bean
    public RestClient openFoodFactsRestClient() {
        return RestClient.builder()
                .baseUrl("https://world.openfoodfacts.net")
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.USER_AGENT, USER_AGENT)
                .build();
    }
}