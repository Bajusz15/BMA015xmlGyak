package hu.domparse.bma015;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Scanner;

public class DomModifyBMA015 {

    public void ModifyTeam(Document doc) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Adja meg a modositandó csapat id-jét");
        String id = scan.nextLine();
        try {
            Element csapat = doc.getElementById(id);
            Node liga = csapat.getElementsByTagName("liga").item(0);
            Node nev = csapat.getElementsByTagName("nev").item(0);
            Node varos = csapat.getElementsByTagName("varos").item(0);
            System.out.println("Írja be az új ligát");
            String l = scan.nextLine();
            System.out.println("Írja be az új nevet");
            String n = scan.nextLine();
            System.out.println("Írja be az új várost");
            String v = scan.nextLine();

            liga.setTextContent(l);
            nev.setTextContent(n);
            varos.setTextContent(v);

            System.out.println("Módosítás sikeres");
            System.out.println("csapat azonosító: " + csapat.getAttribute("id"));
            System.out.println("bajnokság / liga: " + csapat.getElementsByTagName("liga").item(0).getTextContent());
            System.out.println("Csapat név: " + csapat.getElementsByTagName("nev").item(0).getTextContent());
            System.out.println("város: " + csapat.getElementsByTagName("varos").item(0).getTextContent());
        } catch (Exception exception) {
            System.out.println("nem sikerült a módosítás");
        }
    }

    public void ModifyPlayer(Document doc) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Adja meg a modositandó játékos id-jét");
        String id = scan.nextLine();
        try {
            Element jatekos = doc.getElementById(id);
            Node knev = jatekos.getElementsByTagName("keresztnev").item(0);
            Node vnev = jatekos.getElementsByTagName("vezeteknev").item(0);
            Node szdatum = jatekos.getElementsByTagName("szuletesi_datum").item(0);
            System.out.println("Írja be az új keresztnevet");
            String k = scan.nextLine();
            System.out.println("Írja be az új vezetéknevet");
            String v = scan.nextLine();
            System.out.println("Írja be az új születési dátumot");
            String sz = scan.nextLine();
            knev.setTextContent(k);
            vnev.setTextContent(v);
            szdatum.setTextContent(sz);
            System.out.println("Módosítás sikeres");
            System.out.println("játékos azonosító: " + jatekos.getAttribute("id"));
            System.out.println("keresztnév: " + jatekos.getElementsByTagName("keresztnev").item(0).getTextContent());
            System.out.println("vezetéknév: " + jatekos.getElementsByTagName("vezeteknev").item(0).getTextContent());
            System.out.println("születési dátum: " + jatekos.getElementsByTagName("szuletesi_datum").item(0).getTextContent());
        } catch (Exception exception) {
            System.out.println("nem sikerült a módosítás");
        }
    }
    //Módosítja a játékos mérkőzést
    public void ModifyGame(Document doc) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Adja meg a modositandó mérkőzés id-jét");
        String id = scan.nextLine();
        try {
            Element merkozes = doc.getElementById(id);
            Node liga = merkozes.getElementsByTagName("liga").item(0);
            Element eredmeny = (Element) merkozes.getElementsByTagName("eredmeny").item(0);
            System.out.println("Írja be az új hazai csapat ID-jét");
            String hazaiID = scan.nextLine();
            System.out.println("Írja be az új vendég csapat ID-jét");
            String vendegID = scan.nextLine();
            System.out.println("Írja be az új eredményt hazai részről");
            String hEredmeny = scan.nextLine();
            System.out.println("Írja be az új eredményt vendég részről");
            String vEredmeny = scan.nextLine();
            System.out.println("Írja be az új ligát");
            String l = scan.next();

            liga.setTextContent(l);
            merkozes.setAttribute("hazai_csapat", hazaiID);
            merkozes.setAttribute("vendeg_csapat", vendegID);
            eredmeny.getElementsByTagName("hazai").item(0).setTextContent(hEredmeny);
            eredmeny.getElementsByTagName("vendeg").item(0).setTextContent(vEredmeny);

            System.out.println("Módosítás sikeres");
            System.out.println("mérkőzés azonosító: " + id);
            System.out.println("bajnokság / liga: " + liga.getTextContent());
            //ugye itt a csapat neveket nem tartalmazza a merkozes, csak az attributum utal a csapatra egy id-vel,
            // ezért a nevüket le kell kérdezni a megfelelő csapat element-ből
            System.out.println("hazai csapat: " +   getTagValueByID(doc, merkozes.getAttribute("hazai_csapat"), "nev"));
            System.out.println("vendég csapat: " +   getTagValueByID(doc, merkozes.getAttribute("vendeg_csapat"), "nev"));
//            Element eredmeny = (Element) merkozes.getElementsByTagName("eredmeny").item(0);
            System.out.println("hazai eredmény: " +eredmeny.getElementsByTagName("hazai").item(0).getTextContent());
            System.out.println("vendég eredmény: " + eredmeny.getElementsByTagName("vendeg").item(0).getTextContent());

        } catch (Exception exception) {
            System.out.println("nem sikerült a módosítás");
        }

    }

    //Módosítja a játékos statisztikát
    public void ModifyPlayerStatistic(Document doc) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Adja meg a modositandó játékos statisztika játékos id-jét");
        String jatekosID = scan.nextLine();
        System.out.println("Adja meg a modositandó játékos statisztika meccs id-jét");
        String meccsID = scan.nextLine();
        NodeList statistics = doc.getElementsByTagName("jatekos_statisztika");
        try {
            for (int i = 0; i < statistics.getLength(); i++) {
                Node stat = statistics.item(i);
                if (stat.getNodeType() == Node.ELEMENT_NODE) {
                    Element statElement = (Element) stat;
                    if (statElement.getAttribute("jatekos").equals(jatekosID) && statElement.getAttribute("meccs_id").equals(meccsID)){
                        System.out.println("Írja be az új ligát");
                        String l = scan.nextLine();
                        System.out.println("Írja be az szerzett gólokat");
                        String gol = scan.nextLine();
                        System.out.println("Írja be az új játszott perceket");
                        String perc = scan.nextLine();
                        System.out.println("Írja be az új mez számot");
                        String mez = scan.nextLine();
                        statElement.getElementsByTagName("liga").item(0).setTextContent(l);
                        statElement.getElementsByTagName("szerzett_gol").item(0).setTextContent(gol);
                        statElement.getElementsByTagName("jatszott_perc").item(0).setTextContent(perc);
                        statElement.getElementsByTagName("mez_szam").item(0).setTextContent(mez);
                        System.out.println("Módosítás sikeres\n");

                        System.out.println("liga: " + statElement.getElementsByTagName("liga").item(0).getTextContent());
                        System.out.println("szerzett_gol: " + statElement.getElementsByTagName("szerzett_gol").item(0).getTextContent());
                        System.out.println("jatszott_perc: " + statElement.getElementsByTagName("jatszott_perc").item(0).getTextContent());
                        System.out.println("mez: " + statElement.getElementsByTagName("mez_szam").item(0).getTextContent());

                    }
                }
            }

        } catch (Exception exception) {
            System.out.println("nem sikerült a módosítás");
        }
    }
    //ez egy id alapján megkeresi az element-et majd a megfelelő tag értékét vissza adja
    //Csak akkor mukodik ha schema fájlban megfelelően van definiálva az id
    public String getTagValueByID(Document doc, String id, String tag) {
        Element element = doc.getElementById(id);
        return element.getElementsByTagName(tag).item(0).getTextContent();
    }

}
