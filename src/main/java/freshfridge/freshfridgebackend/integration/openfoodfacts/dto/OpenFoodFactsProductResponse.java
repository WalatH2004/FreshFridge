package freshfridge.freshfridgebackend.integration.openfoodfacts.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Minimal DTO for https://world.openfoodfacts.net/api/v2/product/{barcode}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenFoodFactsProductResponse {

    @JsonProperty("code")
    private String code;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("status_verbose")
    private String statusVerbose;

    @JsonProperty("product")
    private OpenFoodFactsProduct product;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusVerbose() {
        return statusVerbose;
    }

    public void setStatusVerbose(String statusVerbose) {
        this.statusVerbose = statusVerbose;
    }

    public OpenFoodFactsProduct getProduct() {
        return product;
    }

    public void setProduct(OpenFoodFactsProduct product) {
        this.product = product;
    }
}