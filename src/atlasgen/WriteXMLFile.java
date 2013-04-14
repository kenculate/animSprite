/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package atlasgen;

/**
 *
 * @author ken
 */

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class WriteXMLFile {
    public Document doc;
    public Element rootElement;
    public Element lastElement;
    public WriteXMLFile(String dir, String fileName) {
        addbody(fileName);
//        addbranch(lastnode.inputList.get(0).LeftNode,rootElement);
//        addbranch(getNodeobject(lastnode,rootElement),rootElement);
//        getNodeobject(lastnode,rootElement);
        writeXML(dir, fileName);
        
    }


    public void addbody(String fn)
    {
        try
        {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            doc = docBuilder.newDocument();

            Element rootElement1 = doc.createElement("plist");
            doc.appendChild(rootElement1);
            Attr attr = doc.createAttribute("version");
            attr.setValue("1.0");
            rootElement1.setAttributeNode(attr);
            
            int iw = AtlasGenView.imageWidth;
            int ih = AtlasGenView.imageheight;
            int ix = AtlasGenView.imageSplitX;
            int iy = AtlasGenView.imageSplitY;
            int isx = (int)(iw/ix);
            int isy = (int)(ih/iy);

            int ls = AtlasGenView.lm.getSize();

            Element el1 = addElement(rootElement1, "dict");

            Element elFrames1 = addElement(el1, "key");
            addTextElement(elFrames1, "frames");
            
            Element el2 = addElement(el1, "dict");

            int frame = 0;
            for(int i = 0; i < ls; i++)
            {
                frame++;
                int fi = Integer.parseInt(AtlasGenView.lm.getElementAt(i).toString());
//                if (fi<ix)
                int xi = (fi) / ix;
                int yi = (fi) % ix;
                System.out.print(Integer.toString(yi) + " " + Integer.toString(xi) + "\n");
                addFrame(el2, fn.substring(0, fn.length()-4) + frame, yi, xi , isx, isy);
            }
//            for(int i = 1; i <= iy; i++)
//            {
//                for(int j = 1; j <= ix; j++)
//                {
//                    frame++;
//
//                    addFrame(el2, fn.substring(0, fn.length()-4) + frame, i, j, isx, isy);
//                }
//            }
            addMetaData(el1, fn, iw, ih);
        }
        catch (ParserConfigurationException pce)
        {
            pce.printStackTrace();
        }

    }

    public void addElementWithText(Element _EL, String name, String str)
    {
        Element el = addElement(_EL, name);
        addTextElement(el, str);
    }
    
    public void addMetaData(Element _EL, String fn, int iw, int ih)
    {
        addElementWithText(_EL, "key", "metadata");
        Element el = addElement(_EL, "dict");
        addElementWithText(el, "key", "format");
        addElementWithText(el, "integer", "2");
        addElementWithText(el, "key", "realTextureFileName");
        addElementWithText(el, "string", fn);
        addElementWithText(el, "key", "size");
        addElementWithText(el, "string", "{" + iw + "," + ih + "}");
        addElementWithText(el, "key", "textureFileName");
        addElementWithText(el, "string", fn);
    }
    public void addFrame(Element _EL, String name, int i, int j, int isx, int isy)
    {
        addElementWithText(_EL, "key", name);
        Element el4 = addElement(_EL, "dict");
        
        addElementWithText(el4, "key", "frame");
        addElementWithText(el4, "string", "{{" + (i)*isx + "," + (j)*isy + "},{" + isx + "," + isy + "}}");

        addElementWithText(el4, "key", "offset");
        addElementWithText(el4, "string", "{0,0}");

        addElementWithText(el4, "key", "rotated");
        addElement(el4, "fase");

        addElementWithText(el4, "key", "sourceColorRect");
        addElementWithText(el4, "string", "{{0,0},{" + isx + "," + isy + "}}");

        addElementWithText(el4, "key", "sourceSize");
        addElementWithText(el4, "string", "{" + isx + "," + isy + "}");
    }
    public void addbranch(Element _LE, String name)
    {
        Element el = addElement(_LE, name);
    }

    public void addAttrib(Element _el,String _name,String _value)
    {
        Attr attr = doc.createAttribute(_name);
        attr.setValue(_value);
        _el.setAttributeNode(attr);
    }

    public Element addElement(Element _el, String _name)
    {
        Element el = doc.createElement(_name);
        _el.appendChild(el);
        return el;
    }

    public Text addTextElement(Element _el, String _name)
    {
        Text el = doc.createTextNode(_name);
        _el.appendChild(el);
        return el;
    }
 
    public void writeXML(String dir, String fileName)
    {
        try
        {


        TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformerFactory.setAttribute("indent-number", 4);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(new File(dir.substring(0, dir.length()-4) + ".xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");
        }
        catch (TransformerException tfe)
        {
            tfe.printStackTrace();
        }
    }
    
}