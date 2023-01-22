package persystencja;

import bilans.IBilans;
import bilans.Przychody;
import bilans.Wydatki;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Persystencja implements Serializable {
    public ObservableList<IBilans> getListaBilans() {
        List<IBilans> listaBilans = new ArrayList<>();
        listaBilans.addAll(listaPrzychody);
        listaBilans.addAll(listaWydatki);

        return FXCollections.observableArrayList(listaBilans);
    }

    private List<Przychody> listaPrzychody;
    private List<Wydatki> listaWydatki;

    public Persystencja(ObservableList<IBilans> listaBilans) {
        this.listaPrzychody = new ArrayList<>();
        this.listaWydatki = new ArrayList<>();
        for (IBilans bilans :
                listaBilans) {
            if (bilans.getType() == "Przychody"){
                this.listaPrzychody.add((Przychody) bilans);
            } else {
                this.listaWydatki.add((Wydatki) bilans);
            }
        }

    }
}
