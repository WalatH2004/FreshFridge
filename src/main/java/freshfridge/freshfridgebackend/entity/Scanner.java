package freshfridge.freshfridgebackend.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Scanner")
public class Scanner {

    @Id
    @Column(name = "scannerId")
    private Integer scannerId;        // zelf zetten bij insert, of @GeneratedValue erbij als kolom auto_increment is

    @Column(name = "naam")
    private String naam;

    @Column(name = "garantie", nullable = false)
    private String garantie;

    //@OneToMany(mappedBy = "scanner")
    //private List<ProductInKoelkast> productenInKoelkast;

    public Scanner() {
    }

    public Integer getScannerId() {
        return scannerId;
    }

    public void setScannerId(Integer scannerId) {
        this.scannerId = scannerId;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getGarantie() {
        return garantie;
    }

    public void setGarantie(String garantie) {
        this.garantie = garantie;
    }

    //public List<ProductInKoelkast> getProductenInKoelkast() {
        //return productenInKoelkast;
    //}

    //public void setProductenInKoelkast(List<ProductInKoelkast> productenInKoelkast) {
        //this.productenInKoelkast = productenInKoelkast;
    //}
}

