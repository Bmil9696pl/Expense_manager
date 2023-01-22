package com.example.to_projekt;

import bilans.IBilans;
import bilans.Wydatki;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import bilans.Przychody;
import persystencja.Persystencja;
import viewPrzychody.ViewPrzychody;
import viewWydatki.ViewWydatki;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MainView {

    public MainView(){
        this.listaBilans = FXCollections.observableArrayList();
    }
    @FXML
    private Button buttonNastepnyMsc;

    @FXML
    private Button buttonPoprzedniMsc;

    @FXML
    private Button buttonPrzychody;

    @FXML
    private Button buttonWydatki;

    @FXML
    private PieChart chart;

    @FXML
    private TextField textBilans;

    @FXML
    private ListView<IBilans> textHistoria;

    public TextField getTextMiesiac() {
        return textMiesiac;
    }

    @FXML
    private TextField textMiesiac;
    private ObservableList<IBilans> listaBilans;
    private Przychody currentPrzychody;


    private Wydatki currentWydatki;

    public String[] getMiesiace() {
        return miesiace;
    }

    private String[] miesiace = {"Styczen", "Luty", "Marzec", "Kwiecien", "Maj", "Czerwiec", "Lipiec", "Sierpien", "Wrzesien","Pazdziernik" , "Listopad", "Grudzien"};

    public int getNumerMiesiaca() {
        return numerMiesiaca;
    }

    public void setNumerMiesiaca(int numerMiesiaca) {
        this.numerMiesiaca = numerMiesiaca;
    }

    private int numerMiesiaca;

    @FXML
    void buttonNastepnyMscAction(ActionEvent event) throws IOException, ClassNotFoundException {
        Persystencja persystencja = new Persystencja(this.listaBilans);
        FileOutputStream fileOutputStream = new FileOutputStream(this.textMiesiac.getText() + ".ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(persystencja);
        objectOutputStream.flush();
        objectOutputStream.close();
        this.numerMiesiaca++;
        this.textMiesiac.setText(miesiace[this.numerMiesiaca%12]);
        FileInputStream fileInputStream = new FileInputStream(this.textMiesiac.getText() + ".ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        persystencja =(Persystencja)objectInputStream.readObject();
        this.listaBilans = persystencja.getListaBilans();
        this.refreshBilans();
    }

    @FXML
    void buttonPoprzedniMscAction(ActionEvent event) throws IOException, ClassNotFoundException {
        Persystencja persystencja = new Persystencja(this.listaBilans);
        FileOutputStream fileOutputStream = new FileOutputStream(this.textMiesiac.getText() + ".ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(persystencja);
        objectOutputStream.flush();
        objectOutputStream.close();
        this.numerMiesiaca--;
        if(this.numerMiesiaca == -1){
            this.numerMiesiaca = 11;
        }
        this.textMiesiac.setText(miesiace[this.numerMiesiaca%12]);
        FileInputStream fileInputStream = new FileInputStream(this.textMiesiac.getText() + ".ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        persystencja =(Persystencja)objectInputStream.readObject();
        this.listaBilans = persystencja.getListaBilans();
        this.refreshBilans();
    }

    @FXML
    void buttonPrzychodyAction(ActionEvent event) {
        Parent root;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewPrzychody.fxml"));
            root = loader.load();
            ViewPrzychody viewPrzychody = loader.getController();
            viewPrzychody.initialize(this, numerMiesiaca);
            Stage stage = new Stage();
            stage.setTitle("Nowy przychód");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void listOnClickedListener(MouseEvent event) throws IOException {
        IBilans bilans = textHistoria.getSelectionModel().getSelectedItem();
        if(bilans.getType() == "Przychody"){
            Parent root;
            this.currentPrzychody = (Przychody) bilans;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewPrzychody.fxml"));
            root = loader.load();
            ViewPrzychody viewPrzychody = loader.getController();
            viewPrzychody.initialize(this, numerMiesiaca);
            viewPrzychody.setPrzychody(this.currentPrzychody, this.textHistoria.getSelectionModel().getSelectedIndex());
            Stage stage = new Stage();
            stage.setTitle("Edytuj przychód");
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            Parent root;
            this.currentWydatki = (Wydatki) bilans;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewWydatki.fxml"));
            root = loader.load();
            ViewWydatki viewWydatki = loader.getController();
            viewWydatki.initialize(this, numerMiesiaca);
            viewWydatki.setWydatki(this.currentWydatki, this.textHistoria.getSelectionModel().getSelectedIndex());
            Stage stage = new Stage();
            stage.setTitle("Edytuj wydatek");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @FXML
    void buttonWydatkiAction(ActionEvent event) {
        Parent root;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewWydatki.fxml"));
            root = loader.load();
            ViewWydatki viewWydatki = loader.getController();
            viewWydatki.initialize(this, numerMiesiaca);
            Stage stage = new Stage();
            stage.setTitle("Nowy przychód");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<IBilans> getListaBilans() {
        return listaBilans;
    }

    public void setNowePrzychody(Przychody nowePrzychody) {
        listaBilans.add(nowePrzychody);
        this.refreshBilans();
    }

    public void setNoweWydatki(Wydatki noweWydatki) {
        listaBilans.add(noweWydatki);
        this.refreshBilans();
    }

    public void refreshBilans(){
        textHistoria.setItems(listaBilans);
        Float temp = (float) 0;
        Map<String, Float> chartData = new HashMap<>();
        for (IBilans bilans :
                listaBilans) {
            if (bilans.getType() == "Przychody") {
                temp += bilans.getWartosc();
            } else {
                temp -= bilans.getWartosc();
                Wydatki wydatekData = (Wydatki) bilans;
                chartData.merge(wydatekData.getKategoria(), wydatekData.getWartosc(), Float::sum);
            }
        }
        if(temp > 0){
            chartData.merge("Reszta", temp, Float::sum);
        }
        ObservableList<PieChart.Data> pieChartData =
                chartData.entrySet().stream()
                        .map(entry -> new PieChart.Data(entry.getKey(), entry.getValue()))
                        .collect(Collectors.toCollection(() -> FXCollections.observableArrayList()));
        this.textBilans.setText(temp.toString());
        this.chart.setData(pieChartData);
    }


    public void initialize() throws IOException, ClassNotFoundException, InterruptedException {
        FileInputStream fileInputStream = new FileInputStream(this.miesiace[numerMiesiaca] + ".ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Persystencja persystencja =(Persystencja)objectInputStream.readObject();
        this.listaBilans = persystencja.getListaBilans();
        this.refreshBilans();
    }
}
