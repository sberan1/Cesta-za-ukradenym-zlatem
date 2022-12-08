package logika;

import java.text.Normalizer;

public class PrikazOdemkni implements IPrikaz{

    private final String NAZEV = "odemkni";
    private HerniPlan plan;

    public PrikazOdemkni(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            //pokud chybi nazev mistnosti kterou chce nekdo odemknout
            return "Musite mit jasno v tom co chcete odemknout, co sem vubec lezete takhle nepripravenej sakra";
        }
        if (parametry.length > 1) {
            //pokud uzivatel zada vice mistnosti
            return "jezisi kriste a co z toho mam asi odemknout, vy jste jak z jara";
        }

        String nazevMistnosti = Normalizer.normalize(parametry[0], Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toLowerCase();
        for (var mistnost : plan.getAktualniProstor().zamceneProstoryList()){
            if(nazevMistnosti.equals(mistnost.getnNormalnizedNazev()) && plan.getBatuzek().odeberVec("klic") && mistnost.isViditelny()) {
                    mistnost.odemknoutMistnost();
                    return "Odemkl jsi " + mistnost.getNazev() + "\n" + plan.getAktualniProstor().dlouhyPopis();
            }
        }
        return "Hele, tak bud nemas klic, nebo tam odsud cesta nevede, takze se pakuj";
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
