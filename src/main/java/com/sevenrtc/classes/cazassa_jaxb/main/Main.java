package com.sevenrtc.classes.cazassa_jaxb.main;

import com.sevenrtc.classes.cazassa_jaxb.modelo.Item;
import com.sevenrtc.classes.cazassa_jaxb.modelo.Items;
import com.sevenrtc.classes.cazassa_jaxb.modelo.PurchaseOrderType;
import com.sun.xml.internal.txw2.output.IndentingXMLStreamWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by a.accioly on 5/14/15.
 */
public class Main {

    public static void main(String[] args) {
        // ItemReader
        PurchaseOrderType purchaseOrderType = new PurchaseOrderType();
        purchaseOrderType.setComment("Isso é um comentário");
        purchaseOrderType.setOrderDate(Calendar.getInstance());
        Items items = new Items();
        List<Item> itemList = new ArrayList<>();
        // Processor
        itemList.add(new Item("Coca", 1, new BigDecimal("2.0"), "Gelada", Calendar.getInstance(), "oi"));
        itemList.add(new Item("Cheese-Burger", 1, new BigDecimal("5.0"), "Quente", Calendar.getInstance(), "oi"));
        itemList.add(new Item("Batata", 1, new BigDecimal("5.0"), "Salgada", Calendar.getInstance(), "oi"));
        items.setItem(itemList);
        purchaseOrderType.setItems(items);
        // Writter
        try {
            JAXBContext jc = JAXBContext.newInstance("com.sevenrtc.classes.cazassa_jaxb.modelo");
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            try(FileOutputStream fos =  new FileOutputStream(new File("resultado.xml"));
                OutputStreamWriter ows = new OutputStreamWriter(fos, Charset.forName("UTF-8"));
                BufferedWriter bw = new BufferedWriter(ows, 4096)) {
                System.out.println("Escrevendo arquivo");
                marshaller.marshal(purchaseOrderType, bw);
            }  catch (IOException e) {
                e.printStackTrace();
            }

            try(FileOutputStream fos =  new FileOutputStream(new File("resultado2.xml"));
                OutputStreamWriter ows = new OutputStreamWriter(fos, Charset.forName("UTF-8"));
                BufferedWriter bw = new BufferedWriter(ows, 4096)) {
                XMLStreamWriter xmlWriter = new IndentingXMLStreamWriter(XMLOutputFactory.newFactory().createXMLStreamWriter(bw));
                System.out.println("Escrevendo segundo arquivo");

                xmlWriter.writeStartDocument();
                xmlWriter.writeStartElement("purchaseOrderType");
                xmlWriter.writeAttribute("xmlns","http://tempuri.org/po.xsd");
                xmlWriter.writeAttribute("orderDate","2015-05-14-03:00");
                xmlWriter.writeStartElement("items");

                for (Item item : itemList) {
                    marshaller.marshal(item, xmlWriter);
                }
                xmlWriter.writeEndElement();
                xmlWriter.writeEndElement();
                xmlWriter.writeEndDocument();
            }  catch (IOException e) {
                e.printStackTrace();
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }


    }
}
