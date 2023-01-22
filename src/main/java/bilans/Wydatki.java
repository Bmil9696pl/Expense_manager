package bilans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Wydatki implements IBilans, Serializable {
    private Float wartosc;
    private String kategoria;
    private LocalDate data;
    private String tytuł;
    private String opis;

    public Wydatki(String tytuł, String kategoria, Float wartosc, String opis, LocalDate data){
        this.tytuł = tytuł;
        this.kategoria = kategoria;
        this.wartosc = wartosc;
        this.opis = opis;
        this.data = data;
    }

    @Override
    public String toString() {
        String ret = tytuł + " -" + wartosc.toString() + "zl";
        return ret;
    }

    @Override
    public String getType() {
        return "Wydatki";
    }
    @Override
    public Float getWartosc() {
        return wartosc;
    }

    public void setWartosc(Float wartosc) {
        this.wartosc = wartosc;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getTytuł() {
        return tytuł;
    }

    public void setTytuł(String tytuł) {
        this.tytuł = tytuł;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
