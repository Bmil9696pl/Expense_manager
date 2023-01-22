module com.example.to_projekt {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.to_projekt to javafx.fxml;
    exports com.example.to_projekt;
    opens viewPrzychody to javafx.fxml;
    opens viewWydatki to javafx.fxml;
}