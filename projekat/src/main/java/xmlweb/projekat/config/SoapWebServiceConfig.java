package xmlweb.projekat.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * @author Nikola
 *
 */
@EnableWs
@Configuration
public class SoapWebServiceConfig extends WsConfigurerAdapter {

	private String namespace = "http://xmlweb/projekat/entities/soap"; // "http://spring.io/guides/gs-producing-web-service"

	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
			ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<MessageDispatcherServlet>(servlet, "/ws/*");
	}
	
	// accomodation agent
	@Bean(name = "accomodation_agent")
	public DefaultWsdl11Definition defaultAccomodationAgentDefinition(XsdSchema accomodationAgentSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("AccomodationAgentPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace(namespace);
		wsdl11Definition.setSchema(accomodationAgentSchema);
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema accomodationAgentSchema() {
		return new SimpleXsdSchema(new ClassPathResource("soap_schemas/accomodation_agent.xsd"));
	}
	
	// accomodation bonus service
	@Bean(name = "accomodation_bonus_service")
	public DefaultWsdl11Definition defaultAccomodationBonusServiceDefinition(XsdSchema accomodationBonusServiceSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("AccomodationBonusServicePort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace(namespace);
		wsdl11Definition.setSchema(accomodationBonusServiceSchema);
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema accomodationBonusServiceSchema() {
		return new SimpleXsdSchema(new ClassPathResource("soap_schemas/accomodation_bonus_service.xsd"));
	}
	
	// accomodation type
	@Bean(name = "accomodation_type")
	public DefaultWsdl11Definition defaultAccomodationTypeDefinition(XsdSchema accomodationTypeSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("AccomodationTypePort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace(namespace);
		wsdl11Definition.setSchema(accomodationTypeSchema);
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema accomodationTypeSchema() {
		return new SimpleXsdSchema(new ClassPathResource("soap_schemas/accomodation_type.xsd"));
	}
	
	// accomodation 
	@Bean(name = "accomodation")
	public DefaultWsdl11Definition defaultAccomodationDefinition(XsdSchema accomodationSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("AccomodationPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace(namespace);
		wsdl11Definition.setSchema(accomodationSchema);
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema accomodationSchema() {
		return new SimpleXsdSchema(new ClassPathResource("soap_schemas/accomodation.xsd"));
	}
	
	// bonus service
	@Bean(name = "bonus_service")
	public DefaultWsdl11Definition defaultBonusServiceDefinition(XsdSchema bonusServiceSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("BonusServicePort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace(namespace);
		wsdl11Definition.setSchema(bonusServiceSchema);
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema bonusServiceSchema() {
		return new SimpleXsdSchema(new ClassPathResource("soap_schemas/bonus_service.xsd"));
	}
	
	// comment
	@Bean(name = "comment")
	public DefaultWsdl11Definition defaultCommentDefinition(XsdSchema commentSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("CommentPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace(namespace);
		wsdl11Definition.setSchema(commentSchema);
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema commentSchema() {
		return new SimpleXsdSchema(new ClassPathResource("soap_schemas/comment.xsd"));
	}
	
	// location
	@Bean(name = "location")
	public DefaultWsdl11Definition defaultLocationDefinition(XsdSchema locationSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("LocationPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace(namespace);
		wsdl11Definition.setSchema(locationSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema locationSchema() {
		return new SimpleXsdSchema(new ClassPathResource("soap_schemas/location.xsd"));
	}
	
	// message
	@Bean(name = "message")
	public DefaultWsdl11Definition defaultMessageDefinition(XsdSchema messageSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("MessagePort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace(namespace);
		wsdl11Definition.setSchema(messageSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema messageSchema() {
		return new SimpleXsdSchema(new ClassPathResource("soap_schemas/message.xsd"));
	}
	
	// picture
	@Bean(name = "picture")
	public DefaultWsdl11Definition defaultPictureDefinition(XsdSchema pictureSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("PicturePort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace(namespace);
		wsdl11Definition.setSchema(pictureSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema pictureSchema() {
		return new SimpleXsdSchema(new ClassPathResource("soap_schemas/picture.xsd"));
	}
	
	// price
	@Bean(name = "price")
	public DefaultWsdl11Definition defaultPriceDefinition(XsdSchema priceSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("PricePort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace(namespace);
		wsdl11Definition.setSchema(priceSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema priceSchema() {
		return new SimpleXsdSchema(new ClassPathResource("soap_schemas/price.xsd"));
	}
	
	
	// reservation
	@Bean(name = "reservation")
	public DefaultWsdl11Definition defaultReservationDefinition(XsdSchema reservationSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("ReservationPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace(namespace);
		wsdl11Definition.setSchema(reservationSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema reservationSchema() {
		return new SimpleXsdSchema(new ClassPathResource("soap_schemas/reservation.xsd"));
	}
	
	//user
	@Bean(name = "user")
	public DefaultWsdl11Definition defaultUserDefinition(XsdSchema userSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("UserPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace(namespace);
		wsdl11Definition.setSchema(userSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema userSchema() {
		return new SimpleXsdSchema(new ClassPathResource("soap_schemas/user.xsd"));
	}

}