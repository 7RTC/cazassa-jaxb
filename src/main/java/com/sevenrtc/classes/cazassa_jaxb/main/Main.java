package com.sevenrtc.classes.cazassa_jaxb.main;

import com.sevenrtc.classes.cazassa_jaxb.modelo.Item;
import com.sevenrtc.classes.cazassa_jaxb.modelo.Items;
import com.sevenrtc.classes.cazassa_jaxb.modelo.PurchaseOrderType;
import com.sun.xml.txw2.output.IndentingXMLStreamWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
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

        // Writer
        try {
            JAXBContext jc = JAXBContext.newInstance("com.sevenrtc.classes.cazassa_jaxb.modelo");
            Marshaller marshaller = jc.createMarshaller();
            // Remover caso não precise identar
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            escreverObjetoInteiro(purchaseOrderType, marshaller);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            escreverFragmentos(itemList, marshaller);
        } catch (JAXBException e) {
            e.printStackTrace();
        }


    }

    private static void escreverObjetoInteiro(PurchaseOrderType purchaseOrderType, Marshaller marshaller) throws JAXBException {
        try(FileOutputStream fos =  new FileOutputStream(new File("resultado.xml"));
            OutputStreamWriter ows = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            BufferedWriter bw = new BufferedWriter(ows, 4096)) {
            System.out.println("Escrevendo arquivo");
            marshaller.marshal(purchaseOrderType, bw);
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void escreverFragmentos(List<Item> itemList, Marshaller marshaller) throws JAXBException {
        try(FileOutputStream fos =  new FileOutputStream(new File("resultado2.xml"));
            BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            XMLStreamWriter xmlWriter = XMLOutputFactory.newFactory().createXMLStreamWriter(bos, "UTF-8");

            // Remover caso não precise identar
            IndentingXMLStreamWriter iXmlWriter = new IndentingXMLStreamWriter(xmlWriter);
            iXmlWriter.setIndentStep("    ");
            xmlWriter = iXmlWriter;


            System.out.println("Escrevendo segundo arquivo");

            xmlWriter.writeStartDocument("UTF-8", "1.0");
            xmlWriter.writeStartElement("purchaseOrderType");
            xmlWriter.writeAttribute("orderDate","2015-05-14-03:00");
            xmlWriter.writeDefaultNamespace("http://tempuri.org/po.xsd");

            xmlWriter.writeStartElement("comment");
            xmlWriter.writeCharacters("Isso é um comentário");
            xmlWriter.writeEndElement();

            xmlWriter.writeStartElement("items");

            QName itemQName = new QName("http://tempuri.org/po.xsd", "item");
            for (Item item : itemList) {
                JAXBElement<Item> itemElement = new JAXBElement<>(itemQName, Item.class, null, item);
                marshaller.marshal(itemElement, xmlWriter);
            }

            xmlWriter.writeEndElement();

            xmlWriter.writeEndElement();
            xmlWriter.writeEndDocument();
        }  catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
