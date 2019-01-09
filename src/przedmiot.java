import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class przedmiot {
    private SimpleStringProperty nazwa;
    private SimpleDoubleProperty masa;
    private SimpleDoubleProperty cena;
    

    przedmiot(String nazwa, double masa, double cena) {

        this.nazwa = new SimpleStringProperty(nazwa);
        this.masa = new SimpleDoubleProperty(masa);
        this.cena = new SimpleDoubleProperty(cena);
    }

    public void setNazwa(String n){
        this.nazwa=new SimpleStringProperty(n);
    }

    public String getNazwa(){
        return nazwa.getValue();
    }


    public void setMasa(Double m){
        this.masa=new SimpleDoubleProperty(m);
    }

    public Double getMasa(){
        return masa.get();
    }

    public void setCena(Double c){
        this.cena=new SimpleDoubleProperty(c);
    }

    public Double getCena(){
        return cena.get();
    }

    public String toString(){
        return nazwa+"";
    }

	
	

}


