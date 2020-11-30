package hu.domparse.bma015;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMReadBMA015 {

    //összes játékos
    public void GetPlayers(Document doc) {
        System.out.println("\n játékosok:");
        NodeList nodeList = doc.getElementsByTagName("jatekos");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            System.out.println();
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                System.out.println("játékos azonosító: " + element.getAttribute("id"));
                System.out.println("vezetéknév: " + element.getElementsByTagName("vezeteknev").item(0).getTextContent());
                System.out.println("keresztnév: " + element.getElementsByTagName("keresztnev").item(0).getTextContent());
                System.out.println("születési dàtum: " + element.getElementsByTagName("szuletesi_datum").item(0).getTextContent());
            }
        }
    }
    //összes csapat
    public void GetTeams(Document doc) {
        System.out.println("\n csapatok:");
        NodeList nodeList = doc.getElementsByTagName("csapat");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            System.out.println();
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                System.out.println("csapat azonosító: " + element.getAttribute("id"));
                System.out.println("bajnokság / liga: " + element.getElementsByTagName("liga").item(0).getTextContent());
                System.out.println("Csapat név: " + element.getElementsByTagName("nev").item(0).getTextContent());
                System.out.println("város: " + element.getElementsByTagName("varos").item(0).getTextContent());
            }
        }
    }
    //osszes merkozes, és a hozzá tartozó csapat nevek, játékos statisztikák
    public void GetGames(Document doc) {
        System.out.println("\n mérkőzés információk:");
        NodeList merkozesek = doc.getElementsByTagName("merkozes");
//        NodeList csapatok = doc.getElementsByTagName("csapat");
        for (int i = 0; i < merkozesek.getLength(); i++) {
            Node node = merkozesek.item(i);
            System.out.println();
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                System.out.println("mérkőzés azonosító: " + element.getAttribute("id"));
                System.out.println("bajnokság / liga: " + element.getElementsByTagName("liga").item(0).getTextContent());
                System.out.println("hazai csapat: " +  getTagValueByID(doc, element.getAttribute("hazai_csapat"), "nev"));
                System.out.println("vendég csapat: " +   getTagValueByID(doc, element.getAttribute("vendeg_csapat"), "nev"));
                Element eredmeny = (Element) element.getElementsByTagName("eredmeny").item(0);
                System.out.println("hazai eredmény: " +eredmeny.getElementsByTagName("hazai").item(0).getTextContent());
                System.out.println("vendég eredmény: " + eredmeny.getElementsByTagName("vendeg").item(0).getTextContent());
            }
        }
    }
    //Az összes játékos statisztika, játékosonként csoportosítva
    public void GetAllPlayerStatisticsByPlayer(Document doc) {
        System.out.println("\n minden játékos statisztikája, játékosonként csoportosítva");
        NodeList jatekosok = doc.getElementsByTagName("jatekos");
        NodeList statistics = doc.getElementsByTagName("jatekos_statisztika");
        for (int i = 0; i < jatekosok.getLength(); i++) {
            Node jatekos = jatekosok.item(i);
            if (jatekos.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) jatekos;
                String id = element.getAttribute("id");
                System.out.println("*********************************************");
                System.out.println("játékos azonosító: " + id);
                System.out.println("vezetéknév: " + element.getElementsByTagName("vezeteknev").item(0).getTextContent());
                System.out.println("keresztnév: " + element.getElementsByTagName("keresztnev").item(0).getTextContent());
                System.out.println("születési dàtum: " + element.getElementsByTagName("szuletesi_datum").item(0).getTextContent());
                //ez lekérdezi az adott játékoshoz tartozó statisztikákat
                for (int j = 0; j < statistics.getLength(); j++) {
                    Node stat = statistics.item(j);
                    if (stat.getNodeType() == Node.ELEMENT_NODE) {
                        Element statElement = (Element) stat;
                        if (statElement.getAttribute("jatekos").equals(id)){
                            System.out.println("mérkőzés azonosító: " + statElement.getAttribute("meccs_id"));
                            System.out.println("csapatban játszott: " + getTagValueByID(doc, statElement.getAttribute("csapat_id"), "nev"));
                            System.out.println("liga: " + statElement.getElementsByTagName("liga").item(0).getTextContent());
                            System.out.println("szerzett_gol: " + statElement.getElementsByTagName("szerzett_gol").item(0).getTextContent());
                            System.out.println("jatszott_perc: " + statElement.getElementsByTagName("jatszott_perc").item(0).getTextContent());
                        }
                    }
                }
            }
        }
    }
    //ez egy id alapján megkeresi az element-et majd a megfelelő tag értékét vissza adja
    //Csak akkor mukodik ha schema fájlban megfelelően van definiálva az id
    public String getTagValueByID(Document doc, String id, String tag) {
        Element element = doc.getElementById(id);
        return element.getElementsByTagName(tag).item(0).getTextContent();
    }
}
