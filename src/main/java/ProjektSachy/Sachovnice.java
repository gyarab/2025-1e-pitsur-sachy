package ProjektSachy;

import java.util.List;

public class Sachovnice {

    public Figurka [][] policko = new Figurka[8][8];

    public boolean bilyHraji = true;

    public int enPassantX = -1;
    public int enPassantY = -1;

    public Sachovnice(){


        //spawn pesaku
        for(int i = 0; i < 8; i++){
            policko[1][i] = new Pesec(false);
            policko[6][i] = new Pesec(true);
        }

        //spawn vezi
        policko[0][0] = new Vez(false);
        policko[0][7] = new Vez(false);
        policko[7][0] = new Vez(true);
        policko[7][7] = new Vez(true);

        //spawn konu
        policko[0][1] = new Kun(false);
        policko[0][6] = new Kun(false);
        policko[7][1] = new Kun(true);
        policko[7][6] = new Kun(true);

        //spawn strelcu
        policko[0][2] = new Strelec(false);
        policko[0][5] = new Strelec(false);
        policko[7][2] = new Strelec(true);
        policko[7][5] = new Strelec(true);

        //spawn dam
        policko[0][3] = new Dama(false);
        policko[7][3] = new Dama(true);

        //spawn kralu
        policko[0][4] = new Kral(false);
        policko[7][4] = new Kral(true);

        Pesec.sachovnice = this;
    }

   public boolean provedTah(int x1, int y1, int x2, int y2) {
    Figurka f = policko[x1][y1];

    // neni tam figurka
    if (f == null) return false;

    // spatny hrac na tahu
    if (f.jeBila != bilyHraji) return false;

    // vezmi mozne tahy
    var tahy = f.MozneTahy(policko, x1, y1);

    // zkontroluj jestli tam muze jit
    for (Pozice p : tahy) {
        if (p.x == x2 && p.y == y2) {

            // ulozime co je na cilovem poli
            Figurka cil = policko[x2][y2];

            // provedeni tahu nanecisto
            policko[x2][y2] = f;
            policko[x1][y1] = null;

            // kontrola jestli jsme si nedali sach
            boolean sach = jeSach(f.jeBila);

            // vratime tah zpet
            policko[x1][y1] = f;
            policko[x2][y2] = cil;

            // pokud je sach tah je neplatny
            if (sach) return false;

            // provedeni tahu natvrdo
            policko[x2][y2] = f;
            policko[x1][y1] = null;

            // en passant sebrani
            if (f instanceof Pesec) {
                if (y1 != y2 && cil == null) {
                    policko[x1][y2] = null;
                }
            }

            // reset en passant
            enPassantX = -1;
            enPassantY = -1;

            // nastaveni ze se figurka pohnula
            if (f instanceof Kral) {
                ((Kral) f).pohnulSe = true;
            }

            if (f instanceof Vez) {
                ((Vez) f).pohnulSe = true;
            }

            // rosada posun veze
            if (f instanceof Kral) {

                // kratka rosada
                if (y2 == y1 + 2) {
                    Figurka vez = policko[x1][7];
                    policko[x1][5] = vez;
                    policko[x1][7] = null;
                }

                // dlouha rosada
                if (y2 == y1 - 2) {
                    Figurka vez = policko[x1][0];
                    policko[x1][3] = vez;
                    policko[x1][0] = null;
                }
            }

            // nastaveni en passant
            if (f instanceof Pesec) {
                if (Math.abs(x2 - x1) == 2) {
                    enPassantX = (x1 + x2) / 2;
                    enPassantY = y1;
                }
            }

            // promotion pesce
            if (f instanceof Pesec) {
                if ((f.jeBila && x2 == 0) || (!f.jeBila && x2 == 7)) {
                    policko[x2][y2] = new Dama(f.jeBila);
                }
            }

            // zmena hrace
            bilyHraji = !bilyHraji;

            return true;
        }
    }

    return false;
}
    public boolean jeSach(boolean bila) {

        int kralX = -1;
        int kralY = -1;

        // najdi krále
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Figurka f = policko[i][j];

                if (f instanceof Kral && f.jeBila == bila) {
                    kralX = i;
                    kralY = j;
                }
            }
        }

        // projdi všechny nepřátelské figurky
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Figurka f = policko[i][j];

                if (f != null && f.jeBila != bila) {

                    var tahy = f.MozneTahy(policko, i, j);

                    for (Pozice p : tahy) {
                        if (p.x == kralX && p.y == kralY) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean maNekdoTah(boolean bila) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                Figurka f = policko[i][j];

                if (f != null && f.jeBila == bila) {

                    var tahy = f.MozneTahy(policko, i, j);

                    for (Pozice p : tahy) {

                        Figurka cil = policko[p.x][p.y];

                        policko[p.x][p.y] = f;
                        policko[i][j] = null;

                        boolean sach = jeSach(bila);

                        policko[i][j] = f;
                        policko[p.x][p.y] = cil;

                        if (!sach) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean jeMat(boolean bila) {
        return jeSach(bila) && !maNekdoTah(bila);
    }

    public boolean jePat(boolean bila) {
        return !jeSach(bila) && !maNekdoTah(bila);
    }
}
