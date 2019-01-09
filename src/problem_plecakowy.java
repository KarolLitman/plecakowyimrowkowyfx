import java.util.ArrayList;
import java.util.List;

class problem_plecakowy{



    public List<przedmiot> wszystkie_przedmioty = new ArrayList<>();
    public List<przedmiot> przedmioty_w_plecaku = new ArrayList<>();
    double pozostala_masa=50;
    double pierwotna_masa=50;

    public String toString(){
        return przedmioty_w_plecaku+"";
    }
double czy_miejsce_wplecaku() {
	pozostala_masa=pierwotna_masa;
	for(int i = 0;i<=przedmioty_w_plecaku.size();i++) {
		pozostala_masa-=przedmioty_w_plecaku.get(i).getMasa();
	}
	return pozostala_masa;
}
void dodaj_przedmiot(String nazwa, double masa, double cena){
    przedmioty_w_plecaku.add(new przedmiot(nazwa, masa, cena));
}

void dodaj_przedmiot(przedmiot p){
    przedmioty_w_plecaku.add(p);
}




    public Double policz_wartosc_plecaka()
    {
        Double tmp=0.0;
        for(przedmiot p : przedmioty_w_plecaku)
        {
            tmp = tmp + p.getCena();
        }
        return tmp;
    }

    public boolean czy_wystarczajaco_miejsca(przedmiot p)
    {
        return czy_miejsce_wplecaku() >= p.getMasa();
    }







}
