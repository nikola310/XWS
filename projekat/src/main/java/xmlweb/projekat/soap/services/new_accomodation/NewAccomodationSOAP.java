//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.23 at 11:37:50 AM CEST 
//


package xmlweb.projekat.soap.services.new_accomodation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NewAccomodationSOAP complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NewAccomodationSOAP">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="new_message_response" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NewAccomodationSOAP", propOrder = {
    "newMessageResponse"
})
public class NewAccomodationSOAP {

    @XmlElement(name = "new_message_response")
    protected boolean newMessageResponse;

    /**
     * Gets the value of the newMessageResponse property.
     * 
     */
    public boolean isNewMessageResponse() {
        return newMessageResponse;
    }

    /**
     * Sets the value of the newMessageResponse property.
     * 
     */
    public void setNewMessageResponse(boolean value) {
        this.newMessageResponse = value;
    }

}
