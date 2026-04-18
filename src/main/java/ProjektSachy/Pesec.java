package ProjektSachy;

import java.util.ArrayList;
import java.util.List;

public class Pesec extends Figurka {

    public static Sachovnice sachovnice;

    public Pesec(boolean jeBila) {
        this.jeBila = jeBila;
    }

    @Override
    public List<Pozice> MozneTahy(Figurka[][] s, int x, int y) {
        List<Pozice> tahy = new ArrayList<>();

        int smer = jeBila ? -1 : 1;

        // 1 pole dopredu
        if (x + smer >= 0 && x + smer < 8) {
            if (s[x + smer][y] == null) {
                tahy.add(new Pozice(x + smer, y));

                // 2 pole dopredu (jen z prvni pozice)
                if ((jeBila && x == 6) || (!jeBila && x == 1)) {
                    if (s[x + 2 * smer][y] == null) {
                        tahy.add(new Pozice(x + 2 * smer, y));
                    }
                }
            }
        }

        // brani vlevo
        if (x + smer >= 0 && x + smer < 8 && y - 1 >= 0) {
            if (s[x + smer][y - 1] != null &&
                    s[x + smer][y - 1].jeBila != this.jeBila) {
                tahy.add(new Pozice(x + smer, y - 1));
            }
        }

        // brani vpravo
        if (x + smer >= 0 && x + smer < 8 && y + 1 < 8) {
            if (s[x + smer][y + 1] != null &&
                    s[x + smer][y + 1].jeBila != this.jeBila) {
                tahy.add(new Pozice(x + smer, y + 1));
            }
        }

        // en passant
        if (sachovnice != null && sachovnice.enPassantX != -1) {

            if (x + smer == sachovnice.enPassantX &&
                    Math.abs(y - sachovnice.enPassantY) == 1) {

                tahy.add(new Pozice(sachovnice.enPassantX, sachovnice.enPassantY));
            }
        }

        return tahy;
    }
}