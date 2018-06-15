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

}