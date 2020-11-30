package hu.domparse.bma015;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final String XML_SCHEMA =
                "http://www.w3.org/2001/XMLSchema";
        final String SCHEMA_LANG =
                "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
        final String SCHEMA_SOURCE =
                "http://java.sun.com/xml/jaxp/properties/schemaSource";
        File schema = new File("./XMLSchemaBMA015.xsd");
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setNamespaceAware(true);
            // Enables validation of xml document.
            dbFactory.setValidating(true);
            try {
                //Setting the required schema details
                dbFactory.setAttribute(SCHEMA_LANG, XML_SCHEMA);
                dbFactory.setAttribute(SCHEMA_SOURCE, schema);
            } catch (IllegalArgumentException x) {
                System.err.println("DOM Parser" + "does not support validation.");
            }
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse("./XDMBMA015.xml");
            doc.getDocumentElement().normalize();

            DOMReadBMA015 reader = new DOMReadBMA015();
            DomModifyBMA015 modifier = new DomModifyBMA015();

            Scanner scan = new Scanner(System.in);
            String select;
            //do while loop-al itt tartjuk
            do {
                System.out.println("Válassza ki mit szeretne csinálni");
                System.out.println(" 1: listázás, 2: módosítás, q: kilépés");
                select = scan.next();
                String select2;
                switch (select) {
                    case "1":
                        System.out.println("Mit szeretne listázni?");
                        System.out.println("1: összes játékos");
                        System.out.println("2: összes csapatot");
                        System.out.println("3: összes mérkőzés adatait");
                        System.out.println("4: összes játékos statisztika játékosonként csoportosítva");
                        select2= scan.next();
                        switch (select2) {
                            case "1" -> reader.GetPlayers(doc);
                            case "2" -> reader.GetTeams(doc);
                            case "3" -> reader.GetGames(doc);
                            case "4" -> reader.GetAllPlayerStatisticsByPlayer(doc);
                        }
                        break;
                    case "2":
                        System.out.println("Melyik elemet szeretné módosítani? ");
                        System.out.println("1: Csapat");
                        System.out.println("2: játékos");
                        System.out.println("3: mérkőzés ");
                        System.out.println("4: játékos statisztika ");
                        select2 = scan.next();
                        switch (select2) {
                            case "1" -> {
                                modifier.ModifyTeam(doc);
                                saveDocument(doc);
                            }
                            case "2" -> {
                                modifier.ModifyPlayer(doc);
                                saveDocument(doc);
                            }
                            case "3" -> {
                                modifier.ModifyGame(doc);
                                saveDocument(doc);
                            }
                            case "4" -> {
                                modifier.ModifyPlayerStatistic(doc);
                                saveDocument(doc);
                            }
                        }
                        break;
                }
            } while (!select.equals("q"));
//            menu(doc);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveDocument(Document doc) throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Result output = new StreamResult(new File("./XDMBMA015.xml"));
        Source input = new DOMSource(doc);
        transformer.transform(input, output);
    }

}
