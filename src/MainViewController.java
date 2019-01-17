import javafx.geometry.Point2D;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainViewController implements Initializable{




    @FXML
    private Canvas canvas;

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


    ArrayList<Point2D> nodes = new ArrayList<Point2D>();
    problem_plecakowy p;

    public void initialize(URL location, ResourceBundle resources) {


        p=new problem_plecakowy();
        p.wszystkie_przedmioty.add((new przedmiot("s1",20,100)));
        p.wszystkie_przedmioty.add((new przedmiot("s2",500,650)));
        p.wszystkie_przedmioty.add((new przedmiot("s3",30,450)));
        p.wszystkie_przedmioty.add((new przedmiot("s4",410,400)));
        p.wszystkie_przedmioty.add((new przedmiot("s5",600,650)));
        p.wszystkie_przedmioty.add((new przedmiot("s6",500,568)));p.wszystkie_przedmioty.add((new przedmiot("s7",200,267)));p.wszystkie_przedmioty.add((new przedmiot("s8",30,287)));p.wszystkie_przedmioty.add((new przedmiot("s9",250,290)));p.wszystkie_przedmioty.add((new przedmiot("s10",50,514)));p.wszystkie_przedmioty.add((new przedmiot("s11",200,273)));p.wszystkie_przedmioty.add((new przedmiot("s12",400,444)));p.wszystkie_przedmioty.add((new przedmiot("s13",80,279)));p.wszystkie_przedmioty.add((new przedmiot("s14",690,290)));p.wszystkie_przedmioty.add((new przedmiot("s15",100,482)));p.wszystkie_przedmioty.add((new przedmiot("s16",600,423)));p.wszystkie_przedmioty.add((new przedmiot("s17",300,452)));p.wszystkie_przedmioty.add((new przedmiot("s18",130,377)));p.wszystkie_przedmioty.add((new przedmiot("s19",140,174)));p.wszystkie_przedmioty.add((new przedmiot("s20",150,424)));p.wszystkie_przedmioty.add((new przedmiot("s21",160,464)));p.wszystkie_przedmioty.add((new przedmiot("s22",170,242)));p.wszystkie_przedmioty.add((new przedmiot("s23",260,208)));p.wszystkie_przedmioty.add((new przedmiot("s24",190,232)));p.wszystkie_przedmioty.add((new przedmiot("s25",200,321)));p.wszystkie_przedmioty.add((new przedmiot("s26",210,306)));p.wszystkie_przedmioty.add((new przedmiot("s27",220,427)));p.wszystkie_przedmioty.add((new przedmiot("s28",230,800)));p.wszystkie_przedmioty.add((new przedmiot("s29",240,452)));p.wszystkie_przedmioty.add((new przedmiot("s30",250,279)));p.wszystkie_przedmioty.add((new przedmiot("s31",260,192)));p.wszystkie_przedmioty.add((new przedmiot("s32",270,600)));p.wszystkie_przedmioty.add((new przedmiot("s33",280,556)));p.wszystkie_przedmioty.add((new przedmiot("s34",490,478)));p.wszystkie_przedmioty.add((new przedmiot("s35",300,382)));p.wszystkie_przedmioty.add((new przedmiot("s36",310,890)));p.wszystkie_przedmioty.add((new przedmiot("s37",320,364)));p.wszystkie_przedmioty.add((new przedmiot("s38",330,572)));p.wszystkie_przedmioty.add((new przedmiot("s39",340,441)));p.wszystkie_przedmioty.add((new przedmiot("s40",350,132)));p.wszystkie_przedmioty.add((new przedmiot("s41",360,537)));p.wszystkie_przedmioty.add((new przedmiot("s42",370,379)));p.wszystkie_przedmioty.add((new przedmiot("s43",380,600)));p.wszystkie_przedmioty.add((new przedmiot("s44",390,527)));p.wszystkie_przedmioty.add((new przedmiot("s45",400,550)));p.wszystkie_przedmioty.add((new przedmiot("s46",410,590)));p.wszystkie_przedmioty.add((new przedmiot("s47",420,221)));p.wszystkie_przedmioty.add((new przedmiot("s48",430,800)));p.wszystkie_przedmioty.add((new przedmiot("s49",440,335)));p.wszystkie_przedmioty.add((new przedmiot("s50",450,396)));p.wszystkie_przedmioty.add((new przedmiot("s51",460,1200)));p.wszystkie_przedmioty.add((new przedmiot("s52",470,256)));p.wszystkie_przedmioty.add((new przedmiot("s53",480,437)));p.wszystkie_przedmioty.add((new przedmiot("s54",490,900)));p.wszystkie_przedmioty.add((new przedmiot("s55",1000,427)));

        double sizex = canvas.getWidth();
        double sizey = canvas.getHeight();





        for (int x = 0; x < p.wszystkie_przedmioty.size(); x++) {
            nodes.add(new Point2D(Math.random() * sizex, Math.random() * sizey));
        }


        draw_nodes();

        ArrayList<Integer>sciezka = new ArrayList<Integer>(2);


        for(int i=0;i<p.wszystkie_przedmioty.size()-1;i++){
            sciezka.add(i);
            sciezka.add(i+1);
        }


        draw_road(sciezka, (int) 10, Color.BLUE, true, 3);


//        p.wszystkie_przedmioty.add((new przedmiot("s56",510,575)));p.wszystkie_przedmioty.add((new przedmiot("s57",520,336)));p.wszystkie_przedmioty.add((new przedmiot("s58",530,330)));p.wszystkie_przedmioty.add((new przedmiot("s59",540,493)));p.wszystkie_przedmioty.add((new przedmiot("s60",550,324)));p.wszystkie_przedmioty.add((new przedmiot("s61",560,264)));p.wszystkie_przedmioty.add((new przedmiot("s62",570,408)));p.wszystkie_przedmioty.add((new przedmiot("s63",580,342)));p.wszystkie_przedmioty.add((new przedmiot("s64",590,505)));p.wszystkie_przedmioty.add((new przedmiot("s65",600,366)));p.wszystkie_przedmioty.add((new przedmiot("s66",610,479)));p.wszystkie_przedmioty.add((new przedmiot("s67",620,504)));p.wszystkie_przedmioty.add((new przedmiot("s68",630,185)));p.wszystkie_przedmioty.add((new przedmiot("s69",640,247)));p.wszystkie_przedmioty.add((new przedmiot("s70",650,232)));p.wszystkie_przedmioty.add((new przedmiot("s71",660,136)));p.wszystkie_przedmioty.add((new przedmiot("s72",670,496)));p.wszystkie_przedmioty.add((new przedmiot("s73",680,572)));p.wszystkie_przedmioty.add((new przedmiot("s74",690,588)));p.wszystkie_przedmioty.add((new przedmiot("s75",700,493)));p.wszystkie_przedmioty.add((new przedmiot("s76",710,575)));p.wszystkie_przedmioty.add((new przedmiot("s77",720,256)));p.wszystkie_przedmioty.add((new przedmiot("s78",730,387)));p.wszystkie_przedmioty.add((new przedmiot("s79",740,332)));p.wszystkie_przedmioty.add((new przedmiot("s80",750,573)));p.wszystkie_przedmioty.add((new przedmiot("s81",760,392)));p.wszystkie_przedmioty.add((new przedmiot("s82",770,230)));p.wszystkie_przedmioty.add((new przedmiot("s83",780,218)));p.wszystkie_przedmioty.add((new przedmiot("s84",790,174)));p.wszystkie_przedmioty.add((new przedmiot("s85",800,161)));p.wszystkie_przedmioty.add((new przedmiot("s86",810,206)));p.wszystkie_przedmioty.add((new przedmiot("s87",820,366)));p.wszystkie_przedmioty.add((new przedmiot("s88",830,581)));p.wszystkie_przedmioty.add((new przedmiot("s89",840,546)));p.wszystkie_przedmioty.add((new przedmiot("s90",850,314)));p.wszystkie_przedmioty.add((new przedmiot("s91",860,273)));p.wszystkie_przedmioty.add((new przedmiot("s92",870,381)));p.wszystkie_przedmioty.add((new przedmiot("s93",880,323)));p.wszystkie_przedmioty.add((new przedmiot("s94",890,169)));p.wszystkie_przedmioty.add((new przedmiot("s95",900,255)));p.wszystkie_przedmioty.add((new przedmiot("s96",910,157)));p.wszystkie_przedmioty.add((new przedmiot("s97",920,253)));p.wszystkie_przedmioty.add((new przedmiot("s98",930,382)));p.wszystkie_przedmioty.add((new przedmiot("s99",940,109)));p.wszystkie_przedmioty.add((new przedmiot("s100",950,329)));p.wszystkie_przedmioty.add((new przedmiot("s101",960,359)));p.wszystkie_przedmioty.add((new przedmiot("s102",970,221)));p.wszystkie_przedmioty.add((new przedmiot("s103",980,175)));p.wszystkie_przedmioty.add((new przedmiot("s104",990,302)));p.wszystkie_przedmioty.add((new przedmiot("s105",1000,257)));

        try {
            algorytm_mrowkowy aw=new algorytm_mrowkowy(p);
        } catch (Exception e) {
            e.printStackTrace();
        }


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


    public void draw_nodes(){
        int n = nodes.size();
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (int x = 0; x < n; x++) {
            Point2D node = nodes.get(x);
            Float radius = 4.0f;
            gc.setStroke(Color.BLUE);
            gc.setLineWidth(1);
            gc.strokeOval(node.getX(), node.getY(), radius, radius);
        }
    }



    public void draw_road(ArrayList<Integer> sciezka, int offset, Color color, boolean loop, int width) {
        ArrayList<Point2D> punkty_sciezka = modyfikuj_sciezke(sciezka, offset);
        int n = nodes.size();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(color);
        gc.setLineWidth(width);
        for (int x = 1; x < n; x++) {
            Point2D from = punkty_sciezka.get(x - 1);
            Point2D to = punkty_sciezka.get(x);
            gc.strokeLine(from.getX(), from.getY(), to.getX(), to.getY());
        }
        if (loop) {
            Point2D from = punkty_sciezka.get(0);
            Point2D to = punkty_sciezka.get(punkty_sciezka.size() - 1);
            gc.strokeLine(from.getX(), from.getY(), to.getX(), to.getY());
        }
    }

    public ArrayList<Point2D> modyfikuj_sciezke(ArrayList<Integer> sciezka, int offset) {
        ArrayList<Point2D> modified_road = new ArrayList<Point2D>(algorytm_mrowkowy.ilosc_mrowek);
        Point2D poprzedni_wierzcholek = nodes.get(sciezka.get(0));
        Point2D nastepny_wierzcholek;
        for (int x = 1; x < (p.wszystkie_przedmioty.size()+1); x++) {
            if (x==p.wszystkie_przedmioty.size()) {
                nastepny_wierzcholek = nodes.get(sciezka.get(0));
            } else{
                nastepny_wierzcholek = nodes.get(sciezka.get(x));
            }
            double delta_x = Math.abs(poprzedni_wierzcholek.getX() - nastepny_wierzcholek.getX());
            double delta_y = Math.abs(poprzedni_wierzcholek.getY() - nastepny_wierzcholek.getY());
            Point2D nowy_wierzcholek;
            if (delta_x < delta_y) {
                nowy_wierzcholek = new Point2D(poprzedni_wierzcholek.getX()-offset, poprzedni_wierzcholek.getY());
            } else {
                nowy_wierzcholek = new Point2D(poprzedni_wierzcholek.getX(), poprzedni_wierzcholek.getY()-offset);
            }
            modified_road.add(nowy_wierzcholek);
            poprzedni_wierzcholek = nastepny_wierzcholek;
        }
        return modified_road;
    }


}