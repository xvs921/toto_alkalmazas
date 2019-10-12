package toto_alkalmazas;

import java.util.Scanner;
import szolgaltatas.TotoSzolgaltatas;

public class Toto_alkalmazas {

    public static void main(String[] args) {
        
        TotoSzolgaltatas toto = new TotoSzolgaltatas("toto.csv");

        //legnagyobb nyeremeny
        System.out.print("A legnagyobb nyeremeny amit rogzitettek: "+toto.getMaxNyeremenyVolt() + " Ft\n");

        //eselyek kiszamolasa es kiirasa
        double osszes = toto.getEgyTippGyoz()+toto.getKettoTippGyoz()+toto.getXTippGyoz();
        double EgySzazalek=Math.round((toto.getEgyTippGyoz() / osszes * 100) * 100.0) / 100.0;
        double KettoSzazalek=Math.round((toto.getKettoTippGyoz() / osszes * 100) * 100.0) / 100.0;
        double XSzazalek=Math.round(100-(EgySzazalek+KettoSzazalek));
        System.out.print("Statisztika: #1 csapat nyert: " + EgySzazalek + "%, #2 csapat nyert: " + KettoSzazalek + "%, dontetlen: " + XSzazalek + "%\n");

        //felhasznalo jatszik
        Scanner sc = new Scanner(System.in);
        System.out.print("Kerem adjon meg egy datumot: ");
        String datum = sc.next();
        if (toto.HelyesDatumE(datum) == true)
        {
            String tippsor;
            do
            {
                System.out.print("Kerem adjon meg egy tippet:");
                tippsor = sc.next();
            }
            while(tippsor.length() != 14);
            toto.getEredmeny(datum, tippsor);
        }
        else
        {
            System.out.println("Helytelen datum!");
        }
    }
}