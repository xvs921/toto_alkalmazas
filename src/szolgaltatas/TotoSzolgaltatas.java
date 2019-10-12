package szolgaltatas;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import tarolo.Eredmeny;
import tarolo.Fordulo;

public class TotoSzolgaltatas
{
    private List<Fordulo> forduloLista = new ArrayList<>();
    List<Eredmeny> tippekLista = new ArrayList<>();
    
    public TotoSzolgaltatas(String fajlNev)
    {
        try
        {
            FileReader fr = new FileReader(fajlNev);
            BufferedReader br = new BufferedReader(fr);
            String sor = br.readLine();
            while (sor != null)
            {
                Fordulo f=new Fordulo(sor);
                this.forduloLista.add(f);
                sor=br.readLine();
            }
            fr.close();
            br.close();
        }
        catch (Exception e)
        {
            System.out.printf("Hiba " + e);
        }
    }
    //Melyik volt a legnagyobb nyeremény, amit valaha rögzítettek?
    public int getMaxNyeremenyVolt()
    {
        int maxNyeremeny = 0;
        for (int i = 0; i < this.forduloLista.size(); i++)
        {
            if (maxNyeremeny < this.forduloLista.get(i).getMaxNyeremeny())
            {
                maxNyeremeny = this.forduloLista.get(i).getMaxNyeremeny();
            }
        }
        return maxNyeremeny;
    }
    //Hogyan oszlik meg minden forduló 1/2/X eredménye?
    public int getEgyTippGyoz()
    {
        int gyozelem1 = 0;
        for (int i = 0; i < this.forduloLista.size(); i++)
        {
            for (int j = 0; j < this.forduloLista.get(i).getEredmenyek().size(); j++)
            {
                if (this.forduloLista.get(i).getEredmenyek().get(j).equals(Eredmeny.option1))
                {
                    gyozelem1++;
                }
            }
        }
        return gyozelem1;
    }
    public int getKettoTippGyoz()
    {
        int gyozelem2 = 0;
        for (int i = 0; i < this.forduloLista.size(); i++)
        {
            for (int j = 0; j < this.forduloLista.get(i).getEredmenyek().size(); j++)
            {
                if (this.forduloLista.get(i).getEredmenyek().get(j).equals(Eredmeny.option2))
                {
                    gyozelem2++;
                }
            }
        }
        return gyozelem2;
    }
    public int getXTippGyoz()
    {
        int gyozelemX = 0;
        for (int i = 0; i < this.forduloLista.size(); i++)
        {
            for (int j = 0; j < this.forduloLista.get(i).getEredmenyek().size(); j++)
            {
                if (this.forduloLista.get(i).getEredmenyek().get(j).equals(Eredmeny.optionX))
                {
                    gyozelemX++;
                }
            }
        }
        return gyozelemX;
    }
    //Adott dátum és egy adott tippsorozat (1/2/X listája) alapján hány találatot ért el, és mennyit nyert a fogadásával?
     public void getEredmeny(String datumSzamol, String tippsor)
    {
        //datum felbontas
        String[] datumok = datumSzamol.replace(".", ",").split(",");
        LocalDate datum = LocalDate.of(Integer.parseInt(datumok[0]), Integer.parseInt(datumok[1]), Integer.parseInt(datumok[2]));
        //tippek kezelese
        String[] tippek = tippsor.toUpperCase().split("");
        for (String tippek1 : tippek) {
            switch (tippek1) {
                case "1": tippekLista.add(Eredmeny.option1); break;
                case "2": tippekLista.add(Eredmeny.option2); break;
                case "X": tippekLista.add(Eredmeny.optionX); break;
            }
        }
        int talalatSzama = 0;
        int nyeremeny = 0;
        for (Fordulo forduloLista1 : this.forduloLista) {
            if (forduloLista1.getDatum().equals(datum)) {
                for (int j = 0; j < forduloLista1.getEredmenyek().size(); j++) {
                    if (forduloLista1.getEredmenyek().get(j) == tippekLista.get(j)) {
                        talalatSzama++;
                    }
                }
                if (talalatSzama > 9) {
                    for (int j = 0; j < forduloLista1.getTalalatok().size(); j++) {
                        if (forduloLista1.getTalalatok().get(j).getTalalatokSzama() == talalatSzama) {
                            nyeremeny = forduloLista1.getTalalatok().get(j).getNyeremeny();
                        }
                    }
                    System.out.printf("Eredmény: talalat: "+talalatSzama+", nyeremeny: "+nyeremeny+" Ft!");
                } else {
                    System.out.println("Nem nyertel!");
                }
            }
        }
    }
    //datumvizsgalat, volt e jateknap
    public boolean HelyesDatumE(String datumSzamol)
    {
        String[] datumok = datumSzamol.replace(".", ",").split(",");
        LocalDate datum = LocalDate.of(Integer.parseInt(datumok[0]), Integer.parseInt(datumok[1]), Integer.parseInt(datumok[2]));
        boolean jateknap=false;
        for (Fordulo forduloLista1 : this.forduloLista) {
            if (forduloLista1.getDatum().equals(datum)) {
                jateknap = true;
            }
        }
        return jateknap;
    }
}
