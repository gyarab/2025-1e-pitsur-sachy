package ProjektSachy;

public class Sachovnice {

    public Figurka [][] policko = new Figurka[8][8];

    public boolean bilyHraji = true;

    public Sachovnice(){

        //spawn pesaku

        for(int i = 0; i < 8; i++){
            policko[1][i] = new Pesec(false);
            policko[7][i] = new Pesec(true);
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
    }
}
