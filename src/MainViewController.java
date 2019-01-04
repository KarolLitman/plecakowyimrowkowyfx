import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable{


    public TableView <przedmiot> tabelaprzedmiotow;
    public TableColumn <przedmiot, String> colnazwa;
    public TableColumn <przedmiot, Double> colmasa;
    public TableColumn <przedmiot, Double> colcena;


    public void initialize(URL location, ResourceBundle resources) {

        //make sure the property value factory should be exactly same as the e.g getStudentId from your model class
        colnazwa.setCellValueFactory(new PropertyValueFactory<>("Nazwa"));
        colmasa.setCellValueFactory(new PropertyValueFactory<>("Masa"));
        colcena.setCellValueFactory(new PropertyValueFactory<>("Cena"));

        //add your data to the table here.
        tabelaprzedmiotow.setItems(lista);
    }

    ObservableList<przedmiot> lista = FXCollections.observableArrayList(
            new przedmiot("hehe", 10, 50)
    );



    public TextField textfieldMasa;
    public TextField textfieldPrzedmiot;
    public TextField textfieldCena;

    public void dodajprzedmiot(ActionEvent actionEvent) {

        przedmiot p=new przedmiot(textfieldPrzedmiot.getText(),Double.parseDouble(textfieldMasa.getText()),Double.parseDouble(textfieldCena.getText()));
        tabelaprzedmiotow.getItems().add(p);
    }

    public void usunprzedmiot(ActionEvent actionEvent) {

        ObservableList<przedmiot> wszystkie_przedmioty,jeden_przedmiot;
        wszystkie_przedmioty=tabelaprzedmiotow.getItems();
        jeden_przedmiot=tabelaprzedmiotow.getSelectionModel().getSelectedItems();
        jeden_przedmiot.forEach(wszystkie_przedmioty::remove);
    }
}