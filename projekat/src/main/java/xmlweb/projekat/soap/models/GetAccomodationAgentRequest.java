//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.16 at 04:22:11 PM CEST 
//


package xmlweb.projekat.soap.models;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="accomodation_agent" type="{http://xmlweb/projekat/soap/models}AccomodationAgentRequest" maxOccurs="unbounded" minOccurs="0"/>
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
    "accomodationAgent"
})
@XmlRootElement(name = "getAccomodationAgentRequest")
public class GetAccomodationAgentRequest {

    @XmlElement(name = "accomodation_agent")
    protected List<AccomodationAgentRequest> accomodationAgent;

    /**
     * Gets the value of the accomodationAgent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accomodationAgent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccomodationAgent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccomodationAgentRequest }
     * 
     * 
     */
    public List<AccomodationAgentRequest> getAccomodationAgent() {
        if (accomodationAgent == null) {
            accomodationAgent = new ArrayList<AccomodationAgentRequest>();
        }
        return this.accomodationAgent;
    }

}
