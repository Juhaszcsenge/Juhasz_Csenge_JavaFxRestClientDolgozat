module com.example.juhasz_csenge_javafxrestclientdolgozat {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens com.example.juhasz_csenge_javafxrestclientdolgozat to javafx.fxml, com.google.gson;
    exports com.example.juhasz_csenge_javafxrestclientdolgozat;
}