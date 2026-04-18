package ProjektSachy;

public class Testtrida {
    public static void main(String[] args) {

        Sachovnice s = new Sachovnice();

        // test en passant
        s.provedTah(6,4,4,4);
        s.provedTah(1,0,2,0);
        s.provedTah(4,4,3,4);
        s.provedTah(1,5,3,5);

        boolean ok = s.provedTah(3,4,2,5);

        System.out.println("en passant: " + ok);
    }
}


