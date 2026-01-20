package freshfridge.freshfridgebackend.integration.openfoodfacts;

import freshfridge.freshfridgebackend.exception.ProductNotFoundException;
import freshfridge.freshfridgebackend.integration.openfoodfacts.dto.OpenFoodFactsProductResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

/**
 * Client wrapper for Open Food Facts API.
 */
@Component
public class OpenFoodFactsClient {

    private final RestClient restClient;

    public OpenFoodFactsClient(RestClient openFoodFactsRestClient) {
        this.restClient = openFoodFactsRestClient;
    }

    public OpenFoodFactsProductResponse getProductByBarcode(String barcode) {
        try {
            OpenFoodFactsProductResponse resp = restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/api/v2/product/{barcode}")
                            // response beperken tot velden die we gebruiken
                            .queryParam("fields", "product_name,categories,brands,image_url")
                            .build(barcode))
                    .retrieve()
                    .body(OpenFoodFactsProductResponse.class);

            if (resp == null || resp.getStatus() == null || resp.getStatus() != 1 || resp.getProduct() == null) {
                throw new ProductNotFoundException("Product niet gevonden voor barcode: " + barcode);
            }
            return resp;
        } catch (ProductNotFoundException ex) {
            throw ex;
        } catch (RestClientException ex) {
            throw new RuntimeException("Open Food Facts niet bereikbaar", ex);
        }
    }
}