import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class algorytm_mrowkowy {


    static public double poczatkowy_feromon = 0.01;
    static public double Rho = 0.001;
    static public double Beta = 5;
    static public double Alpha = 1;
    static public double q0 = 0.01;
    static public int ilosc_mrowek = 2;
    static public int system=0;
    static public int ilosc_cykli=30;


    static public double min=0.01;
    static public double max=2.0;

    //s.mrowiskowy ACS 0






    //s.mrowkowy f. staly 1
    //mrowkowy f. sredni 2
    //mrowkowy f. cykliczny 3
    //mrowkowy min/max 4
    //mrowkowy elitarny 5 ??

    static public double Q=0.001;
    
    
    
    ArrayList<wierzcholek> lista_wierzcholkow;
     mrowka[] mrowki;
     mrowka najlepsza_mrowka;
    problem_plecakowy pp;
     double najlepsza_sciezka = 0;


    algorytm_mrowkowy(problem_plecakowy pp) throws Exception {


        if(algorytm_mrowkowy.ilosc_mrowek<1) {
            throw new Exception("Nieprawidowy liczba mrowek");
        }
        else if(algorytm_mrowkowy.poczatkowy_feromon<=0) {
            throw new Exception("Nieprawidowy poczatkowy feromon ");
        }
        else if(algorytm_mrowkowy.Beta<0) {
            throw new Exception("Nieprawidowy liczba Beta ");
        }
        else if(algorytm_mrowkowy.Rho<0 || algorytm_mrowkowy.Rho>1) {
            throw new Exception("Nieprawidowy liczba Rho");
        }
        else if(algorytm_mrowkowy.Alpha<0 || algorytm_mrowkowy.Alpha>1) {
            throw new Exception("Nieprawidowy liczba Alpha");
        }
        else if(algorytm_mrowkowy.q0<0 || algorytm_mrowkowy.q0>1) {
            throw new Exception("Nieprawidowy liczba q0");
        }


//System.out.println(lista_wierzcholkow);
//System.out.println("Wartosc max: "+mrowka.wynik_max);
//System.out.println("Wartosc srednia: "+ (mrowka.wynik_srednia/algorytm_mrowkowy.ilosc_mrowek));
//System.out.println(mrowka.iteracje);

        this.pp=pp;


    }


    rozwiazanie wylicz_rozwiazanie() throws Exception {
        rozwiazanie rozw = null;

        List<przedmiot> lista_przedmitow = pp.wszystkie_przedmioty;
        lista_wierzcholkow = new ArrayList<>();


        for (int j = 0; j < lista_przedmitow.size(); j++)
        {
            lista_wierzcholkow.add(new wierzcholek(lista_przedmitow.get(j)));
        }

        mrowki = new mrowka[algorytm_mrowkowy.ilosc_mrowek];
        for (int i = 0; i < algorytm_mrowkowy.ilosc_mrowek; i++)
        {
            mrowki[i] = new mrowka(lista_wierzcholkow);
        }

        if(system==0)
           rozw=wykonaj();
        else
            rozw=wykonaj_mrowkowy();


        return rozw;
    }


    rozwiazanie wykonaj() throws Exception {

        int trafienia=0;
        int pierwsze_trafienie=0;
        int iteracja=0;

        double maksymalna_wartosc = Double.MIN_VALUE;
        double minimalna_wartosc = Double.MAX_VALUE;
        double srednia_wartosc_cyklu = 0;
        double najlepsze_rozwiazanie = 0;

        double tmp;

        Random r=new Random();


        //cykle
        for(int i=0;i<algorytm_mrowkowy.ilosc_cykli;i++) {
            srednia_wartosc_cyklu=0;
            maksymalna_wartosc=0;
            minimalna_wartosc=0;
            for (mrowka m : mrowki) {


                m.reset();
                m.odwiedz_wierzcholek(lista_wierzcholkow.get(r.nextInt(lista_wierzcholkow.size())));

//            System.out.println("Pierwszy wylosowany wierzcholek "+m.odwiedzone_wierzcholki);

                m.run();

                tmp = m.rozwiazanie();


                srednia_wartosc_cyklu += tmp;
                if (tmp < minimalna_wartosc) {
                    minimalna_wartosc = tmp;
                }
                if (tmp > maksymalna_wartosc) {
                    maksymalna_wartosc = tmp;
                    najlepsza_mrowka = m;
                }
                if(tmp==najlepsza_mrowka.rozwiazanie()){
                    trafienia++;
                }
            }


            srednia_wartosc_cyklu = srednia_wartosc_cyklu / algorytm_mrowkowy.ilosc_mrowek;

            if (this.najlepsza_sciezka < maksymalna_wartosc) {
                this.najlepsza_sciezka = maksymalna_wartosc;
            }

            globalny_feromon(najlepsza_mrowka);

            System.out.println("Srednia wartosc z cyklu "+srednia_wartosc_cyklu);
            System.out.println("Najlepsze rozw "+najlepsza_mrowka.rozwiazanie());

            if(najlepsze_rozwiazanie<najlepsza_mrowka.rozwiazanie()){
                najlepsze_rozwiazanie=najlepsza_mrowka.rozwiazanie();
                trafienia=1;
                pierwsze_trafienie=i;
            }



        }
        System.out.println("Najlepsze rozwiazanie "+ najlepsze_rozwiazanie+" cykl nr: "+iteracja);

        return new rozwiazanie(najlepsza_mrowka.rozwiazanie(),trafienia,pierwsze_trafienie,najlepsza_mrowka.plecak);

    }





    rozwiazanie wykonaj_mrowkowy() throws Exception {

        mrowka najlepsza_mrowka_w_iter = null;
        double najlepsza_droga_w_iter=0;

        int trafienia=0;
        int pierwsze_trafienie=0;

//        najlepsza_mrowka=new mrowka();

        //todo: zrobic fermon staly,sredni i cykliczny
        double maksymalna_wartosc = Double.MIN_VALUE;
        double minimalna_wartosc = Double.MAX_VALUE;
        double srednia_wartosc_cyklu = 0;
        double tmp;

        Random r = new Random();
        for(int j=0;j<algorytm_mrowkowy.ilosc_cykli;j++) {
            najlepsza_droga_w_iter=0;
            for (mrowka m : mrowki) {
                m.reset();
            }

                for (int i = 0; i < pp.wszystkie_przedmioty.size(); i++) {
                for (mrowka m : mrowki) {


//            System.out.println("Pierwszy wylosowany wierzcholek "+m.odwiedzone_wierzcholki);

                    if (m.czy_mrowka_zyje) {
                        wierzcholek w = m.wybierz_nastepny_wierzcholek();


                        if (m.odwiedzone_wierzcholki.size() == pp.wszystkie_przedmioty.size() || w == null) {
                            m.czy_mrowka_zyje = false;
                        } else {
                            m.odwiedz_wierzcholek(w);
                        }

                        if (m.czy_mrowka_zyje) {
                            m.dlugosc_trasy += w.odleglosc();
                            if (algorytm_mrowkowy.system == 1) {
                                w.delta_tau += algorytm_mrowkowy.Q;
//                                System.out.println(m + " " + w);
                            } else if (algorytm_mrowkowy.system == 2) {
                                w.delta_tau += algorytm_mrowkowy.Q / w.odleglosc();
//                                System.out.println(m + " " + w);
                            }
                        }
//                    tmp = m.rozwiazanie();

//                    srednia_wartosc_cyklu += tmp;
//                    if (tmp < minimalna_wartosc) {
//                        minimalna_wartosc = tmp;
//                    }
//                    if (tmp > maksymalna_wartosc) {
//                        maksymalna_wartosc = tmp;
//                        najlepsza_mrowka = m;
//                    }


                    }


                    if (m.wylicz_droge() > najlepsza_droga_w_iter) {
                        najlepsza_mrowka_w_iter=m;
                        najlepsza_droga_w_iter=m.wylicz_droge();
                    }

//                if(!m.czy_mrowka_zyje){
//                    System.out.println("xd");
//                    for(wierzcholek w:m.odwiedzone_wierzcholki){
//                        w.feromon=(1 - algorytm_mrowkowy.Rho) * w.feromon + w.delta_tau;
//
//                    }
//                    break;
//                }


//                if (this.najlepsza_sciezka < maksymalna_wartosc) {
//                    this.najlepsza_sciezka = maksymalna_wartosc;
//                }


//                    tmp = m.rozwiazanie();
//                    if (tmp > maksymalna_wartosc) {
//                        maksymalna_wartosc = tmp;
//                        najlepsza_mrowka = m;
//                        pierwsze_trafienie=j;
//                        trafienia=1;
//                    }
//                    if(tmp==maksymalna_wartosc){
//                        trafienia++;
//                    }
                    tmp = m.rozwiazanie();
                    if (tmp > maksymalna_wartosc) {
                        maksymalna_wartosc = tmp;
                        pierwsze_trafienie=j;
                        najlepsza_mrowka = m;
                        trafienia=1;

                    }
                    if(tmp==maksymalna_wartosc){
                        trafienia++;
                    }
                }
            }
//




            for (mrowka m : mrowki) {
                for (wierzcholek w : m.odwiedzone_wierzcholki) {
                    if (algorytm_mrowkowy.system == 3 || algorytm_mrowkowy.system == 4 || algorytm_mrowkowy.system == 5) {
                        w.delta_tau += algorytm_mrowkowy.Q / (m.wylicz_droge());
                    }

//stary wzor niby z odparowaniem ;)
//                    w.feromon = (1 - algorytm_mrowkowy.Rho) * w.feromon + w.delta_tau;


                    if (algorytm_mrowkowy.system == 4 && m==najlepsza_mrowka_w_iter){
                        System.out.println("czydziala");
                        w.delta_tau += algorytm_mrowkowy.Q / (najlepsza_mrowka_w_iter.wylicz_droge());

                    }



                        //odparowanie


                    if(algorytm_mrowkowy.system==5) {

                        for(wierzcholek ww: najlepsza_mrowka.odwiedzone_wierzcholki){

                            int elitarne_mrowki=0;

                            for(mrowka mm: mrowki){
                                for(wierzcholek w2:mm.odwiedzone_wierzcholki){
                                    if(ww==w2){
                                        elitarne_mrowki++;
                                    }
                                }
                            }
//                            System.out.println(elitarne_mrowki);
                            ww.feromon = ww.feromon + ww.delta_tau + elitarne_mrowki*Q/(najlepsza_mrowka.wylicz_droge());



                        }

                        ArrayList<wierzcholek> pozostale_wierzcholki=new ArrayList<>(najlepsza_mrowka.wszystkie_wierzcholki);
                        for(wierzcholek wie:najlepsza_mrowka.odwiedzone_wierzcholki){
                        pozostale_wierzcholki.remove(wie);
                        }
                        for(wierzcholek wie:pozostale_wierzcholki){
                            w.feromon = (1 - algorytm_mrowkowy.Rho) * w.feromon;
                            w.feromon = w.feromon + w.delta_tau;

                        }




                    }

                    else {
                        w.feromon = (1 - algorytm_mrowkowy.Rho) * w.feromon;

                        w.feromon = w.feromon + w.delta_tau;
                    }

                    if(algorytm_mrowkowy.system == 4){
                        if(w.feromon<algorytm_mrowkowy.min)
                        w.feromon=algorytm_mrowkowy.min;
                        if(w.feromon>algorytm_mrowkowy.max)
                            w.feromon=algorytm_mrowkowy.max;
                    }

                    System.out.println(m + " " + w);





                }


//                m.dlugosc_trasy=0;

            }
            System.out.println(najlepsza_mrowka.rozwiazanie());




        }




        return new rozwiazanie(najlepsza_mrowka.rozwiazanie(),trafienia,pierwsze_trafienie,najlepsza_mrowka.plecak);


    }


    public void globalny_feromon(mrowka m)
    {


//        double updateAmount;
//        if (this.globalUpdateFactor > 0)
//        {
//            updateAmount = ant.evaluateGoalFunction() / this.globalUpdateFactor;
//        }
//        else
//        {
//            updateAmount = 0;
//        }
//
//        foreach (KnapsackItemNode node in ant.VisitedNodes)
//        {
//            node.Pheromone +=
//                    this.parameters.Alpha * updateAmount * node.InitialPheromone;
//        }
//
//




        //ostatnie

        double wartosc; 
        if (this.najlepsza_sciezka > 0)
        {
            wartosc = m.rozwiazanie() / this.najlepsza_sciezka;
        }
        else
        {
            wartosc = 0;
        }


//        for(wierzcholek w : m.wszystkie_wierzcholki){
//            w.feromon = (1-algorytm_mrowkowy.Alpha)*w.feromon+algorytm_mrowkowy.Alpha*1/
//        }


        for (wierzcholek w : m.odwiedzone_wierzcholki)
        {
//            System.out.println("Przed"+w.feromon);
//                        System.out.println("Przed globalna: "+w.przedmiot+" "+w.feromon);

            w.feromon +=algorytm_mrowkowy.Alpha * wartosc * algorytm_mrowkowy.poczatkowy_feromon;
//w.feromon=(1-algorytm_mrowkowy.Alpha)*w.feromon+algorytm_mrowkowy.Alpha/najlepsza_sciezka;

//            System.out.println("Po globalna: "+w.przedmiot+" "+w.feromon);











//            System.out.println("Po globalnej: "+w.feromon);

//            zakomentowane poprzednia wersja

//
//
//            w.feromon=(1 - algorytm_mrowkowy.Alpha ) * w.feromon + algorytm_mrowkowy.Alpha/this.najlepsza_sciezka;
//            System.out.println("Po globalnej: "+w.feromon);

        }
    }

    public void lokalny_feromon(mrowka m)
    {
        for (wierzcholek w : m.odwiedzone_wierzcholki)
        {
//            System.out.println(w.przedmiot);
//            System.out.println("Przed lokalna: "+w.przedmiot+" "+w.feromon);

            w.feromon = (1 - algorytm_mrowkowy.Rho) * (w.feromon) + algorytm_mrowkowy.Rho * algorytm_mrowkowy.poczatkowy_feromon;
//            System.out.println(((1 - algorytm_mrowkowy.Rho) * (w.feromon))+" "+algorytm_mrowkowy.Rho * algorytm_mrowkowy.poczatkowy_feromon);
//            System.out.println("Po lokalna: "+w.przedmiot+" "+w.feromon);
        }
    }


    public void Reset()
    {

            for (wierzcholek w : lista_wierzcholkow) {
                
                w.feromon = algorytm_mrowkowy.poczatkowy_feromon;
            }
    }







}



