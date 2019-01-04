class wierzcholek {
    Boolean czy_odwiedzony;
    Double feromon;
    przedmiot przedmiot;

    wierzcholek(przedmiot p) {
        this.przedmiot=p;
        this.feromon= algorytm_mrowkowy.poczatkowy_feromon;
        this.czy_odwiedzony=false;

    }


    public String toString(){
        return przedmiot.getNazwa()+"";
    }


    public double oblicz_atrakcyjnosc(wierzcholek w)
    {
        return w.feromon * Math.pow((w.przedmiot.getCena()/w.przedmiot.getMasa()), algorytm_mrowkowy.Beta);
    }




}
