//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.23 at 11:37:50 AM CEST 
//


package xmlweb.projekat.soap.models.accomodation_bonus_service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AccomodationBonusServiceRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccomodationBonusServiceRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bonus_service_id">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
 *               &lt;minInclusive value="0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="accomodation_id">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
 *               &lt;minInclusive value="0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccomodationBonusServiceRequest", propOrder = {
    "bonusServiceId",
    "accomodationId"
})
public class AccomodationBonusServiceRequest {

    @XmlElement(name = "bonus_service_id")
    protected long bonusServiceId;
    @XmlElement(name = "accomodation_id")
    protected long accomodationId;

    /**
     * Gets the value of the bonusServiceId property.
     * 
     */
    public long getBonusServiceId() {
        return bonusServiceId;
    }

    /**
     * Sets the value of the bonusServiceId property.
     * 
     */
    public void setBonusServiceId(long value) {
        this.bonusServiceId = value;
    }

    /**
     * Gets the value of the accomodationId property.
     * 
     */
    public long getAccomodationId() {
        return accomodationId;
    }

    /**
     * Sets the value of the accomodationId property.
     * 
     */
    public void setAccomodationId(long value) {
        this.accomodationId = value;
    }

}
