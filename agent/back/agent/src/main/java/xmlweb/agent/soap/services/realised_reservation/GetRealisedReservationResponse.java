//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.24 at 12:00:44 AM CEST 
//


package xmlweb.agent.soap.services.realised_reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="realised_reservation_response" type="{http://xmlweb/projekat/soap/services/realised_reservation}RealisedReservationSOAP"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "realisedReservationResponse"
})
@XmlRootElement(name = "getRealisedReservationResponse")
public class GetRealisedReservationResponse {

    @XmlElement(name = "realised_reservation_response", required = true)
    protected RealisedReservationSOAP realisedReservationResponse;

    /**
     * Gets the value of the realisedReservationResponse property.
     * 
     * @return
     *     possible object is
     *     {@link RealisedReservationSOAP }
     *     
     */
    public RealisedReservationSOAP getRealisedReservationResponse() {
        return realisedReservationResponse;
    }

    /**
     * Sets the value of the realisedReservationResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link RealisedReservationSOAP }
     *     
     */
    public void setRealisedReservationResponse(RealisedReservationSOAP value) {
        this.realisedReservationResponse = value;
    }

}