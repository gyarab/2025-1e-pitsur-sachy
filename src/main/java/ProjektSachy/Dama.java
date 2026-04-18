package ProjektSachy;

import java.util.ArrayList;
import java.util.List;

public class Dama extends Figurka {

    public Dama(boolean jeBila) {
        this.jeBila = jeBila;
    }

    @Override
    public List<Pozice> MozneTahy(Figurka[][] s, int x, int y) {
        List<Pozice> tahy = new ArrayList<>();

        int[] dx = {1, -1, 0, 0, 1, 1, -1, -1};
        int[] dy = {0, 0, 1, -1, 1, -1, 1, -1};

        for (int d = 0; d < 8; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            while (nx >= 0 && nx < 8 && ny >= 0 && ny < 8) {

                if (s[nx][ny] == null) {
                    tahy.add(new Pozice(nx, ny));
                } else {
                    if (s[nx][ny].jeBila != this.jeBila) {
                        tahy.add(new Pozice(nx, ny));
                    }
                    break;
                }

                nx += dx[d];
                ny += dy[d];
            }
        }

        return tahy;
    }
}
