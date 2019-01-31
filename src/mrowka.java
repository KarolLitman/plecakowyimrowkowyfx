import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class mrowka {


//    int Nmax; //liczba maksymalnych krokow jakie mrowka bedzie mogla wykonac
static public double wynik_max = 0.0;
static public double wynik_srednia = 0.0;
static public int iteracje = 0;

 boolean czy_mrowka_zyje=true;
 double dlugosc_trasy=0.0;


    public ArrayList<wierzcholek> odwiedzone_wierzcholki;
    public ArrayList<wierzcholek> wszystkie_wierzcholki;
    wierzcholek obecny;
    problem_plecakowy plecak;


    mrowka(ArrayList<wierzcholek> wszystkie_wierzcholki){
        this.wszystkie_wierzcholki=wszystkie_wierzcholki;
        this.odwiedzone_wierzcholki=new ArrayList<>();
//        this.plecak=plecak;
        this.plecak=new problem_plecakowy();
    }







    public void odwiedz_wierzcholek(wierzcholek w) throws Exception {
        odwiedzone_wierzcholki.add(w);


        if (plecak.czy_wystarczajaco_miejsca(w.przedmiot)) //sprawdzenie bo pierwszy wierzcholek jest losowany...
        {
//            System.out.println("przed odwiedzeniem "+plecak.pozostala_masa);




            plecak.pozostala_masa-=w.przedmiot.getMasa();
            plecak.dodaj_przedmiot(w.przedmiot);
//            System.out.println("po odwiedzeniu "+plecak.pozostala_masa);


        }
        else
        {
//            System.out.println("czemu");

//            throw new Exception("Brak miejca w plecaku");
        }
        obecny = w;
    }





    public wierzcholek wybierz_nastepny_wierzcholek()
    {
        Random random=new Random();

        List<wierzcholek> dostepne_wierzcholki = new ArrayList<>();

//        System.out.println("wejscie do funkcji wybierz");

        for (wierzcholek w : wszystkie_wierzcholki)
        {

            if (!odwiedzone_wierzcholki.contains(w))
            {
                if (plecak.czy_wystarczajaco_miejsca(w.przedmiot))
                {
//                    System.out.println("poz masa"+plecak.pozostala_masa);
//                    System.out.println("czy_wyst "+w.przedmiot.masa);

                    dostepne_wierzcholki.add(w);
                }
            }
        }


        if ((random.nextDouble() > algorytm_mrowkowy.q0) || (algorytm_mrowkowy.system>0))
        {

            int count = dostepne_wierzcholki.size();
            double totalAtr = 0;
            double[] atrMap = new double[count];


            wierzcholek tmpNode = null;
            for (int i = 0; i < count; i++)
            {
                tmpNode = dostepne_wierzcholki.get(i);

                if(algorytm_mrowkowy.system==0)
                totalAtr += tmpNode.oblicz_atrakcyjnosc(tmpNode);
                else
                totalAtr += tmpNode.oblicz_atrakcyjnosc_sysmrowkowy(tmpNode);

                atrMap[i] = totalAtr;
            }

            double rand = random.nextDouble() * totalAtr;
//            for(int i =0; i<count; i++){
//                System.out.println("atr "+atrMap[i]+" "+ dostepne_wierzcholki.get(i));
//            }
            for (int i = 0; i < count; i++)
            {
                if (rand < atrMap[i])
                {

                    return dostepne_wierzcholki.get(i);
                }
            }
            return null;
        }
        else
        {
            if (dostepne_wierzcholki.size() == 0) {
                return null;
            }
            wierzcholek tmpNode = dostepne_wierzcholki.get(0);
            double tmp, tmpMax = Double.MIN_VALUE;
            for(wierzcholek w : dostepne_wierzcholki)
            {
                tmp = w.oblicz_atrakcyjnosc(w);
                if(tmp > tmpMax)
                {
                    tmpMax = tmp;
                    tmpNode = w;
                }
            }
            return tmpNode;
        }
    }




    public double rozwiazanie()
    {

        double value = 0;


            for(przedmiot p : plecak.przedmioty_w_plecaku)
            {
                value += p.getCena();
            }

        return value;
    }




    public void run() throws Exception {
        wierzcholek w;
        while ((w = wybierz_nastepny_wierzcholek()) != null)
        {
//            System.out.println("Nastepne "+w);
            this.odwiedz_wierzcholek(w);


        }

        System.out.println(plecak.przedmioty_w_plecaku);


//        Collections.sort(this.wszystkie_wierzcholki);
//        System.out.println(this.wszystkie_wierzcholki);


        double rozw=rozwiazanie();
//System.out.println(rozw);
if (rozw>mrowka.wynik_max){
    mrowka.wynik_max=rozw;
}
    mrowka.wynik_srednia+=rozw;
        mrowka.iteracje++;


        //System.out.println(plecak);
    }

    public boolean run_mrowkowy() throws Exception {
        wierzcholek w;
        while ((w = wybierz_nastepny_wierzcholek()) != null)
        {
//            System.out.println("Nastepne "+w);
            this.odwiedz_wierzcholek(w);

            if(algorytm_mrowkowy.system==1){
                w.delta_tau+=algorytm_mrowkowy.Q;
                System.out.println(this+" "+w);
                return true;}
            else if (algorytm_mrowkowy.system==2){
                w.delta_tau+=algorytm_mrowkowy.Q/w.odleglosc();
                System.out.println(this+" "+w);

                return true;}

                return false;
        }

        System.out.println(plecak.przedmioty_w_plecaku);
//      System.out.println(plecak.wszystkie_przedmioty);


        double rozw=rozwiazanie();
        System.out.println(rozw);
        if (rozw>mrowka.wynik_max){
            mrowka.wynik_max=rozw;
        }
        mrowka.wynik_srednia+=rozw;
        mrowka.iteracje++;


        return true;
//        System.out.println(plecak);
    }

    double wylicz_droge(){
        double suma=0;
        for(wierzcholek w:odwiedzone_wierzcholki){
            suma+=w.odleglosc();

        }
        return suma;
    }

    public void reset()
    {

        for(wierzcholek w:wszystkie_wierzcholki){
            w.delta_tau=0.0;
        }
       odwiedzone_wierzcholki.clear();
       plecak.przedmioty_w_plecaku.clear();

       odwiedzone_wierzcholki.removeAll(odwiedzone_wierzcholki);
       plecak.przedmioty_w_plecaku.removeAll(plecak.przedmioty_w_plecaku);

       czy_mrowka_zyje=true;
       dlugosc_trasy=0.0;


    }





}
