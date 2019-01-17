import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class algorytm_mrowkowy {


    static public double poczatkowy_feromon = 0.001;
    static public double Rho = 0.4;
    static public double Beta = 5;
    static public double Alpha = 1;
    static public double q0 = 0.1;
    static public int ilosc_mrowek = 10;
    static public int system=0;
    //s.mrowiskowy ACS 0


    //s.mrowkowy f. staly 1
    //mrowkowy f. sredni 2
    //mrowkowy f. cykliczny 3
    //mrowkowy min/max 4
    //mrowkowy elitarny 5 ??

    static public double Q=10;
    
    
    
    ArrayList<wierzcholek> lista_wierzcholkow;
     mrowka[] mrowki;
     mrowka najlepsza_mrowka;
     problem_plecakowy pp;
     double najlepsza_sciezka = 0;


    algorytm_mrowkowy(problem_plecakowy pp) throws Exception {

        this.pp=pp;

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
        wykonaj();
else
wykonaj_mrowkowy();


System.out.println(lista_wierzcholkow);
//System.out.println("Wartosc max: "+mrowka.wynik_max);
//System.out.println("Wartosc srednia: "+ (mrowka.wynik_srednia/algorytm_mrowkowy.ilosc_mrowek));
//System.out.println(mrowka.iteracje);
    }


    void wykonaj() throws Exception {
        //todo: zrobic fermon staly,sredni i cykliczny

        double maksymalna_wartosc = Double.MIN_VALUE;
        double minimalna_wartosc = Double.MAX_VALUE;
        double srednia_wartosc = 0;
        double tmp;

        Random r=new Random();


        //cykle
        for(int i=0;i<10;i++) {
            srednia_wartosc=0;
            maksymalna_wartosc=0;
            minimalna_wartosc=0;
            for (mrowka m : mrowki) {


                m.reset();
                m.odwiedz_wierzcholek(lista_wierzcholkow.get(r.nextInt(lista_wierzcholkow.size())));

//            System.out.println("Pierwszy wylosowany wierzcholek "+m.odwiedzone_wierzcholki);

                m.run();

                tmp = m.rozwiazanie();

                srednia_wartosc += tmp;
                if (tmp < minimalna_wartosc) {
                    minimalna_wartosc = tmp;
                }
                if (tmp > maksymalna_wartosc) {
                    maksymalna_wartosc = tmp;
                    najlepsza_mrowka = m;
                }
                lokalny_feromon(m);
            }


            srednia_wartosc = srednia_wartosc / algorytm_mrowkowy.ilosc_mrowek;

            if (this.najlepsza_sciezka < maksymalna_wartosc) {
                this.najlepsza_sciezka = maksymalna_wartosc;
            }

            globalny_feromon(najlepsza_mrowka);

            System.out.println("Srednia wartosc z cyklu "+srednia_wartosc);
            System.out.println("Najlepsze rozw "+najlepsza_mrowka.rozwiazanie());

        }

    }





    void wykonaj_mrowkowy() throws Exception {
        //todo: zrobic fermon staly,sredni i cykliczny

        double maksymalna_wartosc = Double.MIN_VALUE;
        double minimalna_wartosc = Double.MAX_VALUE;
        double srednia_wartosc = 0;
        double tmp;

        Random r = new Random();
        for (int i = 0; i < lista_wierzcholkow.size(); i++) {
            for (mrowka m : mrowki) {



//            System.out.println("Pierwszy wylosowany wierzcholek "+m.odwiedzone_wierzcholki);

                if(m.run_mrowkowy()==true)
                {
                    for(wierzcholek w:m.odwiedzone_wierzcholki)
                    w.feromon = (1 - algorytm_mrowkowy.Rho) * w.feromon + w.delta_tau;
                    continue;
                }


                tmp = m.rozwiazanie();

                srednia_wartosc += tmp;
                if (tmp < minimalna_wartosc) {
                    minimalna_wartosc = tmp;
                }
                if (tmp > maksymalna_wartosc) {
                    maksymalna_wartosc = tmp;
                    najlepsza_mrowka = m;
                }
//            lokalny_feromon(m);


            }

            if (this.najlepsza_sciezka < maksymalna_wartosc) {
                this.najlepsza_sciezka = maksymalna_wartosc;
            }

//        globalny_feromon(najlepsza_mrowka);


        }
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



        double wartosc; 
        if (this.najlepsza_sciezka > 0)
        {
            wartosc = m.rozwiazanie() / this.najlepsza_sciezka;
        }
        else
        {
            wartosc = 0;
        }

        for (wierzcholek w : m.odwiedzone_wierzcholki)
        {
                        System.out.println("Przed globalna: "+w.feromon);

            w.feromon +=
                    algorytm_mrowkowy.Alpha * wartosc * algorytm_mrowkowy.poczatkowy_feromon;
            System.out.println("Po globalnej: "+w.feromon);

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
            System.out.println("Przed: "+w.feromon);

            w.feromon = (1 - algorytm_mrowkowy.Rho) * (w.feromon) + algorytm_mrowkowy.Rho * algorytm_mrowkowy.poczatkowy_feromon;
//            System.out.println(((1 - algorytm_mrowkowy.Rho) * (w.feromon))+" "+algorytm_mrowkowy.Rho * algorytm_mrowkowy.poczatkowy_feromon);
            System.out.println("Po: "+w.feromon);
        }
    }


    public void Reset()
    {

            for (wierzcholek w : lista_wierzcholkow) {
                
                w.feromon = algorytm_mrowkowy.poczatkowy_feromon;
            }
    }







}



