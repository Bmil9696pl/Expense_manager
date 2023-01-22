package viewPrzychody;

import com.example.to_projekt.MainView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import bilans.Przychody;

import java.time.LocalDate;

public class ViewPrzychody {

    private MainView mainView;
    @FXML
    private DatePicker datePicker;

    @FXML
    private Button okButton;

    @FXML
    private Button usunButton;

    @FXML
    private TextArea opisField;

    @FXML
    private TextField tytulField;

    @FXML
    private TextField wartoscField;

    private Przychody przychody;
    private int index;

    @FXML
    void okButtonAction(ActionEvent event) {
        if(this.przychody == null) {
            Przychody przychody = new Przychody(tytulField.getText(), Float.parseFloat(wartoscField.getText()),
                    opisField.getText(), datePicker.getValue());
            this.mainView.setNowePrzychody(przychody);
        }else {
            this.przychody.setData(datePicker.getValue());
            this.przychody.setOpis(opisField.getText());
            this.przychody.setTytul(tytulField.getText());
            this.przychody.setWartosc(Float.parseFloat(wartoscField.getText()));
            this.mainView.getListaBilans().set(index, this.przychody);
            this.mainView.refreshBilans();
        }
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void usunButtonAction(ActionEvent event) {
        if(this.przychody != null) {
            this.mainView.getListaBilans().remove(index);
            this.mainView.refreshBilans();
        }
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

    public ViewPrzychody(){
        //this.datePicker = new DatePicker(LocalDate.now());
        //this.datePicker.setValue(LocalDate.now());
    }

    public void setPrzychody(Przychody przychody, int index){
        this.przychody = przychody;
        this.index = index;
        this.tytulField.setText(this.przychody.getTytul());
        this.wartoscField.setText(this.przychody.getWartosc().toString());
        this.opisField.setText(this.przychody.getOpis());
        this.datePicker.setValue(this.przychody.getData());
    }

    public void initialize(MainView mainView, int numerMiesiaca) {
        this.mainView = mainView;
        this.datePicker.setValue(LocalDate.of(LocalDate.now().getYear(), numerMiesiaca+1, LocalDate.now().getDayOfMonth()));
    }
}
