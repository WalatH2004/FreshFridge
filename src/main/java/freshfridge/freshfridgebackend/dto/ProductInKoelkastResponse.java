package freshfridge.freshfridgebackend.dto;

import java.time.LocalDate;

public class ProductInKoelkastResponse {
    private int pikId;
    private LocalDate houdbaarheidsdatum;
    private LocalDate toegevoegdOp;

    private int productId;
    private String barcode;
    private String naam;
    private String categorie;
    private String imageUrl;
    private int aantal;

    public ProductInKoelkastResponse() {}

    public ProductInKoelkastResponse(
            int pikId,
            LocalDate houdbaarheidsdatum,
            LocalDate toegevoegdOp,
            int productId,
            String barcode,
            String naam,
            String categorie,
            String imageUrl,
            int aantal
    ) {
        this.pikId = pikId;
        this.houdbaarheidsdatum = houdbaarheidsdatum;
        this.toegevoegdOp = toegevoegdOp;
        this.productId = productId;
        this.barcode = barcode;
        this.naam = naam;
        this.categorie = categorie;
        this.imageUrl = imageUrl;
        this.aantal = aantal;
    }

    public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public int getPikId() { return pikId; }
    public void setPikId(int pikId) { this.pikId = pikId; }

    public LocalDate getHoudbaarheidsdatum() { return houdbaarheidsdatum; }
    public void setHoudbaarheidsdatum(LocalDate houdbaarheidsdatum) { this.houdbaarheidsdatum = houdbaarheidsdatum; }

    public LocalDate getToegevoegdOp() { return toegevoegdOp; }
    public void setToegevoegdOp(LocalDate toegevoegdOp) { this.toegevoegdOp = toegevoegdOp; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getBarcode() { return barcode; }
    public void setBarcode(String barcode) { this.barcode = barcode; }

    public String getNaam() { return naam; }
    public void setNaam(String naam) { this.naam = naam; }

    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }
}