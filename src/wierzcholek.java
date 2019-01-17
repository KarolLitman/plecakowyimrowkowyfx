import javafx.geometry.Point2D;

import java.util.Objects;

class wierzcholek implements Comparable<wierzcholek>{
    Boolean czy_odwiedzony;
    Double feromon;
    przedmiot przedmiot;
    Double delta_tau;
    Point2D punkt;

    wierzcholek(przedmiot p) {
        this.przedmiot=p;
        this.feromon= algorytm_mrowkowy.poczatkowy_feromon;
        this.czy_odwiedzony=false;
        delta_tau=0.0;
    }


    public String toString(){
        return przedmiot.getNazwa()+" stosunek: "+this.przedmiot.stosunek() + "feromon :"+feromon;
    }


    public double odleglosc()
    {
        return this.przedmiot.getCena()/this.przedmiot.getMasa();
    }


    //pamietac tutaj ze jest odwrotnosc

    public double oblicz_atrakcyjnosc(wierzcholek w)
    {
//        System.out.println(w.przedmiot+" "+(w.feromon * Math.pow((w.przedmiot.getCena()/w.przedmiot.getMasa()), algorytm_mrowkowy.Beta)));
        return w.feromon * Math.pow((w.przedmiot.getCena()/w.przedmiot.getMasa()), algorytm_mrowkowy.Beta);
    }

    public double oblicz_atrakcyjnosc_sysmrowkowy(wierzcholek w)
    {
        return Math.pow(w.feromon,algorytm_mrowkowy.Alpha) * Math.pow((w.przedmiot.getCena()/w.przedmiot.getMasa()), algorytm_mrowkowy.Beta);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        wierzcholek that = (wierzcholek) o;
        return Objects.equals(przedmiot, that.przedmiot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(przedmiot);
    }

    public int compareTo(wierzcholek w) {
        return -1*this.przedmiot.stosunek().compareTo(w.przedmiot.stosunek());
    }


}
