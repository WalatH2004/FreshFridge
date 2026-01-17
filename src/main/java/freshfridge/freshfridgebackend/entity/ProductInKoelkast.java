package freshfridge.freshfridgebackend.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ProductInKoelkast")
public class ProductInKoelkast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pikId")
    private Integer pikId;

    @Column(name = "houdbaarheidsdatum", nullable = false)
    private LocalDate houdbaarheidsdatum;

    @Column(name = "toegevoegdOp", nullable = false)
    private LocalDate toegevoegdOp;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "koelkastId", nullable = false)
    private Koelkast koelkast;

    public ProductInKoelkast() {
    }

    public Integer getPikId() {
        return pikId;
    }

    public void setPikId(Integer pikId) {
        this.pikId = pikId;
    }

    public LocalDate getHoudbaarheidsdatum() {
        return houdbaarheidsdatum;
    }

    public void setHoudbaarheidsdatum(LocalDate houdbaarheidsdatum) {
        this.houdbaarheidsdatum = houdbaarheidsdatum;
    }

    public LocalDate getToegevoegdOp() {
        return toegevoegdOp;
    }

    public void setToegevoegdOp(LocalDate toegevoegdOp) {
        this.toegevoegdOp = toegevoegdOp;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Koelkast getKoelkast() {
        return koelkast;
    }

    public void setKoelkast(Koelkast koelkast) {
        this.koelkast = koelkast;
    }
}
