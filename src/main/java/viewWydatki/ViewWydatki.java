package viewWydatki;

import bilans.Wydatki;
import com.example.to_projekt.MainView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class ViewWydatki {

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button okButton;

    @FXML
    private Button usunButton;

    @FXML
    private ComboBox<String> kategoriaPicker;

    @FXML
    private TextArea opisField;

    @FXML
    private TextField tytulField;

    @FXML
    private TextField wartoscField;

    private MainView mainView;
    private Wydatki wydatki;
    private int index;

    @FXML
    void okButtonAction(ActionEvent event) {
        if(this.wydatki == null){
            Wydatki wydatki = new Wydatki(this.tytulField.getText(),
                    this.kategoriaPicker.getValue(),
                    Float.parseFloat(this.wartoscField.getText()),
                    this.opisField.getText(), this.datePicker.getValue());
            this.mainView.setNoweWydatki(wydatki);
        }else {
            this.wydatki.setTytuł(tytulField.getText());
            this.wydatki.setOpis(opisField.getText());
            this.wydatki.setWartosc(Float.parseFloat(wartoscField.getText()));
            this.wydatki.setData(datePicker.getValue());
            this.wydatki.setKategoria(kategoriaPicker.getValue());
            this.mainView.getListaBilans().set(index, this.wydatki);
            this.mainView.refreshBilans();
        }
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void usunButtonAction(ActionEvent event) {
        if(this.wydatki != null) {
            this.mainView.getListaBilans().remove(index);
            this.mainView.refreshBilans();
        }
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

    public ViewWydatki(){
    }

    public void setWydatki(Wydatki wydatki, int index){
        this.wydatki = wydatki;
        this.index = index;
        this.datePicker.setValue(this.wydatki.getData());
        this.opisField.setText(this.wydatki.getOpis());
        this.tytulField.setText(this.wydatki.getTytuł());
        this.wartoscField.setText(this.wydatki.getWartosc().toString());
        this.kategoriaPicker.setValue(this.wydatki.getKategoria());
    }

    public void initialize(MainView mainView, int numerMiesiaca){
        this.mainView = mainView;
        ObservableList<String> temp = FXCollections.observableArrayList();
        temp.setAll("Podatki", "Koszty życia", "Hobby", "Nałogi", "Inne");
        this.kategoriaPicker.setItems(temp);
        this.datePicker.setValue(LocalDate.of(LocalDate.now().getYear(), numerMiesiaca+1, LocalDate.now().getDayOfMonth()));
    }
}
