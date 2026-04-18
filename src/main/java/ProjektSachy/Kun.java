package ProjektSachy;

import java.util.ArrayList;
import java.util.List;

public class Kun extends Figurka {

    public Kun(boolean jeBila) {
        this.jeBila = jeBila;
    }

    @Override
    public List<Pozice> MozneTahy(Figurka[][] s, int x, int y) {
        List<Pozice> tahy = new ArrayList<>();

        int[][] pohyby = {
                {2,1}, {2,-1}, {-2,1}, {-2,-1},
                {1,2}, {1,-2}, {-1,2}, {-1,-2}
        };

        for (int[] p : pohyby) {
            int nx = x + p[0];
            int ny = y + p[1];

            // kontrola jestli je na desce
            if (nx >= 0 && nx < 8 && ny >= 0 && ny < 8) {

                // prazdne pole nebo nepritel
                if (s[nx][ny] == null || s[nx][ny].jeBila != this.jeBila) {
                    tahy.add(new Pozice(nx, ny));
                }
            }
        }

        return tahy;
    }
}
