package bilans;

import java.io.Serializable;
import java.time.LocalDate;

public class Przychody implements IBilans, Serializable {
    private Float wartosc;
    private LocalDate data;
    private String tytuł;
    private String opis;

    public Przychody(String tytuł, Float wartosc, String opis, LocalDate data){
        this.tytuł = tytuł;
        this.wartosc = wartosc;
        this.opis = opis;
        this.data = data;
    }

    @Override
    public String toString() {
        String ret = tytuł + " +" + wartosc.toString() + "zl";
        return ret;
    }

    @Override
    public String getType() {
        return "Przychody";
    }

    @Override
    public Float getWartosc() {
        return wartosc;
    }

    public LocalDate getData() {
        return data;
    }

    public String getTytul() {
        return tytuł;
    }

    public String getOpis() {
        return opis;
    }

    public void setWartosc(Float wartosc) {
        this.wartosc = wartosc;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setTytul(String tytuł) {
        this.tytuł = tytuł;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}