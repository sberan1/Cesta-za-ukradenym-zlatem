package logika;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PridaniVeciDoInvenatare implements INavrat{
    private List<Vec> veci;
    private Batoh batuzek;

    public PridaniVeciDoInvenatare(Batoh batuzek, Vec... vec) {
        veci = new ArrayList<>();
        this.batuzek = batuzek;
        veci.addAll(Arrays.asList(vec));
    }

    /**
     *
     */
    @Override
    public void navratovaHodnota() {
        for (var item : veci) {
            item.pridatVideniVeci();
            batuzek.vlozVec(item);
        }
    }
}
