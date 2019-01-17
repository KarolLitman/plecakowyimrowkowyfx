class wierzcholek {
    Boolean czy_odwiedzony;
    Double feromon;
    przedmiot przedmiot;
    Double delta_tau;

    wierzcholek(przedmiot p) {
        this.przedmiot=p;
        this.feromon= algorytm_mrowkowy.poczatkowy_feromon;
        this.czy_odwiedzony=false;
        delta_tau=0.0;
    }


    public String toString(){
        return przedmiot.getNazwa()+" "+feromon;
    }


    public double odleglosc()
    {
        return this.przedmiot.getCena()/this.przedmiot.getMasa();
    }


    //pamietac tutaj ze jest odwrotnosc

    public double oblicz_atrakcyjnosc(wierzcholek w)
    {
        return w.feromon * Math.pow((w.przedmiot.getMasa()/w.przedmiot.getCena()), algorytm_mrowkowy.Beta);
    }

    public double oblicz_atrakcyjnosc_sysmrowkowy(wierzcholek w)
    {
        return Math.pow(w.feromon,algorytm_mrowkowy.Alpha) * Math.pow((w.przedmiot.getMasa()/w.przedmiot.getCena()), algorytm_mrowkowy.Beta);
    }



}
