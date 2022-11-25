package com.example.juhasz_csenge_javafxrestclientdolgozat;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class ListCsillagaszat extends Controller {


    @FXML
    private Button insertButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableView<Csillagaszat> starTable;
    @FXML
    private TableColumn<Csillagaszat, Integer> idCol;
    @FXML
    private TableColumn<Csillagaszat, String> UrallomasCol;
    @FXML
    private TableColumn<Csillagaszat, String> JelentkezokCol;
    @FXML
    private TableColumn<Csillagaszat, Integer> EmailCol;
    @FXML
    private TableColumn<Csillagaszat, Integer> LetszamCol;

    public ListCsillagaszat() {
    }

    public void insertClick(ActionEvent actionEvent) {
    }

    @FXML
    private void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        UrallomasCol.setCellValueFactory(new PropertyValueFactory<>("Urallomas"));
        JelentkezokCol.setCellValueFactory(new PropertyValueFactory<>("Jelentkezok"));
        EmailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
        LetszamCol.setCellValueFactory(new PropertyValueFactory<>("Letszam"));
        Platform.runLater(() -> {
            try {
                loadCsillagaszatFromServer();
            } catch (IOException e) {
                error("Hiba történt az adatok lekérése során", e.getMessage());
                Platform.exit();
            }
        });
    }

    private void loadCsillagaszatFromServer() throws IOException {
        Response response = RequestHandler.get(App.BASE_URL);
        String content = response.getContent();
        Gson converter = new Gson();
        Csillagaszat[] csillagok = converter.fromJson(content, Csillagaszat[].class);
        starTable.getItems().clear();
        for (Csillagaszat csillagok : csillagok) {
            starTable.getItems().add(csillagok);
        }
    }

    @FXML
    public void UpdateClick(ActionEvent actionEvent) {
        Csillagaszat SelectedCsillagaszat = starTable.getSelectionModel().getSelectedItem();
        if(SelectedCsillagaszat == null){
            warning("Törléshez válasz ki előbb egy elemet!");
            return;
        }
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("update.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 640, 480);
            UpdateCsillagaszatController controller = fxmlLoader.getController();
            controller.setUrallomas(SelectedCsillagaszat);
            Stage stage = new Stage();
            stage.setTitle("Update "+ SelectedCsillagaszat.getUrallomas());
            stage.setScene(scene);
            stage.setOnHidden(event ->{
                try{
                    loadCsillagaszatFormServer();
                }
                catch (IOException e){
                    error("Meghíusúlt a kapcsolódás a szerverhez");
                }
            });
            stage.show();
        }
        catch (IOException e){
            error("Hiba történt az űrlap betöltésekor", e.getMessage());
        }
    }



    @FXML
    public void InsertClick(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("create-person-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 640, 480);
            Stage stage = new Stage();
            stage.setTitle("Create person");
            stage.setScene(scene);
            stage.setOnCloseRequest(event -> {
                try {
                    loadCsillagaszatFromServer();
                } catch (IOException e) {
                    error("Nem sikerült kapcsolódni a szerverhez");
                }
            });
            stage.show();
        } catch (IOException e) {
            error("Hiba történt az űrlap betöltése során", e.getMessage());
        }
    }
        }

    @FXML
    public void deleteClick(ActionEvent actionEvent) {
        Csillagaszat selected = starTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            warning("Törléshez előbb válasszon ki egy elemet!");
            return;
        }

        Optional<ButtonType> optionalButtonType =
                alert(Alert.AlertType.CONFIRMATION, "Biztos?",
                        "Biztos, hogy törölni szeretnéd az alábbi állomást: "
                                + selected.getUrallomas(),
                        "");
        if (optionalButtonType.isPresent() &&
                optionalButtonType.get().equals(ButtonType.OK)
        ) {
            String url = App.BASE_URL + "/" + selected.getId();
            try {
                RequestHandler.delete(url);
                loadCsillagaszatFromServer();
            } catch (IOException e) {
                error("Nem sikerült kapcsolódni a szerverhez");
            }
        }
    }
}
