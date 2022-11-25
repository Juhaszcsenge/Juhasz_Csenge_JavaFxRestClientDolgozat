package com.example.juhasz_csenge_javafxrestclientdolgozat;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    public void updateClick(ActionEvent actionEvent) {
    }
}
