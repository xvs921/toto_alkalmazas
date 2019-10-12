package tarolo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Fordulo {
    private int ev;
    private int het;
    private int forduloAHeten;
    private LocalDate datum;
    private List<Talalat> talalatLista = new ArrayList<>();
    private List<Eredmeny> eredmenyekLista = new ArrayList<>();

    public int getEv(){
        return ev;
    }

    public void setEv(int ev) {
        this.ev = ev;
    }

    public int getHet() {
        return het;
    }
    
    public void setHet(int het) {
        this.het = het;
    }

    public int getForduloAHeten() {
        return forduloAHeten;
    }

    public void setForduloAHeten(int forduloAHeten) {
        this.forduloAHeten = forduloAHeten;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
    
    public List<Talalat> getTalalatok(){
        return this.talalatLista;
    }
    
    public void setTalalatok(List<Talalat> talalatLista){
        this.talalatLista = talalatLista;
    }
    
    public List<Eredmeny> getEredmenyek(){
        return this.eredmenyekLista;
    }
    
    public void setEredmenyek(List<Eredmeny> eredmenyekLista){
        this.eredmenyekLista = eredmenyekLista;
    }
    
    public Fordulo(String sor) {
        String[] adatok = sor.split(";");
        this.ev = Integer.parseInt(adatok[0]);
        this.het = Integer.parseInt(adatok[1]);
        
        //fordulok szama a heten
        if (adatok[2].equals(""))
        {
            this.forduloAHeten = 1;
        }
        else
        {
            this.forduloAHeten = Integer.parseInt(adatok[2]);
        }
        
        //datum szamitasa
        if (adatok[3].equals(""))
        {
            int napok = this.het * 7;
            if (napok > 365)
            {
                napok -= 365;
                this.ev++;
            }
            else if (this.ev % 4 == 0 && napok > 366)
            {
                napok -= 366;
                this.ev++;
            }
            LocalDate szamitasDatum = LocalDate.ofYearDay(this.ev, napok);
            if (this.forduloAHeten==1) {
                this.datum = szamitasDatum.with(DayOfWeek.MONDAY);
            }
            else if (this.forduloAHeten==2) {
                this.datum = szamitasDatum.with(DayOfWeek.TUESDAY);
            }
            else if (this.forduloAHeten==3) {
                this.datum = szamitasDatum.with(DayOfWeek.WEDNESDAY);
            }
            else if (this.forduloAHeten==4) {
                this.datum = szamitasDatum.with(DayOfWeek.THURSDAY);
            }
            else if (this.forduloAHeten==5) {
                this.datum = szamitasDatum.with(DayOfWeek.FRIDAY);
            }
            else if (this.forduloAHeten==6) {
                this.datum = szamitasDatum.with(DayOfWeek.SATURDAY);
            }
            else if (this.forduloAHeten==7) {
                this.datum = szamitasDatum.with(DayOfWeek.SUNDAY);
            }
        }
        else
        {
            String[] datumok = adatok[3].replace(".", ",").split(",");
            this.datum = LocalDate.of(Integer.parseInt(datumok[0]), Integer.parseInt(datumok[1]), Integer.parseInt(datumok[2]));
        }
        
        //a talalat es hozza tartozo osszeg
        int szamitas = 4;//ev+het+fordulo+datum+talalatokszama
        int eltalalt = 14;
        while(szamitas<14)
        {
            String osszeg = adatok[szamitas + 1].replace(" ", "");
            int nyeremeny = Integer.valueOf(osszeg.substring(0, osszeg.length() - 2));//nyeremeny a Ft felirat nelkul
            this.talalatLista.add(new Talalat(eltalalt, Integer.parseInt(adatok[szamitas]), nyeremeny));
            szamitas += 2;
            eltalalt--;
        }

        //eredmeny szamitasa
        for (int i = 14; i < adatok.length; i++)
        {
            switch (adatok[i])
            {
                case "1": this.eredmenyekLista.add(Eredmeny.option1); break;
                case "2": this.eredmenyekLista.add(Eredmeny.option2); break;
                case "X": this.eredmenyekLista.add(Eredmeny.optionX); break;
            }
        }
    }
    public int getMaxNyeremeny()
    {
        int maxNyeremeny = 0;
        for (int i = 0; i < this.talalatLista.size(); i++)
        {
            if (maxNyeremeny < this.talalatLista.get(i).getNyeremeny())
            {
                maxNyeremeny = this.talalatLista.get(i).getNyeremeny();
            }
        }
        return maxNyeremeny;
    }  
}