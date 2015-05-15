
package com.sevenrtc.classes.cazassa_jaxb.modelo;

import java.math.BigDecimal;
import java.util.Calendar;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="quantity">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger">
 *               &lt;maxExclusive value="100"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="USPrice" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element ref="{http://tempuri.org/po.xsd}comment" minOccurs="0"/>
 *         &lt;element name="shipDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="partNum" use="required" type="{http://tempuri.org/po.xsd}SKU" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "productName",
    "quantity",
    "usPrice",
    "comment",
    "shipDate"
})
@XmlRootElement
public class Item {

    @XmlElement(required = true)
    protected String productName;
    protected int quantity;
    @XmlElement(name = "USPrice", required = true)
    protected BigDecimal usPrice;
    protected String comment;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "date")
    protected Calendar shipDate;
    @XmlAttribute(name = "partNum", required = true)
    protected String partNum;

    public Item() {
    }

    public Item(String productName, int quantity, BigDecimal usPrice, String comment, Calendar shipDate, String partNum) {
        this.productName = productName;
        this.quantity = quantity;
        this.usPrice = usPrice;
        this.comment = comment;
        this.shipDate = shipDate;
        this.partNum = partNum;
    }

    /**
     * Gets the value of the productName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the value of the productName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductName(String value) {
        this.productName = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     */
    public void setQuantity(int value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the usPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUSPrice() {
        return usPrice;
    }

    /**
     * Sets the value of the usPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUSPrice(BigDecimal value) {
        this.usPrice = value;
    }

    /**
     * Gets the value of the comment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the value of the comment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComment(String value) {
        this.comment = value;
    }

    /**
     * Gets the value of the shipDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getShipDate() {
        return shipDate;
    }

    /**
     * Sets the value of the shipDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipDate(Calendar value) {
        this.shipDate = value;
    }

    /**
     * Gets the value of the partNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartNum() {
        return partNum;
    }

    /**
     * Sets the value of the partNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartNum(String value) {
        this.partNum = value;
    }

}
