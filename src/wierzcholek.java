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
    public static double oblicz_atrakcyjnosc_staly(wierzcholek w)
    {
        return (1-algorytm_mrowkowy.Rho)*w.feromon+Math.pow((w.przedmiot.getCena()/w.przedmiot.getMasa()), algorytm_mrowkowy.Beta);
    }	
    public static double oblicz_atrakcyjnosc_staly_odwiedziny_mrowki(wierzcholek w)
    {
        return w.feromon+algorytm_mrowkowy.Q0;
    }	



}
