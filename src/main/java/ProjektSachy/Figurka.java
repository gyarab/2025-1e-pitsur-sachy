package ProjektSachy;

import java.util.List;

public abstract class Figurka {
    public boolean jeBila;

    public abstract List<Pozice> MozneTahy(Figurka[][] sachovnice, int x, int y);

}
