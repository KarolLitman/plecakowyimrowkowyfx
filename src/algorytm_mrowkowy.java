import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings({ "unused" })
public class algorytm_mrowkowy {


    static public double poczatkowy_feromon = 0.01;
    static public double Rho = 0.10;//wyparowywanie
    static public double Beta = 1;//Wspó³czynni podró¿y
    static public double Alpha = 1;//Wspó³czynnik eksplatacji najlepszego ¿ród³a
    static public double Q0 = 0.5;//slad feromonowy
    static public int ilosc_mrowek = 500;
	public static double q0=0.20;
    
    
    
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


        wykonaj();
        feromonsta³y();

    }
   

    void wykonaj() throws Exception {
        //todo: zrobic fermon staly,sredni i cykliczny

        double maksymalna_wartosc = Double.MIN_VALUE;
        double minimalna_wartosc = Double.MAX_VALUE;
        double srednia_wartosc = 0;
        double tmp;

        Random r=new Random();

        for (mrowka m : mrowki)
        {


            m.reset();
            m.odwiedz_wierzcholek(lista_wierzcholkow.get(r.nextInt(lista_wierzcholkow.size())));

//            System.out.println("Pierwszy wylosowany wierzcholek "+m.odwiedzone_wierzcholki);

            m.run();

            tmp = m.rozwiazanie();

            srednia_wartosc += tmp;
            if (tmp < minimalna_wartosc)
            {
                minimalna_wartosc = tmp;
            }
            if (tmp > maksymalna_wartosc)
            {
                maksymalna_wartosc = tmp;
                najlepsza_mrowka = m;
            }
            lokalny_feromon(m);
        }
        srednia_wartosc = srednia_wartosc / (double)algorytm_mrowkowy.ilosc_mrowek;

        if(this.najlepsza_sciezka < maksymalna_wartosc)
        {
            this.najlepsza_sciezka = maksymalna_wartosc;
        }

        globalny_feromon(najlepsza_mrowka);



    }
 

    public void globalny_feromon(mrowka m)
    {
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
            w.feromon +=
                    algorytm_mrowkowy.Alpha * wartosc * algorytm_mrowkowy.poczatkowy_feromon;
        }
    }

    public void lokalny_feromon(mrowka m)
    {
        for (wierzcholek w : m.odwiedzone_wierzcholki)
        {
            w.feromon = (1 - algorytm_mrowkowy.Rho) * (w.feromon) + algorytm_mrowkowy.Rho * algorytm_mrowkowy.poczatkowy_feromon;
        }
    }
    public Boolean czy_jest_jakas_zywa_mrowka() {
    	boolean ta = false;
    	        
    	for (mrowka m : mrowki)
        { 
    		for(wierzcholek w : m.wszystkie_wierzcholki) {
    		if(m.plecak.czy_wystarczajaco_miejsca(w.przedmiot)) {
    			ta= true;
    		}
        }
    		}
		return ta;
    	 
    }
    public Boolean czy_jest_mrowka_zywa(mrowka m) {
    	boolean ta = false;
    	        
    	
    		for(wierzcholek w : m.wszystkie_wierzcholki) {
    		if(m.plecak.czy_wystarczajaco_miejsca(w.przedmiot)) {
    			ta= true;
    		}
        }
    		
		return ta;
    	 
    }

    public void Reset()
    {

            for (wierzcholek w : lista_wierzcholkow) {
                
                w.feromon = algorytm_mrowkowy.poczatkowy_feromon;
            }
    }

    void feromonsta³y() throws Exception {
    	double maksymalna_wartosc = Double.MIN_VALUE;
        double minimalna_wartosc = Double.MAX_VALUE;
        double srednia_wartosc = 0;
        double tmp;
        ArrayList<wierzcholek> odwiedzinki=new ArrayList<>();
        Random r=new Random();
        for (mrowka m : mrowki)
        {
        	
            m.odwiedz_wierzcholek(lista_wierzcholkow.get(r.nextInt(lista_wierzcholkow.size())));//
           odwiedzinki.add(m.obecny);
        }
        int i=0;
        for (mrowka m : mrowki)
        { 
        	wierzcholek.oblicz_atrakcyjnosc_staly_odwiedziny_mrowki(odwiedzinki.get(i));
        	i++;
        	
        }
        for(wierzcholek w: lista_wierzcholkow) {
        	wierzcholek.oblicz_atrakcyjnosc_staly(w);
        }
        while(czy_jest_jakas_zywa_mrowka()) {
        	for (mrowka m : mrowki)
            {odwiedzinki.clear();
        		if(czy_jest_mrowka_zywa(m)) {

        		m.wybierz_nastepny_wierzcholek();
        		odwiedzinki.add(m.obecny);
        		m.plecak.dodaj_przedmiot(m.obecny.przedmiot);
        		i=0;
                for (mrowka t : mrowki)
                { i++;
                if(odwiedzinki.size()>i)
                	wierzcholek.oblicz_atrakcyjnosc_staly_odwiedziny_mrowki(odwiedzinki.get(i));
                	
                	
                }
                for(wierzcholek w: lista_wierzcholkow) {
                	wierzcholek.oblicz_atrakcyjnosc_staly(w);
                }
            }
        }i=0;
        for (mrowka m : mrowki)
        {
        System.out.println("Mrówka "+i+" ma plecak o wartoœci"+m.plecak.policz_wartosc_plecaka()+""+m.plecak.przedmioty_w_plecaku);i++;
        }
        
}
	} 

}