package com.example.juhasz_csenge_javafxrestclientdolgozat;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Update extends Controller {
    @FXML
    private TextField UrallomasField;
    @FXML
    private TextField JelentkezokField;

    @FXML
    private TextField EmailField;

    @FXML
    private Spinner<Integer> LetszamField;
    @FXML
    private Button submitButton;

    @FXML
    private void initialize() {
        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 200, 30);
        LetszamField.setValueFactory(valueFactory);
    }
    @FXML
    public void updateClick(ActionEvent actionEvent) {
        String Urallomas = UrallomasField.getText().trim();
        String Jelentkezok = JelentkezokField.getText().trim();
        String Email = EmailField.getText().trim();
        int Letszam = LetszamField.getValue();
        if (Urallomas.isEmpty()){
            warning("Állomás megadása kötelező");
            return;
        }
        if(Email.isEmpty()){
            warning("Email megadása kötelező");
            return;
        }
    }
    this.Csillagaszat.setUrallomas(Urallomas);
    this.Csillagaszat.setjelentkezok(jelentkezok);
    this.Csillagaszat.setLetszam(Letszam);
    this.Csillagaszat.setEmail(Email);
    Gson converter = new Gson();
    String json = converter.toJson(this.Csillagaszat);
    try{
        String url = App.BASE_URL + "/" + this.Csillagaszat.getId();
        Response response = RequestHandler.put(url, json);
        if (response.getResponseCode() == 200){
            Stage stage = (Stage) this.updateButton.getScene().getWindow();
            stage.close();
        }
        else {
            error("Hiba történik amódosítás során", response.getContent());
        }
    } catch (IOException e){
        error("Nem sikerült kapcsolódni a szerverhez");
    }
}
