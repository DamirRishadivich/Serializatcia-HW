package test.java;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class ShopXML {
    boolean enable;
    String fileName;
    String fileFormat;

    public boolean getEnable() {
        return enable;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public ShopXML(String option) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("shop.xml"));
        Element element = doc.getDocumentElement();

        NodeList options = element.getChildNodes();
        for (int i = 0; i <options.getLength() ; i++) {
            Node node = options.item(i);
            if (node.getNodeName().equals(option)) {
                NodeList param = node.getChildNodes();
                for (int j = 0; j < param.getLength(); j++) {
                    Node node1 = param.item(j);
                    if (Node.ELEMENT_NODE == node1.getNodeType()) {
                        if(node1.getNodeName().equals("enabled")) {
                            enable = node1.getTextContent().equals("true") ? true : false;
                        }
                        if (node1.getNodeName().equals("fileName")) {
                            fileName = node1.getTextContent();
                        }
                        if (node1.getNodeName().equals("format")){
                            fileFormat = node1.getTextContent();
                        }
                    }
                }

            }
        }
    }


}