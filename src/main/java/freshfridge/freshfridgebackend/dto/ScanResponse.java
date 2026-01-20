package freshfridge.freshfridgebackend.dto;

public class ScanResponse {
    private Long productId;
    private String barcode;
    private String naam;
    private Long productInKoelkastId;
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Long getProductInKoelkastId() {
        return productInKoelkastId;
    }

    public void setProductInKoelkastId(Long productInKoelkastId) {
        this.productInKoelkastId = productInKoelkastId;
    }
}

