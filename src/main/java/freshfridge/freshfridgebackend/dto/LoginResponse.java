package freshfridge.freshfridgebackend.dto;

public class LoginResponse {

    private Integer gebruikernr;
    private String email;
    private String voornaam;

    public LoginResponse(Integer gebruikernr, String email, String voornaam) {
        this.gebruikernr = gebruikernr;
        this.email = email;
        this.voornaam = voornaam;
    }

    public Integer getGebruikernr() {
        return gebruikernr;
    }

    public String getEmail() {
        return email;
    }

    public String getVoornaam() {
        return voornaam;
    }
}
