package ProjektSachy;

import java.util.ArrayList;
import java.util.List;

public class Kral extends Figurka {

    public Kral(boolean jeBila) {
        this.jeBila = jeBila;
    }

    public boolean pohnulSe = false;

    @Override
    public List<Pozice> MozneTahy(Figurka[][] s, int x, int y) {
        List<Pozice> tahy = new ArrayList<>();

        // normalni pohyb krale
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {

                if (dx == 0 && dy == 0) continue;

                int nx = x + dx;
                int ny = y + dy;

                if (nx >= 0 && nx < 8 && ny >= 0 && ny < 8) {
                    if (s[nx][ny] == null || s[nx][ny].jeBila != this.jeBila) {
                        tahy.add(new Pozice(nx, ny));
                    }
                }
            }
        }

        //  ROSADA
        if (!this.pohnulSe) {

            // kratka rosada (vpravo)
            if (y + 3 < 8) {
                Figurka f1 = s[x][y + 1];
                Figurka f2 = s[x][y + 2];
                Figurka f3 = s[x][y + 3];

                if (f1 == null && f2 == null && f3 instanceof Vez) {
                    Vez vez = (Vez) f3;

                    if (!vez.pohnulSe && vez.jeBila == this.jeBila) {
                        tahy.add(new Pozice(x, y + 2));
                    }
                }
            }

            // dlouha rosada (vlevo)
            if (y - 4 >= 0) {
                Figurka f1 = s[x][y - 1];
                Figurka f2 = s[x][y - 2];
                Figurka f3 = s[x][y - 3];
                Figurka f4 = s[x][y - 4];

                if (f1 == null && f2 == null && f3 == null && f4 instanceof Vez) {
                    Vez vez = (Vez) f4;

                    if (!vez.pohnulSe && vez.jeBila == this.jeBila) {
                        tahy.add(new Pozice(x, y - 2));
                    }
                }
            }
        }

        return tahy;
    }
}