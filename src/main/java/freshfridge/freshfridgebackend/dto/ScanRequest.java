package freshfridge.freshfridgebackend.dto;

import java.time.LocalDate;

public class ScanRequest {
    private String barcode;
    private Integer koelkastId;
    private LocalDate houdbaarheidsdatum;

    public String getBarcode() { return barcode; }
    public void setBarcode(String barcode) { this.barcode = barcode; }

    public Integer getKoelkastId() { return koelkastId; }
    public void setKoelkastId(Integer koelkastId) { this.koelkastId = koelkastId; }

    public LocalDate getHoudbaarheidsdatum() { return houdbaarheidsdatum; }
    public void setHoudbaarheidsdatum(LocalDate houdbaarheidsdatum) { this.houdbaarheidsdatum = houdbaarheidsdatum; }
}
