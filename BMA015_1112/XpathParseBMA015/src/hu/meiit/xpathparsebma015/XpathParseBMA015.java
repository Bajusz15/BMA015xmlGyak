package hu.meiit.xpathparsebma015;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
public class XpathParseBMA015 {
    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true); // never forget this!
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("./studentBMA015.xml");
            //Create XPath
            XPathFactory xpathfactory = XPathFactory.newInstance();
            XPath xpath = xpathfactory.newXPath();

            System.out.println("n//1) ");

            XPathExpression firstName = xpath.compile("//student/firstname/text()");
            XPathExpression lastName = xpath.compile("//student/lastname/text()");
            XPathExpression nickName = xpath.compile("//student/nickname/text()");
            XPathExpression marks = xpath.compile("//student/marks/text()");
            Object firstNameObject = firstName.evaluate(doc, XPathConstants.NODESET);
            Object lastNameObject = lastName.evaluate(doc, XPathConstants.NODESET);
            Object nickNameObject = nickName.evaluate(doc, XPathConstants.NODESET);
            Object marksObject = marks.evaluate(doc, XPathConstants.NODESET);

            NodeList firstNameNodes = (NodeList) firstNameObject;
            NodeList lastNameNodes = (NodeList) lastNameObject;
            NodeList nickNameNodes = (NodeList) nickNameObject;
            NodeList marksNodes = (NodeList) marksObject;
            for (int i = 0; i < lastNameNodes.getLength(); i++) {
                System.out.println("Current element: student");
                System.out.println(firstNameNodes.item(i).getNodeValue());
                System.out.println(lastNameNodes.item(i).getNodeValue());
                System.out.println(nickNameNodes.item(i).getNodeValue());
                System.out.println(marksNodes.item(i).getNodeValue());
            }
        } catch (Exception exception){

        }



    }
}
