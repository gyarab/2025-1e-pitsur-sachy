package ProjektSachy;

import java.util.ArrayList;
import java.util.List;

public class Vez extends Figurka {

    public Vez(boolean jeBila) {
        this.jeBila = jeBila;
    }

    public boolean pohnulSe = false;

    @Override
    public List<Pozice> MozneTahy(Figurka[][] Sachovnice, int x, int y) {
        List<Pozice> tahy = new ArrayList<>();

        // nahoru
        for (int i = x - 1; i >= 0; i--) {
            if (Sachovnice[i][y] == null) {
                tahy.add(new Pozice(i, y));
            } else {
                if (Sachovnice[i][y].jeBila != this.jeBila) {
                    tahy.add(new Pozice(i, y));
                }
                break;
            }
        }

        // dolu
        for (int i = x + 1; i < 8; i++) {
            if (Sachovnice[i][y] == null) {
                tahy.add(new Pozice(i, y));
            } else {
                if (Sachovnice[i][y].jeBila != this.jeBila) {
                    tahy.add(new Pozice(i, y));
                }
                break;
            }
        }

        // vlevo
        for (int i = y - 1; i >= 0; i--) {
            if (Sachovnice[x][i] == null) {
                tahy.add(new Pozice(x, i));
            } else {
                if (Sachovnice[x][i].jeBila != this.jeBila) {
                    tahy.add(new Pozice(x, i));
                }
                break;
            }
        }

        // vpravo
        for (int i = y + 1; i < 8; i++) {
            if (Sachovnice[x][i] == null) {
                tahy.add(new Pozice(x, i));
            } else {
                if (Sachovnice[x][i].jeBila != this.jeBila) {
                    tahy.add(new Pozice(x, i));
                }
                break;
            }
        }

        return tahy;
    }
}
