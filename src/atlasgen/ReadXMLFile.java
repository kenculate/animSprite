/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package atlasgen;

import java.awt.Point;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

/**
 *
 * @author ken
 */

public class ReadXMLFile {

    public ReadXMLFile() {
        readXML();
    }
    public void readXML()
    {
        try
        {
            File fXmlFile = new File("./test.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("shader");

//            Node n = doc.getElementsByTagName("shader");

            System.out.println("-----------------------");

            for (int i = 0; i < nList.getLength(); i++)
            {
                Node n = nList.item(i);
                if (n.hasChildNodes())
                {
                     NodeList nL = n.getChildNodes();
                     for (int j = 0; j < nL.getLength(); j++)
                     {
                         Node nn = nL.item(j);
//                         if (nn.getNodeType() == Node.ELEMENT_NODE)
//                         {
//                             getNode(nn,n,null);
//
//                         }
                     }
                }
            }
	  } catch (Exception e) {

		e.printStackTrace();
	  }
    }

    public String getAttribValue(Node _n, String _attr)
    {
        return _n.getAttributes().getNamedItem(_attr).getNodeValue();
    }
  public String getTagValue(String sTag, Element eElement) {
	NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();

        Node nValue = (Node) nlList.item(0);

	return nValue.getNodeValue();
  }

}
