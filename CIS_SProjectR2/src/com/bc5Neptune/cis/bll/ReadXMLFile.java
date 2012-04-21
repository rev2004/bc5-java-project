/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bc5Neptune.cis.bll;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;

/**
 *
 * @author phu.huynh
 */
public class ReadXMLFile {

    Document doc;
    String path;

    public ReadXMLFile() {
    }

    public ReadXMLFile(String path) {
        this.path = path;
    }

    /* open xml file */
    public void open() {
        File fxmlFile = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            try {
                try {
                    doc = (Document) dBuilder.parse(fxmlFile);
                } catch (IOException ex) {
                    Logger.getLogger(ReadXMLFile.class.getName()).log(Level.SEVERE, null, ex);
                }
                doc.getDocumentElement().normalize();
            } catch (SAXException ex) {
                Logger.getLogger(ReadXMLFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ReadXMLFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* get tag value */
    private static String getTagValue(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();

        Node nValue = (Node) nlList.item(0);

        return nValue.getNodeValue();
    }

    /* save data to xml file */
    public void save() {
        // write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(ReadXMLFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(path));
        try {
            transformer.transform(source, result);
        } catch (TransformerException ex) {
            Logger.getLogger(ReadXMLFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Done");
    }
    /* change value of node */

    public void editNode(String nodeName, String value) {
        Node node = doc.getElementsByTagName(nodeName).item(0); //the first position
        if (node != null) {
            node.setTextContent(value);
            save();
            System.out.println("edit " + nodeName + " successfuly");
        } else {
            System.out.println("node not found: " + nodeName);
        }

    }
    
    /* delete node */
    public void deleteNode(String nodeName) {
        Node node = doc.getElementsByTagName(nodeName).item(0); //the first position
        if (node != null) {
            Node parentNode = node.getParentNode();
            parentNode.removeChild(node);
            //save data from DOM to xml file
            save();
            System.out.println("Delete " + nodeName + " successfuly");
        } else {
            System.out.println("node not found, could not delete: " + nodeName);
        }
    }

    public void displayData(String nodeName, String[] elementsNode) {
        NodeList nList = doc.getElementsByTagName(nodeName);
        System.out.println("-----------------------");

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;
                for (int i = 0; i < elementsNode.length; i++) {
                    System.out.println(elementsNode[i] + " :"
                            + getTagValue(elementsNode[i], eElement));
                }
            }
        }
        /* to used this function, you can type the same below 
        String[] elementsNode = {"rows","cols","dt","data"};
        String nodeName = "personTotalTruthMtr";
        objXML.displayData(nodeName, elementsNode);
         * 
         */
    }

    public static void main(String[] args) {
        ReadXMLFile objXML = new ReadXMLFile("/home/enclaveit/facedata.xml");
        objXML.open();
        objXML.deleteNode("prtTrainFaceMtr");
        String[] elementsNode = {"rows", "cols", "dt", "data"};
        String nodeName = "personTotalTruthMtr";
        objXML.displayData(nodeName, elementsNode);


    }
}
