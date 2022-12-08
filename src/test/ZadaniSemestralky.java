import logika.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ZadaniSemestralky {
    
    private Hra hra;
    
    @BeforeEach
    public void setUp(){
        hra = new Hra();
    }

    @AfterEach
    void tearDown() {
        hra = new Hra();
    }

    @Test
    void testPrubehuHry() {
            assertEquals("domeček", hra.getHerniPlan().getAktualniProstor().getNazev());
            hra.zpracujPrikaz("jdi les");
            assertEquals("les", hra.getHerniPlan().getAktualniProstor().getNazev());
            assertFalse(hra.konecHry());
            hra.zpracujPrikaz("konec");
            assertTrue(hra.konecHry());

    }


    // zde bude probihat test Hry, ktery bude slouzit jako zadani semestralni prace
    @Test
    void testHry() {

        /**
         * Mapa generovana pomoci online nastroje - inkarnate.com
         *
         * Mapa se nachazi v adresari souboru ve slozce dalsi soubory - nazev souboru mapa.jpeg
         *
         * ve slozce se take nachazi textovy soubor popisujici co jde v jakych prostorech najit za vychody a predmety
         *
         */
        //Uvítání - zapnutí hry
        assertEquals(hra.vratUvitani(), """
                Vítej v adventuře, kde je tvým cílem dojít do zamčené shované místnosti v čarodějově věži kde čaroděj shovává všechno ukradené zlato.
                Čeká tě těžký průchod a budeš muset cestou posbírat několik předmětů.
                 Napište 'nápověda', pokud si nevíte rady, jak hrát dál.\s
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: Město
                Věci v místnosti: MrtváKrysa Střepy Barel Lampa
                Východy: Hory Les HlubokýLes Hospoda Kostel
                Zamčené východy: DůmKováře Stodola
                Aktuální předměty v batohu: V batohu nemáš nic
                Kapacita batohu: 0/15""");

        //1. krok jdi kostel
        assertEquals(hra.zpracujPrikaz("jdi kostel"),
                """
                        Kostel -  Místo, kde jsme blíže bohu a můžeme zde najít
                        Počet životů 100/100
                        Momentálně se nacházíš v prostoru: Kostel
                        Věci v místnosti: Róba Žezlo Lavice
                        Východy: Město
                        Aktuální předměty v batohu: V batohu nemáš nic
                        Kapacita batohu: 0/15""");

        //2. krok prozkoumej kostel
        assertEquals(hra.zpracujPrikaz("prozkoumej kostel"),
                """
                        V místnosti Kostel jsi našel Klíč LektvarŽivota
                        Počet životů 100/100
                        Momentálně se nacházíš v prostoru: Kostel
                        Věci v místnosti: Róba Žezlo Lavice Klíč LektvarŽivota
                        Východy: Město
                        Aktuální předměty v batohu: V batohu nemáš nic
                        Kapacita batohu: 0/15""");

        //3. krok seber klic
        assertEquals(hra.zpracujPrikaz("seber klic"), """
                Sebral jsi Klíč
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: Kostel
                Věci v místnosti: Róba Žezlo Lavice LektvarŽivota
                Východy: Město
                Aktuální předměty v batohu: Klíč
                Kapacita batohu: 1/15""");

        //4.krok seber lektvarzivota
        assertEquals(hra.zpracujPrikaz("seber lektvarzivota"), """
                Sebral jsi LektvarŽivota
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: Kostel
                Věci v místnosti: Róba Žezlo Lavice
                Východy: Město
                Aktuální předměty v batohu: Klíč LektvarŽivota
                Kapacita batohu: 2/15""");

        //5. krok jdi mesto
        assertEquals(hra.zpracujPrikaz("jdi mesto"), """
                Město - Tady se děje všechno svaté i nesvaté
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: Město
                Věci v místnosti: MrtváKrysa Střepy Barel Lampa
                Východy: Hory Les HlubokýLes Hospoda Kostel
                Zamčené východy: DůmKováře Stodola
                Aktuální předměty v batohu: Klíč LektvarŽivota
                Kapacita batohu: 2/15""");

        //6.krok odemkni dumkovare
        assertEquals(hra.zpracujPrikaz("odemkni dumkovare"), """
                Odemkl jsi DůmKováře
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: Město
                Věci v místnosti: MrtváKrysa Střepy Barel Lampa
                Východy: Hory Les HlubokýLes Hospoda Kostel DůmKováře
                Zamčené východy: Stodola
                Aktuální předměty v batohu: LektvarŽivota
                Kapacita batohu: 1/15""");

        //7.krok jdi dumkovare
        assertEquals(hra.zpracujPrikaz("jdi dumkovare"), """
                DůmKováře - tady bydlí kovář
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: DůmKováře
                Věci v místnosti: Meč Stůl Nůž
                Východy: Město
                Aktuální předměty v batohu: LektvarŽivota
                Kapacita batohu: 1/15""");

        //8.krok seber mec
        assertEquals(hra.zpracujPrikaz("seber mec"), """
                Sebral jsi Meč
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: DůmKováře
                Věci v místnosti: Stůl Nůž
                Východy: Město
                Aktuální předměty v batohu: LektvarŽivota Meč
                Kapacita batohu: 2/15""");

        //9.krok jdi mesto
        assertEquals(hra.zpracujPrikaz("jdi mesto"), """
                Město - Tady se děje všechno svaté i nesvaté
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: Město
                Věci v místnosti: MrtváKrysa Střepy Barel Lampa
                Východy: Hory Les HlubokýLes Hospoda Kostel DůmKováře
                Zamčené východy: Stodola
                Aktuální předměty v batohu: LektvarŽivota Meč
                Kapacita batohu: 2/15""");

        //10. krok jdi hospoda
        assertEquals(hra.zpracujPrikaz("jdi hospoda"), """
                Hospoda - tady se pije
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: Hospoda
                Věci v místnosti: Půllitr Nůž Stůl Židle LahevAlkoholu
                Východy: Město
                Aktuální předměty v batohu: LektvarŽivota Meč
                Kapacita batohu: 2/15""");

        //11.krok seber LahevAlkoholu
        assertEquals(hra.zpracujPrikaz("seber LahevAlkoholu"), """
                Sebral jsi LahevAlkoholu
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: Hospoda
                Věci v místnosti: Půllitr Nůž Stůl Židle
                Východy: Město
                Aktuální předměty v batohu: LektvarŽivota Meč LahevAlkoholu
                Kapacita batohu: 3/15""");

        //12.krok jdi mesto
        assertEquals(hra.zpracujPrikaz("jdi mesto"),
                """
                        Město - Tady se děje všechno svaté i nesvaté
                        Počet životů 100/100
                        Momentálně se nacházíš v prostoru: Město
                        Věci v místnosti: MrtváKrysa Střepy Barel Lampa
                        Východy: Hory Les HlubokýLes Hospoda Kostel DůmKováře
                        Zamčené východy: Stodola
                        Aktuální předměty v batohu: LektvarŽivota Meč LahevAlkoholu
                        Kapacita batohu: 3/15""");

        //13.krok jdi hlubokyles
        assertEquals(hra.zpracujPrikaz("jdi hlubokyLes"),
                """
                        HlubokýLes - Při vstupu do Hlubokého lesa tě napadli vlci a ubrali ti 20 životů než jsi je stihl zahnat.
                        Počet životů 80/100
                        Momentálně se nacházíš v prostoru: HlubokýLes
                        Věci v místnosti: Klacek Kámen Strom
                        Východy: Město Hory Táborák Pustina
                        Aktuální předměty v batohu: LektvarŽivota Meč LahevAlkoholu
                        Kapacita batohu: 3/15""");

        //14. krok prozkoumej hlubokyles
        assertEquals(hra.zpracujPrikaz("prozkoumej hlubokyles"), """
                V místnosti HlubokýLes jsi našel Klíč
                Počet životů 80/100
                Momentálně se nacházíš v prostoru: HlubokýLes
                Věci v místnosti: Klacek Kámen Strom Klíč
                Východy: Město Hory Táborák Pustina
                Aktuální předměty v batohu: LektvarŽivota Meč LahevAlkoholu
                Kapacita batohu: 3/15""");

        //15. krok seber klíč
        assertEquals(hra.zpracujPrikaz("seber klíč"), """
                Sebral jsi Klíč
                Počet životů 80/100
                Momentálně se nacházíš v prostoru: HlubokýLes
                Věci v místnosti: Klacek Kámen Strom
                Východy: Město Hory Táborák Pustina
                Aktuální předměty v batohu: LektvarŽivota Meč LahevAlkoholu Klíč
                Kapacita batohu: 4/15""");

        //16.krok jdi pustina
        assertEquals(hra.zpracujPrikaz("jdi pustina"), """
                Pustina - tady krom suché hlíny nic nenajdeš, cestou jsi z vyčerpání ztratil 10 životů.
                Počet životů 70/100
                Momentálně se nacházíš v prostoru: Pustina
                Věci v místnosti: Tady nic není
                Východy: HlubokýLes Les Vesnice
                Zamčené východy: Stodola
                Aktuální předměty v batohu: LektvarŽivota Meč LahevAlkoholu Klíč
                Kapacita batohu: 4/15"""); //TODO pridat mistnost co se tam objevi presne jednou, po odemceni se smaze
        assertEquals(hra.zpracujPrikaz("seber lektvarzivota"),"");
        assertEquals(hra.zpracujPrikaz("seber lektvarzivota"),"");
        assertEquals(hra.zpracujPrikaz("seber lektvarzivota"),"");
        assertEquals(hra.zpracujPrikaz("seber lektvarzivota"),"");


    }
}
