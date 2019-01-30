public class rozwiazanie {

    double najlepsze_rozwiazanie;
    int trafionych_rozwiazan;
    int pierwsze_trafinie;

    problem_plecakowy plecak;

    public rozwiazanie(double najlepsze_rozwiazanie, int trafionych_rozwiazan, int pierwsze_trafinie, problem_plecakowy plecak) {
        this.najlepsze_rozwiazanie = najlepsze_rozwiazanie;
        this.trafionych_rozwiazan = trafionych_rozwiazan;
        this.pierwsze_trafinie = pierwsze_trafinie;
        this.plecak = plecak;
    }

    @Override
    public String toString() {
        return "rozwiazanie{" +
                "najlepsze_rozwiazanie=" + najlepsze_rozwiazanie +
                ", trafionych_rozwiazan=" + trafionych_rozwiazan +
                ", pierwsze_trafinie=" + pierwsze_trafinie +
                ", plecak=" + plecak +
                '}';
    }
}
