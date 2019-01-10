import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable{


    public TableView <przedmiot> tabelaprzedmiotow;
    public TableColumn <przedmiot, String> colnazwa;
    public TableColumn <przedmiot, Double> colmasa;
    public TableColumn <przedmiot, Double> colcena;
//    public Slider option_a;
//    public Label value_a;
    public Slider slider_rho;
    public Slider slider_mrowki;
    public Slider slider_q0;
    public Label wartosc_alfa;
    public Label wartosc_beta;
    public Label wartosc_fermon;
    public Label wartosc_rho;
    public Label wartosc_q0;
    public Label wartosc_mrowki;
    public Label wartosc_masa;
    public Slider slider_alfa;
    public Slider slider_beta;
    public Slider slider_pferomon;
    public Label wartosc_pferomon;
    public Slider slider_masa;
    public ChoiceBox choicebox_feromon;

    @FXML
    private TableView<?> tabelaprzedmiotow1;

    @FXML
    private TableColumn<?, ?> colnazwa1;

    @FXML
    private TableColumn<?, ?> colmasa1;

    @FXML
    private TableColumn<?, ?> colcena1;

    @FXML
    
    public void initialize(URL location, ResourceBundle resources) {

        //suwaki
        wartosc_alfa.setText(String.format("%.2f", slider_alfa.getValue()));
        wartosc_beta.setText(String.format("%.2f", slider_beta.getValue()));
        wartosc_pferomon.setText(String.format("%.2f", slider_pferomon.getValue()));
        wartosc_rho.setText(String.format("%.2f", slider_rho.getValue()));
        wartosc_q0.setText(String.format("%.2f", slider_q0.getValue()));
        wartosc_mrowki.setText(String.format("%d", (int) slider_mrowki.getValue()));
        wartosc_masa.setText(String.format("%.2f", slider_masa.getValue()));


        //choicebox
        choicebox_feromon.getItems().addAll("stały", "średni","cykliczny");
        choicebox_feromon.getSelectionModel().selectFirst();



        slider_alfa.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                wartosc_alfa.setText(String.format("%.2f", new_val));
            }
        });

        slider_alfa.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasChanging, Boolean isNowChanging) {
//                if (! isNowChanging)
//                    runonupdate();
            }
        });

        slider_beta.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                wartosc_beta.setText(String.format("%.2f", new_val));
            }
        });

        slider_beta.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasChanging, Boolean isNowChanging) {
//                if (! isNowChanging)
//                    runonupdate();
            }
        });



        slider_pferomon.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                wartosc_pferomon.setText(String.format("%.2f", new_val));
            }
        });

        slider_pferomon.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasChanging, Boolean isNowChanging) {
//                if (! isNowChanging)
//                    runonupdate();
            }
        });




        slider_rho.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                wartosc_rho.setText(String.format("%.2f", new_val));
            }
        });

        slider_rho.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasChanging, Boolean isNowChanging) {
//                if (! isNowChanging)
//                    runonupdate();
            }
        });

        slider_q0.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                wartosc_q0.setText(String.format("%.2f", new_val));
            }
        });

        slider_q0.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasChanging, Boolean isNowChanging) {
//                if (! isNowChanging)
//                    runonupdate();
            }
        });


//        slider_mrowki.valueProperty().addListener(new ChangeListener<Number>() {
//            public void changed(ObservableValue<? extends Number> ov,
//                                Number old_val, Number new_val) {
//                wartosc_mrowki.setText(String.format("%d", new_val));
//            }
//        });
//
//        slider_mrowki.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasChanging, Boolean isNowChanging) {
////                if (! isNowChanging)
////                    runonupdate();
//            }
//        });


        slider_masa.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                wartosc_masa.setText(String.format("%.2f", new_val));
            }
        });

        slider_masa.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasChanging, Boolean isNowChanging) {
//                if (! isNowChanging)
//                    runonupdate();
            }
        });


        long time_delta = 0; // time counting
        int n; // number of nodes
        int m; // number of ants
        double shortest_length; // best road length
//        ArrayList<Integer> shortest; // beast road nodes
        double Q; // pheromones value by 1 ant
        double ro; // disappearance quotient
        int C_max; // maximum iterations number
        int c; // iteration index
        double[][] tau; // pheromones value
//        List<ArrayList<Integer>> tabu; // list of road at current iteration




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