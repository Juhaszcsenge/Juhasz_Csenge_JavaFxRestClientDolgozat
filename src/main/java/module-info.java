module com.example.juhasz_csenge_javafxrestclientdolgozat {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.juhasz_csenge_javafxrestclientdolgozat to javafx.fxml;
    exports com.example.juhasz_csenge_javafxrestclientdolgozat;
}